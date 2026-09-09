package frc.robot.subsystems.shooter;

import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;

public class ShooterConstants {
   
    protected class Control{

            protected static double kI = 0.0;
            protected static double kD = 0.0;
            protected static double kP = 0.6;
            protected static double kV = 0.12;
            
            protected static double acceptedError = 1;
        }

    public class States {
            public static double VoltageOFF = 0.0;
            public static double VoltageON = 4.0;
            public static double VelocityOFF = 0.0;
            public static double VelocityON = 45.0;


        }

    public static int canId = 22;

    public static TalonFXConfiguration motorConfig() {
        return new TalonFXConfiguration()
            .withCurrentLimits(
                new CurrentLimitsConfigs()
                    .withStatorCurrentLimitEnable(true)
                    .withStatorCurrentLimit(120)
                    .withSupplyCurrentLimitEnable(true)
                    .withStatorCurrentLimit(40)
                    .withSupplyCurrentLowerLimit(40)
                    .withSupplyCurrentLowerTime(0.05))
            .withVoltage(
                new VoltageConfigs().withPeakForwardVoltage(12).withPeakReverseVoltage(-12))
            .withMotorOutput(
                new MotorOutputConfigs()
                    .withInverted(InvertedValue.CounterClockwise_Positive)
                    .withNeutralMode(NeutralModeValue.Brake))
            .withSlot0( 
                new Slot0Configs().withKP(Control.kP).withKV(Control.kV));
    }
}
