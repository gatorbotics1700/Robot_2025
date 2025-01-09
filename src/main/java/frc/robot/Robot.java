package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.LimelightSubsystem;


public class Robot extends TimedRobot {
    @SuppressWarnings("unused")
    private RobotContainer container;
    private Command m_limelightCommand;


    @Override
    
    public void robotInit(){
        container = new RobotContainer();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void teleopPeriodic() {



    }
}
