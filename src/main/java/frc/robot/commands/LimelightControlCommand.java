package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.math.kinematics.ChassisSpeeds;

public class LimelightControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final int pipeline; // New field for pipeline number
    private double lastHorizontalOffset = 0.0; // Store the last known horizontal offset

    // Speed adjustment factor for drivetrain movement
    private static final double DRIVE_SPEED = 0.5;
    private static final double ROTATION_SPEED = 1.2;

    public LimelightControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem, int pipeline) {
        super();
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline); // Set the pipeline in the subsystem
        System.out.println("Pipeline set to: " + pipeline); // Debug statement
    }

    @Override
    public void execute() {
        if (limelightSubsystem.hasValidTarget()) {
            System.out.println("Valid target detected."); // Debug statement
            double horizontalOffset = limelightSubsystem.getHorizontalOffset();
            int direction =1;

            if (horizontalOffset < 0){
                direction =-1;
            }

            System.out.println("Horizontal offset: " + horizontalOffset);

            // Check if the horizontal offset has changed significantly
            if (Math.abs(horizontalOffset - lastHorizontalOffset) > 0.5) {
                
                double rotationAdjustment = direction * ROTATION_SPEED;

                // Move the drivetrain to adjust position based on Limelight offset
                drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, -rotationAdjustment));
                //drivetrainSubsystem.periodic();
                lastHorizontalOffset = horizontalOffset; // Update the last known offset
            }
        } else {
            System.out.println("No valid target detected."); // Debug statement
            //drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Stop drivetrain if no valid target
        }
    }

    @Override
    public boolean isFinished() {
        // Command finishes when there is no valid target
        boolean finished = Math.abs(limelightSubsystem.getHorizontalOffset()) < 0.5;
        System.out.println("FINISHED: " + finished);
        return finished;
    }

    @Override
    public void end(boolean interrupted) {
//        drivetrainSubsystem.drive(new ChassisSpeeds(0, 0, 0)); // Ensure drivetrain stops when command ends
    }
}