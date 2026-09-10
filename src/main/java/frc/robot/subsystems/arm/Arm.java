package frc.robot.subsystems.arm;

import java.util.function.BooleanSupplier;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.overture.lib.motorcontrollers.OverTalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Arm extends SubsystemBase{
    protected OverTalonFX armMotor;
    protected CANcoder armCC;
    private double target;
    
    public static CANBus canbus = new CANBus("rio");


    
    protected final PositionVoltage positionRequest = new PositionVoltage(0.0);

    public Arm(){
        
        armMotor = new OverTalonFX(ArmConstants.motorConfig(), ArmConstants.motorCanId, canbus);
        armCC = new CANcoder(ArmConstants.CCCanId, canbus);
        armCC.getConfigurator().apply(ArmConstants.CCConfig());
    }
    
    public void setTarget(double targetSetter){
        target = targetSetter;
    }

    public double getTarget(){
        return target;
    }

    
    public Command setPosition(double rotations){
        return 
            runOnce(() -> setTarget(rotations)) 
            .andThen(run(() -> armMotor.setControl(positionRequest.withPosition(getTarget()))).until(() -> isFinished()));
    }

    public double getPosition(){
        return armMotor.getPosition().getValueAsDouble();
    }

    private double getError(){
        return (Math.abs( getTarget() - getPosition()));
    }
    
    private boolean isFinished(){
        return getError() < ArmConstants.Control.AcceptedError;
    }
    
    
    public void updateTelemetry(){
        SmartDashboard.putNumber("Subsystems/Arm/Position", getPosition());
        SmartDashboard.putNumber("Subsystems/Arm/Target", getTarget());
        SmartDashboard.putNumber("Subsystems/Arm/Error", getError());
        
    }

    @Override
    public void periodic(){
        System.out.println(isFinished());
    }

}
