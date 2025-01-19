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
    private boolean atDesiredPose = false;
    private Pose2d desiredPose;
    private Pose2d currentPose;

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
        System.out.println("currentpose: " + drivetrainSubsystem.getPose());
        
        if (limelightSubsystem.hasValidTarget()) {
             double horizontalOffset = limelightSubsystem.getHorizontalOffsetAngle();
        //     double verticalOffset = limelightSubsystem.getVerticalOffsetAngle();

             currentPose = drivetrainSubsystem.getPose();

              Rotation2d desiredRotation = currentPose.getRotation().plus(Rotation2d.fromDegrees(horizontalOffset));

              desiredPose = new Pose2d(currentPose.getX() + limelightSubsystem.fieldXDistanceToTag(), currentPose.getY() + limelightSubsystem.fieldYDistanceToTag(), desiredRotation); //fix this 
        // // Pose2d desiredPose = new Pose2d(currentPose.getX(), currentPose.getY(), desiredRotation); 

              
              if(Math.abs(currentPose.getX()-desiredPose.getX()) < 0.05 && Math.abs(currentPose.getY()-desiredPose.getY()) < 0.05){
                System.out.println("*******************at desired pose");
                atDesiredPose = true;
              }

             System.out.println("Driving to pose: " + desiredPose);
            
            //System.out.println("*******************"+limelightSubsystem.distanceToTag());
            System.out.println("dx " + limelightSubsystem.fieldXDistanceToTag());
        } else {

            System.out.println("No valid target detected.");
        }
        if(desiredPose != null && Math.abs(currentPose.getX()-desiredPose.getX()) > 0.1 || Math.abs(currentPose.getY()-desiredPose.getY()) > 0.1 /*|| Math.abs(limelightSubsystem.getHorizontalOffsetAngle()) > 10*/){
            drivetrainSubsystem.driveToPose(desiredPose);
        }
    }

    @Override
    public boolean isFinished() {
        System.out.println("Finished: " + (Math.abs(limelightSubsystem.getHorizontalOffsetAngle()) < 2 && atDesiredPose));
        return Math.abs(limelightSubsystem.getHorizontalOffsetAngle()) < 2 && atDesiredPose;
    }

}