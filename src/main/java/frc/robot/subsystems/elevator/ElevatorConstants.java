package frc.robot.subsystems.elevator;

import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

public class ElevatorConstants {
  public class Motor {
    public static int kMotorID = 41;
    public static MotorType kMotorType = MotorType.kBrushless;
    public static IdleMode kIdleMode = IdleMode.kBrake;
    public static boolean kInverted = true;
    public static int kCurrentLimit = 80;
  }

  public class Encoder {
    public static double kPositionConversionFactor = 1.21875;
    public static double kVelocityConversionFactor = kPositionConversionFactor / 60.0;
  }
  
  public class Control {
    public static double kForwardSoftLimitIn = 80;
    public static double kReverseSoftLimitIn = 0;
    
    public static double kP = 1.5;
    public static double kI = 0.0;
    public static double kD = 0.08;

    public static double kMaxAcceleration = 250;
    public static double kMaxVelocity = 500;
    public static double kToleranceIn = 5;

    public static double kS = 0.0;
    public static double kG = 0.9;
    public static double kV = 0.0;
    public static double kA = 0.0;

    // Inches
    public enum Positions {
      BOTTOM(0),
      PRE_INTAKE(30),
      POST_INTAKE(16),
      L1(20),
      L2(27.5),
      L3(45),
      L4(75),
      SCORE(20),
      BARGE(80),
      L2ALGAE(44.5),
      L3ALGAE(67.5),
      HOLD_ALGAE(7);
  
      public final double setpoint;
      
      private Positions(double setpoint) {
        this.setpoint = setpoint;
      }
      
      public double getPosIn() {
        return this.setpoint;
      }
    };
  }
  
  public static final SparkMaxConfig kElevatorConfig = new SparkMaxConfig();

  static {
    kElevatorConfig.idleMode(Motor.kIdleMode).smartCurrentLimit(Motor.kCurrentLimit).inverted(Motor.kInverted);
  }
}
