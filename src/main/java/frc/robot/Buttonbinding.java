package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;



public class Buttonbinding {
    
    public static final XboxController driver = new XboxController(0);

    public Buttonbinding(){
        }

    
    public static void configureButtonBinding(Robot robot){
        Trigger buttonY = new JoystickButton(driver, XboxController.Button.kY.value);

        buttonY.onTrue(
                new InstantCommand(
                    () -> {robot.shooter.setVoltage(4.0);} // aqui
                    
                ))
            .onFalse(
                new InstantCommand (
                    () -> {robot.shooter.setVoltage(0.0);} // aqui
                ));
    
    }

}
