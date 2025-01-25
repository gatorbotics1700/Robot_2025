package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.BlinkinLEDController;
import edu.wpi.first.wpilibj2.command.Command;

public class Robot extends TimedRobot {
    private Command m_teleopColor;
    private Command m_teleopCommand1;
    private Command m_autoColor;

    private RobotContainer m_robotContainer;
    private BlinkinLEDController ledController;


    @Override
    public void robotInit() {
        // Initialize RobotContainer
        m_robotContainer = new RobotContainer();
        ledController = new BlinkinLEDController(0);

    }

    @Override
    public void robotPeriodic() {
      CommandScheduler.getInstance().run();
      
    }

    @Override
    public void teleopInit() {
        ledController.setPattern(BlinkinLEDController.BlinkinPattern.LIME);

        // m_teleopColor = m_robotContainer.getTeleopColor();

        // if (m_teleopColor != null) {
        //     m_teleopColor.schedule();
        //     System.out.println("Scheduled teleopColor command to set LEDs to green.");

        // }


    }

    

    @Override
    public void teleopPeriodic() {
    
        // if (m_teleopColor != null) {
        //     m_teleopColor.schedule();
        //     System.out.println("Scheduled teleopColor command to set LEDs to green.");

        // }
    }


    @Override
    public void autonomousInit(){
      
    } 
       
    

    @Override
    public void autonomousPeriodic() {}

    @Override
    public void disabledInit() {}
  
    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {
      // Cancels all running commands at the start of test mode.
     // CommandScheduler.getInstance().cancelAll();
    }

    @Override
    public void testPeriodic() {}
}