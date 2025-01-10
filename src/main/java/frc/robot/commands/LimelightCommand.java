package frc.robot.commands;

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

    public LimelightCommand(DrivetrainSubsystem drivetrain, LimelightSubsystem limelight) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        addRequirements(drivetrain, limelight);
    }

    @Override
    public void initialize() {
        limelight.turnOnLED();
        System.out.println("DriveToAprilTagCommand initialized");
    }

    @Override
    public void execute() {
        if (limelight.hasValidTarget()) {
            double horizontalOffset = limelight.getHorizontalOffset();

            Pose2d currentPose = drivetrain.getPose();
            Translation2d newTranslation = currentPose.getTranslation().plus(new Translation2d(horizontalOffset, 0.0));
            Pose2d targetPose = new Pose2d(newTranslation, currentPose.getRotation());

            drivetrain.resetPose(targetPose);
            drivetrain.driveRobotRelative(new ChassisSpeeds(1.0, 0.0, 0.0));  // Adjust speeds as needed
        }
    }

    @Override
    public void end(boolean interrupted) {
        limelight.turnOffLED();
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
        System.out.println("DriveToAprilTagCommand ended");
    }

    @Override
    public boolean isFinished() {
        return false;
    }
}