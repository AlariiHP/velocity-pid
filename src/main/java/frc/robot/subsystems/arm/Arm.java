package frc.robot.subsystems.arm;

import static edu.wpi.first.units.Units.Degrees;
import static edu.wpi.first.units.Units.Rotations;

import java.util.function.BooleanSupplier;
import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.hardware.CANcoder;
import com.ctre.phoenix6.controls.MotionMagicVoltage;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.overture.lib.motorcontrollers.OverTalonFX;

import edu.wpi.first.units.measure.Angle;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class Arm extends SubsystemBase{
    protected OverTalonFX armMotor;
    protected CANcoder armCC;
    private Angle target = Degrees.of(0.0);
    
    public static CANBus canbus = new CANBus("rio");
    

    
    protected final PositionVoltage positionRequest = new PositionVoltage(0.0);
    protected final MotionMagicVoltage motionMagicRequest = new MotionMagicVoltage(0);


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

    public Command setMotionMagic(Angle rotations){
        return  
            runOnce(() -> setTarget(rotations))
            .andThen(run(() -> armMotor.setControl(motionMagicRequest.withPosition(target))).until(() -> isFinished()));
    }
    

    public Angle getPosition(){
        return Degrees.of(armMotor.getPosition().getValueAsDouble()*360);
    }

    private Angle getError(){
        Angle error = getTarget().minus(getPosition());
        return Degrees.of(Math.abs(error.baseUnitMagnitude()));
    }
    
    private boolean isFinished(){
        return getError().baseUnitMagnitude() < ArmConstants.Control.AcceptedError.baseUnitMagnitude();
    }
    
    
    public void updateTelemetry(){
        SmartDashboard.putNumber("Subsystems/Arm/Position", getPosition().baseUnitMagnitude()*180/Math.PI);
        SmartDashboard.putNumber("Subsystems/Arm/Target", getTarget().baseUnitMagnitude()*180/Math.PI);
        SmartDashboard.putNumber("Subsystems/Arm/Error", getError().baseUnitMagnitude());
        
    }

    @Override
    public void periodic(){
        System.out.println(isFinished());
    }

}
