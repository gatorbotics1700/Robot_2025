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
        System.out.println("TELEOP**** Setting LEDs to LIME...");
        ledController.setPattern(0.73);
    }
    @Override
    public void autonomousInit() {
        ledController.lightsGreen();
        System.out.println("AUTO**** LEDs set to green (LIME)");
    }

    @Override
    public void disabledInit() {
        // Set LEDs to white when disabled
        ledController.setPattern(0.87);
        System.out.println("LEDs set to blue (normal)");
    }

}