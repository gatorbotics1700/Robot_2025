package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.controls.PositionVoltage;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.math.controller.ElevatorFeedforward;
import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.controller.ProfiledPIDController;
import edu.wpi.first.math.trajectory.TrapezoidProfile;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

//import com.revrobotics.ColorMatch;
//import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.simulation.ElevatorSim;
import edu.wpi.first.wpilibj.util.Color;

public class ElevatorSubsystem extends SubsystemBase {
    private TalonFX motor;
    private static DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    //private static PositionVoltage positionVoltage = new PositionVoltage(0);
    
    // private final DigitalInput colorSensor;
    private final DigitalInput topLimitSwitch;
    private final DigitalInput bottomLimitSwitch;

    // TODO: test and change these values
    
    private static double kDt = 0.02;
    private static double kMaxVelocity = 1.75;
    private static double kMaxAcceleration = 0.75;
    private static final double kP = 0.0005;
    private static final double kI = 0.0;
    private static final double kD = 0.00002;
    private static final double kS = 1.1;
    private static final double kG = 1.2;
    private static final double kV = 1.3;

    private static final double DEADBAND = 0.5 * Constants.ELEVATOR_TICKS_PER_INCH; // 1 inch in ticks; TODO: test and change


    private final TrapezoidProfile.Constraints elevatorConstraints;
    private final ProfiledPIDController elevatorPIDController;
    private final ElevatorFeedforward elevatorFeedforward;

    public ElevatorSubsystem(){
        motor = new TalonFX(Constants.ELEVATOR_CAN_ID);
        motor.setNeutralMode(NeutralModeValue.Brake);
        // colorSensor = new DigitalInput(0);
        topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH_PORT);
        bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH_PORT);
        elevatorConstraints = new TrapezoidProfile.Constraints(kMaxVelocity, kMaxAcceleration);
        elevatorPIDController = new ProfiledPIDController(kP, kI, kD, elevatorConstraints);
        elevatorFeedforward = new ElevatorFeedforward(kS, kG, kV);
    }

    public void setPosition(double desiredTicks){
        double currentTicks = getCurrentTicks(); 
        double error = desiredTicks - currentTicks;
        if(Math.abs(error) > DEADBAND){
            double targetVelocity = elevatorPIDController.getSetpoint().velocity;
            double feedforwardVoltage = elevatorFeedforward.calculate(targetVelocity);
            double pidCorrectedVoltage = elevatorPIDController.calculate(error);
            motor.setControl(dutyCycleOut.withOutput(feedforwardVoltage + pidCorrectedVoltage));


            //elevatorMotor.setControl(positionVoltage.withPosition(desiredTicks));
        }else{
            motor.setControl(dutyCycleOut.withOutput(0));
        }
    }

    public void setSpeed(double speed){
        motor.setControl(dutyCycleOut.withOutput(0));
    }

    public double getCurrentTicks(){ //getPosition() is in rotations so rotations * ticks per rev should give position in ticks
        return (motor.getPosition().getValueAsDouble() * Constants.KRAKEN_TICKS_PER_REV);
    }

    public double determineInchesToTicks(double desiredInches){
        return desiredInches * Constants.ELEVATOR_TICKS_PER_INCH;
    }

    // public boolean isColorSensed(){
    //     return colorSensor.get();
    // }

    // not sure if these should be inverted or not
    public boolean atTopLimitSwitch(){
        return topLimitSwitch.get();
    }

    public boolean atBottomLimitSwitch(){
        return bottomLimitSwitch.get();
    }
}
