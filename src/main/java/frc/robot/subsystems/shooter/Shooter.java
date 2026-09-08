package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.CANBus;

import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.overture.lib.motorcontrollers.OverTalonFX;
import com.overture.lib.motorcontrollers.OverTalonFXConfig;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    protected OverTalonFX shooterMotor;
    protected OverTalonFXConfig config;
    
    public static CANBus canbus = new CANBus("rio");

    protected final VoltageOut voltageRequest = new VoltageOut(0.0);
    protected final VelocityVoltage velocityRequest = new VelocityVoltage(0.0);

    public Shooter(){
        config = new OverTalonFXConfig(config);
        shooterMotor = new OverTalonFX(config, canbus);

    }

    
    public Command setVoltage(double volts){
        return runOnce(() -> shooterMotor.setVoltage(volts));
    }

    
    public Command setVelocity(double rps){
        return runOnce(() -> shooterMotor.setControl(velocityRequest.withVelocity(rps).withFeedForward(0.5)));
    }

    public double getVelocity(){
        return shooterMotor.getVelocity().getValueAsDouble();
    }
    
    public void updateTelemetry(){
        SmartDashboard.putNumber("Subsystems/Shooter", getVelocity());
    }

    @Override
    public void periodic(){

    }

  

}
