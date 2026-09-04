package frc.robot.subsystems.shooter;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Shooter extends SubsystemBase {

    public Shooter(){}

    protected ShooterIO shooterIO = new ShooterIO();
   
    public Command setVoltage(double volts){
        return runOnce(() -> shooterIO.setVoltage(volts));
    }

    @Override
    public void periodic(){

    }

    
}
