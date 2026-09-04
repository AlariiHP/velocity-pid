package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.TalonFXConfiguration;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public Shooter(){
        var configs = new TalonFXConfiguration();
        // in init function, set slot 0 gains
        configs.Slot0.kS = 0.1; // Add 0.1 V output to overcome static friction
        configs.Slot0.kV = 0.12; // A velocity target of 1 rps results in 0.12 V output
        configs.Slot0.kP = ShooterIO.Constants.PID.kP; // An error of 1 rps results in 0.11 V output
        configs.Slot0.kI = ShooterIO.Constants.PID.kI; // no output for integrated error
        configs.Slot0.kD = ShooterIO.Constants.PID.kD; // no output for error derivative

        shooterIO.shooterMotor.getConfigurator().apply(configs);
    
    }

    protected ShooterIO shooterIO = new ShooterIO();
   
    public Command setVoltage(double volts){
        return runOnce(() -> shooterIO.setVoltage(volts));
    }

    public Command setVelocity(double rps){
        return runOnce(() -> shooterIO.setVelocity(rps));
    }

    @Override
    public void periodic(){

    }

    

    
}
