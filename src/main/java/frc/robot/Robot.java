package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.BlinkinLEDController;

public class Robot extends TimedRobot {
    private BlinkinLEDController ledController;

    @Override
    public void robotInit() {
        // Initialize the LED controller on PWM port 0
        ledController = new BlinkinLEDController(0);
    }

    @Override
    public void teleopInit() {
        System.out.println("Setting LEDs to LIME...");
        ledController.setPattern(BlinkinLEDController.BlinkinPattern.LIME);
    }
}