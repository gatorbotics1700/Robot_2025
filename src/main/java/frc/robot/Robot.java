package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private RobotContainer container;

    @Override
    public void robotInit() {
        container = new RobotContainer();
        SmartDashboard.putData(CommandScheduler.getInstance());
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    
}
