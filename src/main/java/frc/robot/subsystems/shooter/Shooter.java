package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    protected TalonFX shooterMotor;
    protected int motorCanID = 22;

    public static CANBus canbus = new CANBus("rio");

    protected final VoltageOut voltageRequest = new VoltageOut(0.0);
    protected final VelocityVoltage velocityRequest = new VelocityVoltage(0.0);

    public Shooter(){
        
        var configs = new TalonFXConfiguration();
        // in init function, set slot 0 gains
        configs.Slot0.kS = 0.1; // Add 0.1 V output to overcome static friction
        configs.Slot0.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        configs.Slot0.kP = Constants.PID.kP; // An error of 1 rps results in 0.11 V output
        configs.Slot0.kI = Constants.PID.kI; // no output for integrated error
        configs.Slot0.kD = Constants.PID.kD; // no output for error derivative

        shooterMotor.getConfigurator().apply(configs);
    
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

    
    protected class Constants{
        protected class PID{

            protected static double kP = 0.1;
            protected static double kI = 0.0;
            protected static double kD = 0.0;
        
        }


    }

}
