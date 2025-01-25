package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class BlinkinLEDController extends SubsystemBase {
    private final Spark blinkin;

    public BlinkinLEDController(int pwmPort) {
        blinkin = new Spark(pwmPort);
    }

    public void setPattern(double patternValue) {
        blinkin.set(patternValue);
    }

    public void lightsGreen() {
        blinkin.set(0.73); // LIME (greenish pattern)
    }
}