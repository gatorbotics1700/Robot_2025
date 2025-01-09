package frc.robot.commands;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class LimelightControlCommand extends InstantCommand {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline;

    // PID controllers for strafe (left/right) and rotation
    private final PIDController strafePIDController;
    private final PIDController rotationPIDController;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        // Strafe PID (horizontal alignment)
        strafePIDController = new PIDController(0.2, 0, 0.01); // Adjust these values as needed
        strafePIDController.setTolerance(0.5); // Set tolerance for horizontal alignment

        // Rotation PID (rotational alignment)
        rotationPIDController = new PIDController(0.2, 0, 0.01); // Adjust these values as needed
        rotationPIDController.setTolerance(1.0); // Set tolerance for rotational alignment

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
            double skew = limelightSubsystem.getSkew(); // Or use rotation offset if applicable

            // Calculate adjustments using PID controllers
            double strafeAdjustment = strafePIDController.calculate(horizontalOffset, 0);
            double rotationAdjustment = rotationPIDController.calculate(skew, 0);

            // Apply the adjustments to the drivetrain
            drivetrainSubsystem.drive(new ChassisSpeeds(0, -strafeAdjustment, -rotationAdjustment));

            System.out.println("Strafing adjustment: " + strafeAdjustment);
            System.out.println("Rotating adjustment: " + rotationAdjustment);
        } else {
            System.out.println("No valid target detected.");
           // drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Stop drivetrain if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when both strafe and rotation errors are within tolerance
        return strafePIDController.atSetpoint() && rotationPIDController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
       // drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Ensure drivetrain stops when command ends
    }
}