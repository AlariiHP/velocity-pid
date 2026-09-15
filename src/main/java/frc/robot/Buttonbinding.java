package frc.robot;

import edu.wpi.first.networktables.BooleanEntry;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.arm.ArmConstants;
import frc.robot.subsystems.shooter.ShooterConstants.*;



public class Buttonbinding {
    
    public static final XboxController driver = new XboxController(0);
    
    


    public Buttonbinding(){
    
    
    
        
    }

    

    
    public static void configureButtonBinding(RobotContainer robotContainer){
   

        

        Trigger buttonY = new JoystickButton(driver, XboxController.Button.kY.value);
        Trigger buttonX = new JoystickButton(driver, XboxController.Button.kX.value);
        Trigger buttonA = new JoystickButton(driver, XboxController.Button.kA.value);
        Trigger buttonB = new JoystickButton(driver, XboxController.Button.kB.value);
        
        buttonY
            .whileTrue(robotContainer.shooter.setVoltage(States.VoltageON.baseUnitMagnitude()))
            .onFalse(robotContainer.shooter.setVoltage(States.VoltageOFF.baseUnitMagnitude()));

        buttonX
            .whileTrue(robotContainer.shooter.setVelocity(States.VelocityON.baseUnitMagnitude()))
            .onFalse(robotContainer.shooter.setVelocity(States.VoltageOFF.baseUnitMagnitude()));

        buttonA
            .whileTrue(robotContainer.arm.setPosition(ArmConstants.States.Position1));
        
        buttonB
            .whileTrue(robotContainer.arm.setPosition(ArmConstants.States.Position2));
        
        

    }

}
