package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;


public class ShooterIO {

    protected final TalonFX shooterMotor;
    protected int motorCanID = 22;

    public static CANBus canbus = new CANBus("rio");

    protected final VoltageOut voltageRequest = new VoltageOut(0.0);

    public ShooterIO() {
        shooterMotor = new TalonFX(motorCanID, canbus);
        shooterMotor.getConfigurator().apply(new TalonFXConfiguration());
    }

    public void setVoltage(double volts){
        shooterMotor.setControl(voltageRequest.withOutput(volts));
    }
}
