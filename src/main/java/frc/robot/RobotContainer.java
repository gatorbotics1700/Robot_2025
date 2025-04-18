package frc.robot;

import frc.robot.commands.AutoDriveCommand;
import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.CoralShooterCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.PointToReefCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.commands.PointToTagCommand;
import frc.robot.commands.DriveBackwardsCommand;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;

import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.GenericHID;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    
    private static double visionAlignOffset = 0.0;
    private final XboxController controller = new XboxController(0);
    
    private final GenericHID buttonBoard1A = new GenericHID(1);
    private final GenericHID buttonBoard1B = new GenericHID(2);

    private final GenericHID buttonBoard2A = new GenericHID(3);
    private final GenericHID buttonBoard2B = new GenericHID(4);
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem("limelight", Constants.LIMELIGHT_OFFSETS);
//    private static final LimelightSubsystem m_limelightsub2 = new LimelightSubsystem("limelight-two", Constants.LIMELIGHT_OFFSETS_2);
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();

    private final SendableChooser<Command> autoChooser;

    private final Trigger Q1LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(1));
    private final Trigger Q1RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(2));
    private final Trigger Q1PointToTag = new Trigger(()->buttonBoard2B.getRawButtonPressed(3));

    private final Trigger Q2LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(2));
    private final Trigger Q2RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(1));
    private final Trigger Q2PointToTag = new Trigger(()->buttonBoard2B.getRawButtonPressed(4));

    private final Trigger Q3LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(4));
    private final Trigger Q3RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(3));
    private final Trigger Q3PointToTag = new Trigger(()->buttonBoard2B.getRawButtonPressed(5));

    private final Trigger Q4LeftLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(6));
    private final Trigger Q4RightLineup = new Trigger(()->buttonBoard1B.getRawButtonPressed(5));
    private final Trigger Q4PointToTag = new Trigger(()->buttonBoard2B.getRawButtonPressed(6));

    private final Trigger Q5LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(5));
    private final Trigger Q5RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(6));
    private final Trigger Q5PointToTag = new Trigger(()->buttonBoard2A.getRawButtonPressed(3));

    private final Trigger Q6LeftLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(3));
    private final Trigger Q6RightLineup = new Trigger(()->buttonBoard1A.getRawButtonPressed(4));
    private final Trigger Q6PointToTag = new Trigger(()->buttonBoard2A.getRawButtonPressed(2));

    private final Trigger climb = new Trigger(()->buttonBoard2A.getRawButtonPressed(1));
    private final Trigger detach = new Trigger(()->buttonBoard2B.getRawButtonPressed(2));
    private final Trigger intakeLineup = new Trigger(()->buttonBoard2B.getRawButtonPressed(1));
    private final Trigger vomit = new Trigger(()->buttonBoard2B.getRawButtonPressed(7));
    private final Trigger mechStop = new Trigger(()->buttonBoard2A.getRawButtonPressed(8));
    private final Trigger scoreTrough = new Trigger(()->buttonBoard2B.getRawButtonPressed(8));
    private final Trigger scoreL4 = new Trigger(()->buttonBoard2A.getRawButtonPressed(4));
    private final Trigger intake = new Trigger(()->buttonBoard2A.getRawButtonPressed(5));

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
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        new Trigger(controller::getAButtonPressed)
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 7, controller, Constants.INTAKE_ALIGN_OFFSET));

        // new Trigger(controller::getLeftBumperButtonPressed)
        //     .onTrue(new RunCommand(()->drivetrainSubsystem.robotRelativeHeading(180)));

        new Trigger(controller::getLeftBumperButtonPressed)
            .onTrue(new InstantCommand(drivetrainSubsystem::toggleRobotRelativeDrive));

        new Trigger(controller::getXButtonPressed)
            .onTrue(new InstantCommand(m_coralShooterSub::decreaseVoltageTune));

        new Trigger(controller::getBButtonPressed)
            .onTrue(new InstantCommand(m_coralShooterSub::increaseVoltageTune));

        // new Trigger(controller::getYButton)
        //     .onTrue(new DriveBackwardsCommand(drivetrainSubsystem, controller));

 /* CO-DRIVER BUTTON BOARD 1 BUTTONS */

            Q1LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            Q1RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            Q2LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            Q2RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem,4, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            Q3LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            Q3RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            Q4LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller,Constants.SHOOTING_L4_LEFT_OFFSET ));
    
            Q4RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            Q5LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            Q5RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            Q6LeftLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            Q6RightLineup
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
    
    
            /* */
    
            
    /* CO-DRIVER BUTTON BOARD 2 BUTTONS */

        // climb
        climb
            .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED)); // TODO: FYI the sign has been changed
            
        // detach or unwinch
        detach
           .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));

        // lining up to intake
        intakeLineup
            .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 7, controller, Constants.INTAKE_ALIGN_OFFSET));
            // .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 7, controller, Constants.INTAKE_ALIGN_OFFSET));

        // lining up to with reef to score trough
        Q1PointToTag //q1
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 1));
        
        Q2PointToTag //q2
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 2));

        Q3PointToTag //q3
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 3));

        Q4PointToTag //q4
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 4));

        Q5PointToTag //q5
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 5));

        Q6PointToTag //q6
            .onTrue(new PointToTagCommand(drivetrainSubsystem, controller, 6));

        // vomit
        vomit
            .onTrue(VomitAndIntake(m_coralShooterSub)); 

        // stop
        mechStop
            .onTrue(MechStop());

        // score trough
        scoreTrough
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_TROUGH_SHOOTING_VOLTAGE + m_coralShooterSub.getVoltageTune()));
            // .andThen(new DriveBackwardsCommand(drivetrainSubsystem, controller))); //Constants.CORAL_TROUGH_SHOOTING_SPEED));

        // score L4
        scoreL4
            .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_VOLTAGE + m_coralShooterSub.getVoltageTune()));
            // .andThen(new DriveBackwardsCommand(drivetrainSubsystem, controller))); //Constants.CORAL_L4_SHOOTING_SPEED));

        // intake (added move up)
        intake
            // .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_VOLTAGE));
           .onTrue(MoveUpAndIntake(m_coralShooterSub)); //Constants.CORAL_INTAKING_SPEED));       
        
        boolean isCompetition = true;
        autoChooser = AutoBuilder.buildAutoChooserWithOptionsModifier(
        (stream) -> isCompetition
            // ? stream.filter(auto -> (auto.getName().startsWith("Blue") || auto.getName().startsWith("Red")))
            //WITHOUT LEAVE AUTOS
            // ? stream.filter(auto -> (auto.getName().startsWith("Blue 1 Piece Mid to Q1") || auto.getName().startsWith("Red 1 Piece Mid to Q1") || auto.getName().startsWith("Blue 2P") || auto.getName().startsWith("Red 2P")))
            //WITH LEAVE AUTOS
            ? stream.filter(auto -> (auto.getName().startsWith("Blue 1 Piece Mid to Q1") || auto.getName().startsWith("Red 1 Piece Mid to Q1") 
                            || auto.getName().startsWith("Blue 2P Mid") || auto.getName().startsWith("Blue 2P Left") || auto.getName().startsWith("Blue 2P Right") 
                            || auto.getName().startsWith("Red 2P Mid") || auto.getName().startsWith("Red 2P Left") || auto.getName().startsWith("Red 2P Right") 
                            || auto.getName().startsWith("Red Leave Left") || auto.getName().startsWith("Red Leave Right") 
                            || auto.getName().startsWith("Blue Leave Left") || auto.getName().startsWith("Blue Leave Right")))
            //WITH LEAVE AND SIDE 1 PIECE AUTOS
            // ? stream.filter(auto -> (auto.getName().startsWith("Blue 1 Piece Mid to Q1") || auto.getName().startsWith("Red 1 Piece Mid to Q1") 
            //                 || auto.getName().startsWith("Blue 2P Mid") || auto.getName().startsWith("Blue 2P Left") || auto.getName().startsWith("Blue 2P Right") 
            //                 || auto.getName().startsWith("Red 2P Mid") || auto.getName().startsWith("Red 2P Left") || auto.getName().startsWith("Red 2P Right") 
            //                 || auto.getName().startsWith("Red Leave Left") || auto.getName().startsWith("Red Leave Right") 
            //                 || auto.getName().startsWith("Blue Leave Left") || auto.getName().startsWith("Blue Leave Right")
            //                 || auto.getName().startsWith("Red 1 Piece Right to Q2") || auto.getName().startsWith("Red 1 Piece Left to Q6")
            //                 || auto.getName().startsWith("Blue 1 Piece Right to Q2") || auto.getName().startsWith("Blue 1 Piece Left to Q6")))
            : stream
    );

        SmartDashboard.putData("Auto Chooser", autoChooser);
        // Shuffleboard.getTab("SmartDashboard").add(autoChooser);
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
                    () -> modifyAxis(0.9*controller.getRightY()),    // Changed to raw values
                    () -> modifyAxis(0.9*controller.getRightX()),     // Changed to raw values
                    () -> -modifyAxis(0.8*controller.getLeftX())    // Changed to raw values
                )
            );
        }else if(alliance.isPresent() && alliance.get() == DriverStation.Alliance.Blue){
            drivetrainSubsystem.setDefaultCommand(
                new TeleopDriveCommand(
                    drivetrainSubsystem,
                    () -> -modifyAxis(0.9*controller.getRightY()),    // Changed to raw values
                    () -> -modifyAxis(0.9*controller.getRightX()),     // Changed to raw values
                    () -> -modifyAxis(0.8*controller.getLeftX())    // Changed to raw values
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
