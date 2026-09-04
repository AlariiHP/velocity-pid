package frc.robot;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class Buttonbinding {
    
    public static final XboxController driver = new XboxController(0);
    
    


    public Buttonbinding(){
    
    
    
        
    }

    

    
    public static void configureButtonBinding(RobotContainer robotContainer){
   

        Command OnVoltage = robotContainer.shooter.setVoltage(4.0);
        Command OffVoltage = robotContainer.shooter.setVoltage(0.0);
        Command OnVelocity = robotContainer.shooter.setVelocity(4.0);
        Command OffVelocity = robotContainer.shooter.setVelocity(0.0);

        Trigger buttonY = new JoystickButton(driver, XboxController.Button.kY.value);
        Trigger buttonX = new JoystickButton(driver, XboxController.Button.kX.value);
        
        buttonY
            .whileTrue(OnVoltage)
            .onFalse(OffVoltage);

        buttonX
            .whileTrue(OnVelocity)
            .onFalse(OffVelocity);

    }

}
