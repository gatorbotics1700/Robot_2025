package frc.robot;

import frc.robot.commands.ClimbingCommand;
import frc.robot.commands.CoralShooterCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.commands.ScoreCommands;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.commands.TestDriveCommand;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.CoralShooterSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

import com.pathplanner.lib.auto.AutoBuilder;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import com.pathplanner.lib.auto.NamedCommands;

import edu.wpi.first.networktables.GenericEntry;
import edu.wpi.first.wpilibj.GenericHID;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem(Constants.LIMELIGHT_OFFSETS);
    private static final CoralShooterSubsystem m_coralShooterSub = new CoralShooterSubsystem();
    private static final ClimbingSubsystem m_climbingSub = new ClimbingSubsystem();

    private final SendableChooser<Command> autoChooser;

    private final XboxController controller = new XboxController(0);
    
    private final GenericHID buttonBoard1A = new GenericHID(1);
    private final GenericHID buttonBoard1B = new GenericHID(2);

    private final GenericHID buttonBoard2A = new GenericHID(3);
    private final GenericHID buttonBoard2B = new GenericHID(4);

    public RobotContainer() {
        NamedCommands.registerCommand("Score Trough", ScoreCommands.Level(1));
        NamedCommands.registerCommand("Score L2", ScoreCommands.Level(2));
        NamedCommands.registerCommand("Score L3", ScoreCommands.Level(3));
        NamedCommands.registerCommand("Score L4", ScoreCommands.Level(4));

        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

        //slow drive
        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));


 /* CO-DRIVER BUTTON BOARD 1 BUTTONS */

            new Trigger(()->buttonBoard1A.getRawButtonPressed(1))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            new Trigger(()->buttonBoard1A.getRawButtonPressed(2))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            new Trigger(()->buttonBoard1A.getRawButtonPressed(3))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            new Trigger(()->buttonBoard1A.getRawButtonPressed(4))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            new Trigger(()->buttonBoard1A.getRawButtonPressed(5))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            new Trigger(()->buttonBoard1A.getRawButtonPressed(6))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(1))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller,Constants.SHOOTING_L4_RIGHT_OFFSET ));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(2))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(3))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(4))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(5))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_RIGHT_OFFSET));
    
            new Trigger(()->buttonBoard1B.getRawButtonPressed(6))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.SHOOTING_L4_LEFT_OFFSET));
    

            
/* CO-DRIVER BUTTON BOARD 2 BUTTONS */
    
            // lining up to with reef to score trough
            new Trigger(()->buttonBoard2B.getRawButtonPressed(3))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
            
            new Trigger(()->buttonBoard2B.getRawButtonPressed(4))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 4, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
    
            new Trigger(()->buttonBoard2B.getRawButtonPressed(5))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 3, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
    
            new Trigger(()->buttonBoard2B.getRawButtonPressed(6))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 2, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
    
            new Trigger(()->buttonBoard2A.getRawButtonPressed(3))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 1, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
    
            new Trigger(()->buttonBoard2A.getRawButtonPressed(2))
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 5, controller, Constants.FRONT_CENTER_ALIGN_OFFSET));
        
             // lining up to intake
            // new Trigger(()->buttonBoard2B.getRawButtonPressed(2))
            //     .onTrue(new LimelightControlCommand(m_limelightsub, drivetrainSubsystem, 8, controller));



            // climb
            new Trigger(()->buttonBoard2A.getRawButtonPressed(1))
                .onTrue(new ClimbingCommand(m_climbingSub, Constants.CLIMBING_SPEED));
                
            
            // detach
            new Trigger(()->buttonBoard2B.getRawButtonPressed(2))
                .onTrue(new ClimbingCommand(m_climbingSub, -Constants.CLIMBING_SPEED));
            //vomit
            new Trigger(()->buttonBoard2B.getRawButtonPressed(7))
            .onTrue(VomitAndIntake(m_coralShooterSub)); 

            new Trigger(()->buttonBoard2B.getRawButtonPressed(9))
                .onTrue(new CoralShooterCommand(m_coralShooterSub, 0)
                .alongWith(new ClimbingCommand(m_climbingSub, 0)));

            // score trough
            new Trigger(()->buttonBoard2B.getRawButtonPressed(8))
                .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_TROUGH_SHOOTING_SPEED));

            // score L4
            new Trigger(()->buttonBoard2A.getRawButtonPressed(4))
                .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_L4_SHOOTING_SPEED));

            // intake
            new Trigger(()->buttonBoard2A.getRawButtonPressed(5))
                .onTrue(new CoralShooterCommand(m_coralShooterSub, Constants.CORAL_INTAKING_SPEED));        
    
        
        boolean isCompetition = true;
        autoChooser = AutoBuilder.buildAutoChooserWithOptionsModifier(
        (stream) -> isCompetition
            ? stream.filter(auto -> (auto.getName().startsWith("Blue") || auto.getName().startsWith("Red")))
            :stream
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

    //vomit command
     public static Command VomitAndIntake(CoralShooterSubsystem coralShooterSubsystem){
        System.out.println("VOMITING CORAL AND INTAKING");
        return new CoralShooterCommand(coralShooterSubsystem, 0)
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_VOMIT_SPEED))
        .andThen(new CoralShooterCommand(coralShooterSubsystem, Constants.CORAL_INTAKING_SPEED));
    }
}
