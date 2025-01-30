package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.motorcontrol.PWMVictorSPX;

public class IntakeSubsystem {

    private final MotorController m_intakeMotor;
    private final BlinkinLEDController m_ledController;

    public IntakeSubsystem() {
        m_intakeMotor = new PWMVictorSPX(1);
        m_ledController = BlinkinLEDController.getInstance();
    }

    public void runIntake(double speed) {
        m_intakeMotor.set(speed);

        if (speed > 0) {
            m_ledController.setIntakeActiveColor();
        } else {
            m_ledController.setIntakeIdleColor();
        }
    }

    public void stopIntake() {
        runIntake(0.0);
    }
    
}
