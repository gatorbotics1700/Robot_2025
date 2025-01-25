package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.Constants;
import edu.wpi.first.hal.can.CANJNI;
import edu.wpi.first.hal.can.CANStatus;

public class BlinkinLEDController extends SubsystemBase{
    public enum BlinkinPattern {
        //RAINBOW_PARTY_PALETTE(-0.97),
        RED_ORANGE(+0.62), //good
        LIME(+0.73), //good
        PURPLE(+0.92); //test
        public final double value;
        private BlinkinPattern(double value) {
          this.value = value;
        }
  };

 // private static BlinkinLEDController m_controller = null;
  private Spark m_blinkin;
  //private BlinkinPattern m_currentPattern;

  public BlinkinLEDController(int port) {
    m_blinkin = new Spark(port);
}

public void setPattern(BlinkinPattern pattern) {
    System.out.println("Setting LED pattern to: " + pattern.value);
    m_blinkin.set(pattern.value);
}

}