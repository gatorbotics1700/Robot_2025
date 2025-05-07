package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.SwerveDriveInterface;
import frc.robot.subsystems.vision.VisionInterface;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;

public class LimelightControlCommand extends Command {
    private final VisionInterface limelightLubsystem;
    private final SwerveDriveInterface drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d lineUpOffset;
    private Rotation2d pointingToTagAngle; //field relative angle to point the robot at the apriltag
    private boolean dontStart = false;
    public LimelightControlCommand(VisionInterface visionSubsystem, SwerveDriveInterface drivetrainSubsystem,
            int pipeline, XboxController controller, Pose2d lineUpOffset) {
        this.limelightLubsystem = visionSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;
        this.controller = controller;
        this.lineUpOffset = lineUpOffset;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightLubsystem.setPipeline(pipeline);
        System.out.println("LINEUP X-OFFSET: " + lineUpOffset.getX());
        drivetrainSubsystem.setNotAtDesiredPose();
        if (limelightLubsystem.hasValidTarget() && targetMatchesPipeline()) { 
            dontStart = false;
        } else if (DriverStation.isAutonomousEnabled()){
            System.out.println("initialization of limelight control command in auto with no valid target");
        } else {
            dontStart = true;
            System.out.println("\tNo valid target detected.");
        }
    }

    @Override
    public void execute() {
        // makes sure we are looking at the correct id
        if (limelightLubsystem.hasValidTarget() && targetMatchesPipeline()) { 
            updateDesiredPose();
        } else {
            System.out.println("\tNo valid target detected.");
        }

        if (desiredPose != null) {
            //drivetrainSubsystem.driveToPoseWithInitialAngle(desiredPose, pointingToTagAngle);
            drivetrainSubsystem.driveToPose(desiredPose);
        }
    }

    @Override
    public boolean isFinished() {
        
        // Check if either stick on the Xbox controller is moved
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.2 ||
                Math.abs(controller.getLeftY()) > 0.2 ||
                Math.abs(controller.getRightX()) > 0.2 ||
                Math.abs(controller.getRightY()) > 0.2;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            desiredPose = null;
            return true;
        }

        if(drivetrainSubsystem.getAtDesiredPose()){
            return true;
        }
        if(dontStart){
            return true;
        }

        return false;
        // TODO: allow mech commands to end this as well
    }

    private void updateDesiredPose() { 
        desiredPose = limelightLubsystem.getTargetPoseInFieldSpace(drivetrainSubsystem.getPose(), lineUpOffset);
        //the angle we need to be at to be pointing directly at the apriltag, rather than parallel to it
        pointingToTagAngle = drivetrainSubsystem.getPose().getRotation().minus(Rotation2d.fromDegrees(limelightLubsystem.getHorizontalOffsetAngle()));
    }

    private boolean targetMatchesPipeline() {
        if (pipeline == 1) {
            return limelightLubsystem.getTargetID() == 6 || limelightLubsystem.getTargetID() == 19;
        } else if (pipeline == 2) {
            return limelightLubsystem.getTargetID() == 7 || limelightLubsystem.getTargetID() == 10 ||limelightLubsystem.getTargetID() == 18 || limelightLubsystem.getTargetID() == 21; 
        } else if (pipeline == 3){
            return limelightLubsystem.getTargetID() == 8 || limelightLubsystem.getTargetID() == 17; 
        } else if (pipeline == 4){ 
            return limelightLubsystem.getTargetID() == 9 || limelightLubsystem.getTargetID() == 22;
        } else if (pipeline == 5){ 
            return limelightLubsystem.getTargetID() == 11 || limelightLubsystem.getTargetID() == 20;
        } else if (pipeline == 6){ 
            return limelightLubsystem.getTargetID() == 3 || limelightLubsystem.getTargetID() == 16;
        } else if (pipeline == 7){ 
            return limelightLubsystem.getTargetID() == 1 || limelightLubsystem.getTargetID() == 2 || limelightLubsystem.getTargetID() == 12 || limelightLubsystem.getTargetID() == 13;
        }  else if (pipeline == 8){ 
            return limelightLubsystem.getTargetID() == 4 || limelightLubsystem.getTargetID() == 14 || limelightLubsystem.getTargetID() == 5 || limelightLubsystem.getTargetID() == 15;
        } else if (pipeline == 9){ 
            return limelightLubsystem.getTargetID() == 5 || limelightLubsystem.getTargetID() == 15;
        } else{
            return false;
        }
    }
}
