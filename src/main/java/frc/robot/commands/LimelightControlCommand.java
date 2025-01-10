package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
        System.out.println("Pipeline set to: " + pipeline);
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();

            // Get the current pose
            Pose2d currentPose = drivetrainSubsystem.getPose();

            // Calculate the desired rotation to center the AprilTag
            Rotation2d desiredRotation = currentPose.getRotation().plus(Rotation2d.fromDegrees(horizontalOffset));

            // Create a new desired pose with the updated rotation
            Pose2d desiredPose = new Pose2d(currentPose.getX(), currentPose.getY(), desiredRotation);

            // Drive to the desired pose
            drivetrainSubsystem.driveToPose(desiredPose);

            System.out.println("Driving to pose: " + desiredPose);
        } else {
            drivetrainSubsystem.stop();

            System.out.println("No valid target detected.");
        }
    }

    @Override
    public boolean isFinished() {
        Pose2d currentPose = drivetrainSubsystem.getPose();
        Pose2d desiredPose = new Pose2d(currentPose.getX(), currentPose.getY(), currentPose.getRotation());
    
        double positionError = currentPose.getTranslation().getDistance(desiredPose.getTranslation());
        double rotationError = Math.abs(currentPose.getRotation().getDegrees() - desiredPose.getRotation().getDegrees());
    
        return positionError < 1.0 && rotationError < 0.5;
    }

    @Override
    public void end(boolean interrupted) {
      //  drivetrainSubsystem.stop();
    }
}