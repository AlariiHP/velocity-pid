package frc.robot;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.shooter.ShooterConstants.*;



public class Buttonbinding {
    
    public static final XboxController driver = new XboxController(0);
    
    


    public Buttonbinding(){
    
    
    
        
    }

    

    
    public static void configureButtonBinding(RobotContainer robotContainer){
   

        

        Trigger buttonY = new JoystickButton(driver, XboxController.Button.kY.value);
        Trigger buttonX = new JoystickButton(driver, XboxController.Button.kX.value);
        
        buttonY
            .whileTrue(robotContainer.shooter.setVoltage(States.VoltageON))
            .onFalse(robotContainer.shooter.setVoltage(States.VoltageOFF));

        buttonX
            .whileTrue(robotContainer.shooter.setVelocity(States.VelocityON))
            .onFalse(robotContainer.shooter.setVelocity(States.VoltageOFF));

    }

}
