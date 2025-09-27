package frc.robot;

import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.CoralShooterCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.commands.PointToTagCommand;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.geometry.Pose2d;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    
    private static double visionAlignOffset = 0.0;
    private final XboxController controller = new XboxController(0);
    
    private final GenericHID buttonBoard1A = new GenericHID(1);
    private final GenericHID buttonBoard1B = new GenericHID(2);

    private final XboxController codriver = new XboxController(3);

    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem("limelight", Constants.LIMELIGHT_OFFSETS);
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();

    private final SendableChooser<Command> autoChooser;

    private final Trigger Q1LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(1));
    private final Trigger Q1RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(2));

    private final Trigger Q2LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(2));
    private final Trigger Q2RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(1));

    private final Trigger Q3LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(4));
    private final Trigger Q3RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(3));

    private final Trigger Q4LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(6));
    private final Trigger Q4RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(5));

    private final Trigger Q5LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(5));
    private final Trigger Q5RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(6));

    private final Trigger Q6LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(3));
    private final Trigger Q6RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(4));

    private void incrementVisionAlign(){
        visionAlignOffset += 0.01;
    }

    private void decrementVisionAlign(){
        visionAlignOffset -= 0.01;
    }

    public RobotContainer() {
        NamedCommands.registerCommand("Score L4", new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_VOLTAGE)); //Constants.CORAL_L4_SHOOTING_SPEED));
        NamedCommands.registerCommand("Intake", new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_VOLTAGE));
        // NamedCommands.registerCommand("Score Trough", new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_TROUGH_SHOOTING_VOLTAGE)); //Constants.CORAL_TROUGH_SHOOTING_SPEED));

        NamedCommands.registerCommand("Q1 Left Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
        NamedCommands.registerCommand("Q1 Right Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
        NamedCommands.registerCommand("Q2 Left Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
        NamedCommands.registerCommand("Q2 Right Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
        NamedCommands.registerCommand("Q3 Left Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
        NamedCommands.registerCommand("Q3 Right Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
        NamedCommands.registerCommand("Q5 Left Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
        NamedCommands.registerCommand("Q5 Right Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
        NamedCommands.registerCommand("Q6 Left Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
        NamedCommands.registerCommand("Q6 Right Lineup", new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));

        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        // new Trigger(controller::getBackButtonPressed)
        //         .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        new Trigger(controller::getAButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 7, controller, Constants.INTAKE_ALIGN_OFFSET));

        new Trigger(controller::getLeftBumperButtonPressed)
            .onTrue(new InstantCommand(drivetrainSubsystem::toggleRobotRelativeDrive));

        new Trigger(controller::getBButtonPressed)
            .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));
        // new Trigger(controller::getXButtonPressed)
        //     .onTrue(new InstantCommand(this::decrementVisionAlign)/*new InstantCommand(m_coralShooterSub::decreaseVoltageTune)*/);

        // new Trigger(controller::getBButtonPressed)
        //     .onTrue(new InstantCommand(this::incrementVisionAlign)/*new InstantCommand(m_coralShooterSub::increaseVoltageTune)*/);

 /* CO-DRIVER BUTTON BOARD 1 BUTTONS */
            Q1LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())/*Constants.SHOOTING_L4_LEFT_OFFSET*/));
    
            Q1RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
            Q2LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())));
    
            Q2RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem,4, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
            Q3LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())));
    
            Q3RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
            Q4LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())));
    
            Q4RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
            Q5LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())));
    
            Q5RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
            Q6LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, new Pose2d(Constants.SHOOTING_L4_LEFT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_LEFT_OFFSET.getY(), Constants.SHOOTING_L4_LEFT_OFFSET.getRotation())));
    
            Q6RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, new Pose2d(Constants.SHOOTING_L4_RIGHT_OFFSET.getX() + visionAlignOffset, Constants.SHOOTING_L4_RIGHT_OFFSET.getY(), Constants.SHOOTING_L4_RIGHT_OFFSET.getRotation())));
    
    
    
            /* */
    
            
    /* CO-DRIVER BUTTON BOARD 2 BUTTONS */

        // climb
        new Trigger(codriver::getRightBumperButtonPressed)
            .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED)); // TODO: FYI the sign has been changed
            
        // detach or unwinch
        new Trigger(codriver::getLeftBumperButtonPressed)
           .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));

        // vomit (y)
        new Trigger(codriver::getYButtonPressed)
            .onTrue(VomitAndIntake(m_coralShooterSub)); 

        // stop (x)
        new Trigger(codriver::getXButtonPressed)
            .onTrue(MechStop());

        // score L4 (a)
        new Trigger(codriver::getAButtonPressed)
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_VOLTAGE + m_coralShooterSub.getVoltageTune()));

        // intake (added move up) (b)
        new Trigger(codriver::getBButtonPressed)
           .onTrue(MoveUpAndIntake(m_coralShooterSub)); //Constants.CORAL_INTAKING_SPEED));       
        
        boolean isCompetition = true;
        autoChooser = AutoBuilder.buildAutoChooserWithOptionsModifier(
        (stream) -> isCompetition
            ? stream.filter(auto -> (auto.getName().startsWith("Blue 1 Piece Mid to Q1") || auto.getName().startsWith("Red 1 Piece Mid to Q1") 
                            || auto.getName().startsWith("Blue 2P Mid") || auto.getName().startsWith("Blue 2P Left") || auto.getName().startsWith("Blue 2P Right") 
                            || auto.getName().startsWith("Red 2P Mid") || auto.getName().startsWith("Red 2P Left") || auto.getName().startsWith("Red 2P Right") 
                            || auto.getName().startsWith("Red Leave Left") || auto.getName().startsWith("Red Leave Right") 
                            || auto.getName().startsWith("Blue Leave Left") || auto.getName().startsWith("Blue Leave Right")))
            : stream
    );

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
            return new AutoDriveCommand(drivetrainSubsystem);
        }
    }

    public void setDefaultTeleopCommand(){
        System.out.println("SETTING DEFAULT TELEOP COMMAND");
        var alliance = DriverStation.getAlliance();
        if(alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red){
            drivetrainSubsystem.setDefaultCommand(
                new TeleopDriveCommand(
                    drivetrainSubsystem,
                    () -> modifyAxis(0.9*controller.getLeftY()),    // Changed to raw values
                    () -> modifyAxis(0.9*controller.getLeftX()),     // Changed to raw values
                    () -> -modifyAxis(0.8*controller.getRightX())    // Changed to raw values
                )
            );
        }else if(alliance.isPresent() && alliance.get() == DriverStation.Alliance.Blue){
            drivetrainSubsystem.setDefaultCommand(
                new TeleopDriveCommand(
                    drivetrainSubsystem,
                    () -> -modifyAxis(0.9*controller.getLeftY()),    // Changed to raw values
                    () -> -modifyAxis(0.9*controller.getLeftX()),     // Changed to raw values
                    () -> -modifyAxis(0.8*controller.getRightX())    // Changed to raw values
                )
            );
        }
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

    //vomit command
     public static Command VomitAndIntake(CoralShooterSubsystem coralShooterSubsystem){
        System.out.println("VOMITING CORAL AND INTAKING");
        return new CoralShooterCommand(coralShooterSubsystem, 0)
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_VOMIT_VOLTAGE)) //Constants.CORAL_VOMIT_SPEED))
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_INTAKING_VOLTAGE)); //Constants.CORAL_INTAKING_SPEED));
    }

    //new move up and then intake
    public static Command MoveUpAndIntake(CoralShooterSubsystem coralShooterSubsystem){
        System.out.println("INTAKING");
        return new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_INTAKING_VOLTAGE) //intake
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_PREINTAKE_SHOOTING_VOLTAGE)); //move up
    }

    // mech stop command
    public static Command MechStop(){
        System.out.println("ALL MECH STOP!!");
        return new CoralShooterCommand(m_coralShooterSub, 0)
        .alongWith(new ClimbingCommand(m_climbingSub, 0));
    }

    public static Command getMechStopCommand(){
        return MechStop();
    }

    public static Command scoreTrough(CoralShooterSubsystem coralShooterSubsystem){
        System.out.println("SCORING TROUGH");
        return new CoralShooterCommand(coralShooterSubsystem, 0.5);
    }
    // ^ DELETE UNLESS ANY OTHER COMMANDS ARE NEEDED
}
