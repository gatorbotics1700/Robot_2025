package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;

public class BlinkinLEDController extends SubsystemBase {
    public enum BlinkinPattern {
        RED_ORANGE(+0.62),
        LIME(+0.73),
        PURPLE(+0.92);

        public final double value;

        BlinkinPattern(double value) {
            this.value = value;
        }
    }

    private final Spark m_blinkin;

    public BlinkinLEDController(int port) {
        System.out.println("Initializing BlinkinLEDController on port: " + port);
        m_blinkin = new Spark(port);
    }

    public void setPattern(BlinkinPattern pattern) {
        System.out.println("Setting LED pattern to: " + pattern.name() + " (" + pattern.value + ")");
        m_blinkin.set(pattern.value);
    }
}