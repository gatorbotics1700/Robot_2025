package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.subsystems.BlinkinLEDController;

public class Robot extends TimedRobot {
    private BlinkinLEDController ledController;
    private long lastToggleTime = 0;
    private boolean isActiveColor = true;

    @Override
    public void robotInit() {
        // Initialize the LED controller on PWM port 0
        ledController = new BlinkinLEDController();
    }

  /*   @Override
    public void teleopInit() {
      //  System.out.println("TELEOP**** Setting LEDs to LIME...");
        ledController.setPattern(0.57);
    } */

    @Override
    public void teleopPeriodic() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastToggleTime >= 1000) {

            if (isActiveColor) {
                ledController.setPattern(0.57);
            } else {
                ledController.setPattern(0.87);
            }
            isActiveColor = !isActiveColor;
            lastToggleTime = currentTime;
        }
    
    }

    

    @Override
    public void autonomousInit() {
        ledController.setPattern(0.87);
     //   System.out.println("AUTO**** LEDs set to green (lime)");
    }

    @Override
    public void autonomousPeriodic() {
        ledController.setPattern(0.3);
      //  System.out.println("AUTO**** LEDs set to green (lime)");
    }

    @Override
    public void disabledInit() {
        // Set LEDs to white when disabled
        ledController.setPattern(0.87);
     //   System.out.println("LEDs set to blue (normal)");
    }

    @Override
    public void testPeriodic() {
        ledController.setPattern(0.93);
    }

}