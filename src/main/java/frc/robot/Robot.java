package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.cscore.CvSink;
import edu.wpi.first.cscore.CvSource;
import edu.wpi.first.cscore.UsbCamera;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.util.PixelFormat;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.commands.TestCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.cscore.MjpegServer;

public class Robot extends TimedRobot {
    private Command m_autonomousCommand;
    private Command mechStopCommand;
    private RobotContainer container;
    private ShuffleboardTab visionTesting;

    public Robot() {
       CameraServer.startAutomaticCapture();
       CvSink cvSink = CameraServer.getVideo();
       CvSource outputStream = new CvSource("Blur", PixelFormat.kMJPEG, 640, 480, 70);
    }

    @Override
    public void robotInit() {
        container = new RobotContainer();
        SmartDashboard.putData(CommandScheduler.getInstance());
        visionTesting = Shuffleboard.getTab("Vision Testing");
        //SmartDashboard.putBoolean("Is FD?", DrivetrainSubsystem.isFD);
        
        // shuffleboardTest = Shuffleboard.getTab("Shuffleboard Test");
        // shuffleboardTester = shuffleboardTest.add("shuffleboard tester", 0).getEntry();

        mechStopCommand = container.getMechStopCommand();
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();

    //     double tester = shuffleboardTester.getDouble(0);
    //     System.out.println("TESTER VALUE: " + tester);
    }

    @Override
    public void autonomousInit() {
        m_autonomousCommand = container.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }

    }   

    @Override
    public void teleopInit() {
        
        container.setDefaultTeleopCommand();
        mechStopCommand.schedule();


        // This makes sure that the autonomous stops running when teleop starts
        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
        }
        // container.setDefaultTeleopCommand();
    }

    @Override
    public void teleopPeriodic() {
        // Leave empty - default command will handle teleop
    }

    @Override
    public void testInit(){
        CommandScheduler.getInstance().schedule(new TestCommand(container.getDrivetrainSubsystem(), new Pose2d(1,0,new Rotation2d(0))));
    }

    @Override
    public void testPeriodic(){

    }
}
