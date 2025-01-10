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
    private final int pipelineID;

    public LimelightCommand(DrivetrainSubsystem drivetrain, LimelightSubsystem limelight, int pipelineID) {
        this.drivetrain = drivetrain;
        this.limelight = limelight;
        this.pipelineID = pipelineID;
        addRequirements(drivetrain, limelight);
    }

    @Override
    public void initialize() {
        limelight.setPipeline(pipelineID);
        limelight.turnOnLED();
        System.out.println("DriveToAprilTagCommand initialized with pipeline " + pipelineID);
    }

    @Override
    public void execute() {
        if (limelight.hasValidTarget()) {
            double horizontalOffset = limelight.getHorizontalOffset();

            
            Pose2d currentPose = drivetrain.getPose();
            Translation2d newTranslation = currentPose.getTranslation().plus(new Translation2d(horizontalOffset, 0.0));
            Pose2d targetPose = new Pose2d(newTranslation, currentPose.getRotation());

            drivetrain.resetPose(targetPose);
            drivetrain.driveRobotRelative(new ChassisSpeeds(1.0, 0.0, 0.0));  
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