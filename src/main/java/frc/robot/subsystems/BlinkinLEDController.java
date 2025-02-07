package frc.robot.subsystems;

import java.util.Map;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInWidgets;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Constants;
//code came from this chief delphi post: https://www.chiefdelphi.com/t/rev-blinkin-example-code/452871/3

public class BlinkinLEDController {

  private static BlinkinLEDController m_controller = null;
  private Spark m_blinkin;
  private GenericEntry patternEntry;

  public BlinkinLEDController() {
    m_blinkin = new Spark(0);
    patternEntry = Shuffleboard.getTab("LEDs")
    .add("LED pattern", 0.0)
    .withWidget(BuiltInWidgets.kNumberBar)
    .withProperties(Map.of("min", -1.0, "max", 1.0))
    .getEntry();
  }

  public void setPattern(double pattern) {
    m_blinkin.set(pattern);
   patternEntry.setDouble(pattern);
   //patternEntry.
   System.out.println("Pattern set to: " + pattern);
  }

  public void setBlue() {
    setPattern(0.57); 
  }

  public void setPink() {
    setPattern(0.87); 
  }

  public static BlinkinLEDController getInstance() {
   if (m_controller == null) {
    m_controller = new BlinkinLEDController();
   }
   return m_controller;
  }
}