package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase{
    
    private final TalonFX motor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    public ClimbingSubsystem() {
        motor = new TalonFX(Constants.CLIMBING_MOTOR_CAN_ID, Constants.CANIVORE_BUS_NAME);
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    public void setSpeed(double speed) {
        System.out.println("Setting climbing speed");
        motor.setControl(dutyCycleOut.withOutput(speed));
         
    }
}
