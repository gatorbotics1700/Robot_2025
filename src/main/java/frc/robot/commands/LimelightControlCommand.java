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

    // Single PID controller for horizontal alignment
    private final PIDController pidController;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        // Create a PID controller (kP, kI, kD)
        pidController = new PIDController(0.03, 0, 0.001);
        pidController.setTolerance(0.2); // Set tolerance for alignment

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

            // Calculate the adjustment using the PID controller
            double adjustment = pidController.calculate(horizontalOffset, 0);

            // Split the adjustment between strafing and rotation
            double strafeAdjustment = adjustment * 0.8;  // 80% of the adjustment for strafing
            double rotationAdjustment = adjustment * 0.2; // 20% of the adjustment for rotation

            // Apply the adjustments to the drivetrain
            drivetrainSubsystem.drive(new ChassisSpeeds(0, -strafeAdjustment, -rotationAdjustment));

            System.out.println("Strafing adjustment: " + strafeAdjustment);
            System.out.println("Rotating adjustment: " + rotationAdjustment);
        } else {
            System.out.println("No valid target detected.");
            drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Stop drivetrain if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the error is within the tolerance
        return pidController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Ensure drivetrain stops when command ends
    }
}