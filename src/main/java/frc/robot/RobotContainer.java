package frc.robot;

import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ScoreCommands;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.TestDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.pathplanner.lib.auto.NamedCommands;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    private final XboxController controller = new XboxController(0);
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();
    private final SendableChooser<Command> autoChooser;
    private final GenericHID buttonBoard = new GenericHID(1);

    public RobotContainer() {
        NamedCommands.registerCommand("Score Trough", ScoreCommands.Level(1));
        NamedCommands.registerCommand("Score L2", ScoreCommands.Level(2));
        NamedCommands.registerCommand("Score L3", ScoreCommands.Level(3));
        NamedCommands.registerCommand("Score L4", ScoreCommands.Level(4));
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        //pipeline buttons
        new Trigger(()->buttonBoard.getRawButtonPressed(1))
             .whileTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, buttonBoard));
        
        //new Trigger(controller::getAButtonPressed)
            //.onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller)); // id 6,19 A
        new Trigger(controller::getBButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, buttonBoard)); // id 7,18,10,21 B
        new Trigger(controller::getXButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, buttonBoard)); // id 8,17 X
        new Trigger(controller::getYButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, buttonBoard)); // id 9,22 Y

    autoChooser = AutoBuilder.buildAutoChooser();
        SmartDashboard.putData("Auto Chooser", autoChooser);
    }

    public Command getAutonomousCommand() {
        try {
            Command auto = autoChooser.getSelected();
            System.out.println("Auto loaded successfully: " + autoChooser.getSelected().getName());
            return auto;
        } catch (Exception e) {
            System.err.println("Failed to load auto path: " + e.getMessage());
            e.printStackTrace();
            return new TestDriveCommand(drivetrainSubsystem);
        }
    }


    public void setDefaultTeleopCommand(){
        System.out.println("SETTING DEFAULT TELEOP COMMAND");
        drivetrainSubsystem.setDefaultCommand(
            new TeleopDriveCommand(
                drivetrainSubsystem,
                () -> -modifyAxis(controller.getRightY()),    // Changed to raw values
                () -> -modifyAxis(controller.getRightX()),     // Changed to raw values
                () -> -modifyAxis(controller.getLeftX())    // Changed to raw values
            )
        );
    }

    public DrivetrainSubsystem getDrivetrainSubsystem(){
        return drivetrainSubsystem;
    }

    private double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    private double modifyAxis(double value) {
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        if(drivetrainSubsystem.getSlowDrive()){
            return (0.5 * value);
        }

        return value;
    }
}
