package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class Buttonbinding {
    
    public static final XboxController driver = new XboxController(0);

    public Buttonbinding(){
    }

    

    
    public static void configureButtonBinding(Robot robot){
        Command OnVoltage = robot.shooter.setVoltage(4.0);
        Command OffVoltage = robot.shooter.setVoltage(0.0);
        Command OnVelocity = robot.shooter.setVelocity(4.0);
        Command OffVelocity = robot.shooter.setVelocity(0.0);

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
