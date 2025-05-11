package frc.robot.subsystems.elevator;

import static frc.robot.util.motors.SparkUtil.*;
import java.util.function.DoubleSupplier;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkMax;

import edu.wpi.first.math.filter.Debouncer;
import frc.robot.subsystems.elevator.ElevatorConstants.Motor;

public class ElevatorIONeo implements ElevatorIO {
  private final SparkMax mElevatorNeo;
  private final RelativeEncoder mElevatorEncoder;

  private final Debouncer elevatorConnectedDebounce = new Debouncer(0.5);


  public ElevatorIONeo() {
    mElevatorNeo = new SparkMax(Motor.kMotorID, Motor.kMotorType);
    mElevatorEncoder = mElevatorNeo.getEncoder();


    this.mElevatorNeo.configure(
        ElevatorConstants.kElevatorConfig,
        ResetMode.kResetSafeParameters,
        PersistMode.kPersistParameters);
  }

  @Override
  public void updateInputs(ElevatorIOInputs inputs) {
    sparkStickyFault = false;
    ifOk(mElevatorNeo, mElevatorEncoder::getPosition, (value) -> inputs.elevatorPosition = value);
    ifOk(mElevatorNeo, mElevatorEncoder::getVelocity, (value) -> inputs.elevatorVelocity = value);
    ifOk(
        mElevatorNeo,
        new DoubleSupplier[] {mElevatorNeo::getAppliedOutput, mElevatorNeo::getBusVoltage},
        (values) -> inputs.elevatorAppliedVolts = values[0] * values[1]);
    ifOk(mElevatorNeo, mElevatorNeo::getOutputCurrent, (value) -> inputs.elevatorCurrentAmps = value);
    inputs.elevatorConnected = elevatorConnectedDebounce.calculate(!sparkStickyFault);
  }

  @Override
  public void setElevatorOpenLoop(double output) {
    mElevatorNeo.set(output);
  }



}
