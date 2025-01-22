package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class MotorSubsystem extends SubsystemBase {
    private final TalonFX motor; 

    public MotorSubsystem() {
        motor = new TalonFX(30, "CANivore Bus 1");
    }

    public void setSpeed(double speed) {
        DutyCycleOut dutyCycleOut = new DutyCycleOut(speed);
        motor.setControl(dutyCycleOut.withOutput(speed));
        System.out.println("*****spinning????");
    }

}
