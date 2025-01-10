package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final LimelightSubsystem limelight;
    private final int pipelineID;
    private final PIDController rotationController;


    private Pose2d targetPose;
    private static final double POSITION_TOLERANCE_METERS = 0.05; // 5 cm tolerance
    private static final double ROTATION_TOLERANCE_DEGREES = 2.0; // 2 degrees tolerance

    public LimelightCommand(DrivetrainSubsystem drivetrain, LimelightSubsystem limelight, int pipelineID) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        this.pipelineID = pipelineID;
        addRequirements(drivetrain, limelight);

        // Configure the rotation PID controller
        rotationController = new PIDController(0.02, 0.0, 0.001);
        rotationController.setTolerance(ROTATION_TOLERANCE_DEGREES);
    }

    @Override
    public void initialize() {
        limelight.setPipeline(pipelineID);
        targetPose = null;
    }

    @Override
    public void execute() {
        if (limelight.hasValidTarget()) {
            // Get the horizontal offset (tx) from the Limelight
            double horizontalOffset = limelight.getHorizontalOffset();

            // Calculate the new target pose based on the current pose and offset
            Pose2d currentPose = drivetrain.getPose();
            Translation2d newTranslation = currentPose.getTranslation().plus(new Translation2d(horizontalOffset, 0.0));
            targetPose = new Pose2d(newTranslation, currentPose.getRotation());

            // Calculate the desired rotation to face the tag (horizontal offset)
            double rotationCorrection = rotationController.calculate(horizontalOffset, 0.0);

            // Drive the robot towards the target pose and correct rotation
            drivetrain.driveRobotRelative(new ChassisSpeeds(1.0, 0.0, rotationCorrection)); // Adjust speeds as needed
        }
    }

    @Override
    public boolean isFinished() {
        if (targetPose == null) {
            return false;
        }

        Pose2d currentPose = drivetrain.getPose();

        // Calculate the difference in position and rotation
        double positionError = currentPose.getTranslation().getDistance(targetPose.getTranslation());
        double rotationError = Math.abs(limelight.getHorizontalOffset());

        System.out.println("finished- " + (positionError < POSITION_TOLERANCE_METERS && rotationError < ROTATION_TOLERANCE_DEGREES));
        // Check if the robot is within the position and rotation tolerances
        return positionError < POSITION_TOLERANCE_METERS && rotationError < ROTATION_TOLERANCE_DEGREES;

    }

    @Override
    public void end(boolean interrupted) {
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0)); // Stop the drivetrain
    }
}

