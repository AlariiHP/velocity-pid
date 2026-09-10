package frc.robot.subsystems.arm;

import com.ctre.phoenix6.configs.CANcoderConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.ctre.phoenix6.configs.FeedbackConfigs;
import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.Slot0Configs;
import com.ctre.phoenix6.configs.SlotConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.VoltageConfigs;
import com.ctre.phoenix6.signals.FeedbackSensorSourceValue;
import com.ctre.phoenix6.signals.InvertedValue;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.signals.SensorDirectionValue;

public class ArmConstants {

    protected class Control {

            protected static double kI = 0.0;
            protected static double kD = 0.0;
            protected static double kP = 0.0;
            protected static double kV = 0.0;
            
            protected static double AcceptedError = 0.002;            
            protected static double TimeOut = 1; 
            protected static double OffSet = 0.0; 
            protected static double SensorToMechanismRatio = 1.0;
            protected static double RotorToSensorRatio = 0.90625;

        }

    public class States {
            public static double Position1 = 2.5;
            public static double Position2 = 0.0;

        }

    public static int motorCanId = 22;
    public static int CCCanId = 21;

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
                new Slot0Configs().withKP(Control.kP).withKV(Control.kV))
            .withFeedback(
                new FeedbackConfigs()
                    .withFeedbackSensorSource(FeedbackSensorSourceValue.FusedCANcoder)
                    .withFeedbackRemoteSensorID(CCCanId)
                    .withSensorToMechanismRatio(Control.SensorToMechanismRatio)
                    .withRotorToSensorRatio(Control.RotorToSensorRatio)
            );
    }

    public static CANcoderConfiguration CCConfig(){
        CANcoderConfiguration CCConfig = new CANcoderConfiguration();
        CCConfig.MagnetSensor.SensorDirection = SensorDirectionValue.CounterClockwise_Positive;
        CCConfig.MagnetSensor.withMagnetOffset(Control.OffSet);
        return CCConfig;

    }

    
}