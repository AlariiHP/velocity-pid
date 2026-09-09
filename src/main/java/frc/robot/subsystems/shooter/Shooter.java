package frc.robot.subsystems.shooter;

import java.util.function.BooleanSupplier;

import com.ctre.phoenix6.CANBus;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.overture.lib.motorcontrollers.OverTalonFX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    protected OverTalonFX shooterMotor;
    private double target;
    
    public static CANBus canbus = new CANBus("rio");


    protected final VoltageOut voltageRequest = new VoltageOut(0.0);
    protected final VelocityVoltage velocityRequest = new VelocityVoltage(0.0);

    public Shooter(){
        
        shooterMotor = new OverTalonFX(ShooterConstants.motorConfig(), ShooterConstants.canId, canbus);

    }
    
    public void setTarget(double targetSetter){
        target = targetSetter;
    }

    public double getTarget(){
        System.out.println(target);
        return target;
    }
    
    public Command setVoltage(double volts){
        return runOnce(() -> shooterMotor.setVoltage(volts));
    }

    
    public Command setVelocity(double rps){
        return 
            runOnce(() -> setTarget(rps)) 
            .andThen(run(() -> shooterMotor.setControl(velocityRequest.withVelocity(getTarget()))).until(isFinishedSupplier()));
    }

    public double getVelocity(){
        return shooterMotor.getVelocity().getValueAsDouble();
    }

    private boolean isFinished(){
        return (Math.abs( getTarget() - getVelocity()) < ShooterConstants.Control.acceptedError);
    }

    private BooleanSupplier isFinishedSupplier() {
        BooleanSupplier supplier = 
        () -> isFinished();
        return supplier;
    }
    
    
    public void updateTelemetry(){
        SmartDashboard.putNumber("Subsystems/Shooter/Velocity", getVelocity());
        SmartDashboard.putNumber("Subsystems/Shooter/Target", getTarget());
    }

    @Override
    public void periodic(){

    }

  

}
