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
        ledController = BlinkinLEDController.getInstance();
    }

    @Override
    public void teleopPeriodic() {

        long currentTime = System.currentTimeMillis();

        if (currentTime - lastToggleTime >= 2000) {

            if (isActiveColor) {
                ledController.setPink();
            } else {
                ledController.setBlue();
            }
            isActiveColor = !isActiveColor;
            lastToggleTime = currentTime;
        }
    }

}