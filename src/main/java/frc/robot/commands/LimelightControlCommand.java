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

    // PID controller for rotation
    private final PIDController pidController;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        // Create a PIDController with tuning parameters (kP, kI, kD)
        pidController = new PIDController(0.3, 0, 0.001); // Adjust these values as needed
        pidController.setTolerance(0.2); // Set a tolerance for the error

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
            System.out.println("Valid target detected.");
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();

            // Calculate the rotation adjustment using the PID controller
            double rotationAdjustment = pidController.calculate(horizontalOffset, 0);

            // Apply the adjustment to the drivetrain
            drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, -rotationAdjustment));

            System.out.println("Rotating with adjustment: " + rotationAdjustment);
        } else {
            System.out.println("No valid target detected.");
           // drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Stop drivetrain if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when the error is within the tolerance
        return pidController.atSetpoint();
    }

    @Override
    public void end(boolean interrupted) {
        //drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Ensure drivetrain stops when command ends
    }
}