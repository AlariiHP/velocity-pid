package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Degrees;

import java.util.function.BooleanSupplier;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.overture.lib.motorcontrollers.OverTalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Arm extends SubsystemBase{
    protected OverTalonFX armMotor;
    protected CANcoder armCC;
    private Angle target;
    
    public static CANBus canbus = new CANBus("rio");


    
    protected final PositionVoltage positionRequest = new PositionVoltage(0.0);

    public Arm(){
        
        armMotor = new OverTalonFX(ArmConstants.motorConfig(), ArmConstants.motorCanId, canbus);
        armCC = new CANcoder(ArmConstants.CCCanId, canbus);
        armCC.getConfigurator().apply(ArmConstants.CCConfig());
    }
    
    public void setTarget(Angle targetSetter){
        target = targetSetter;
    }

    public Angle getTarget(){
        return target;
    }

    
    public Command setPosition(Angle rotations){
        return 
            runOnce(() -> setTarget(rotations)) 
            .andThen(run(() -> armMotor.setControl(positionRequest.withPosition(getTarget()))).until(() -> isFinished()));
    }

    public Angle getPosition(){
        return Degrees.of(armMotor.getPosition().getValueAsDouble());
    }

    private Angle getError(){
        Angle error = getTarget().minus(getPosition());
        return Degrees.of(Math.abs(error.baseUnitMagnitude()));
    }
    
    private boolean isFinished(){
        return getError().baseUnitMagnitude() < ArmConstants.Control.AcceptedError.baseUnitMagnitude();
    }
    
    
    public void updateTelemetry(){
        SmartDashboard.putNumber("Subsystems/Arm/Position", getPosition().baseUnitMagnitude());
        SmartDashboard.putNumber("Subsystems/Arm/Target", getTarget().baseUnitMagnitude());
        SmartDashboard.putNumber("Subsystems/Arm/Error", getError().baseUnitMagnitude());
        
    }

    @Override
    public void periodic(){
        System.out.println(isFinished());
    }

}
