package frc.robot.subsystems.elevator;

import org.littletonrobotics.junction.AutoLog;

public interface ElevatorIO {
  @AutoLog
  public static class ElevatorIOInputs {
    public boolean elevatorEncoderConnected = false;
    public boolean elevatorConnected = false;
    public double elevatorPosition = 0.0;
    public double elevatorVelocity = 0.0;
    public double elevatorAppliedVolts = 0.0;
    public double elevatorCurrentAmps = 0.0;
  }

  public default void updateInputs(ElevatorIOInputs inputs) {}

  public default void setElevatorOpenLoop(double output){}

  public default void setElevatorVelocity(double velocityRadPerSec){}
}
