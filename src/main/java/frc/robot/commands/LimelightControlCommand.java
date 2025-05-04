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
    private final VisionInterface visionSubsystem;
    private final SwerveDriveInterface drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d lineUpOffset;
    private Rotation2d pointingToTagAngle; //field relative angle to point the robot at the apriltag
    private boolean dontStart = false;
    public LimelightControlCommand(VisionInterface visionSubsystem, SwerveDriveInterface drivetrainSubsystem,
            int pipeline, XboxController controller, Pose2d lineUpOffset) {
        this.visionSubsystem = visionSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;
        this.controller = controller;
        this.lineUpOffset = lineUpOffset;

        addRequirements(drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        visionSubsystem.setPipeline(pipeline);
        System.out.println("LINEUP X-OFFSET: " + lineUpOffset.getX());
        drivetrainSubsystem.setNotAtDesiredPose();
        if (visionSubsystem.hasValidTarget() && targetMatchesPipeline()) { 
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
        if (visionSubsystem.hasValidTarget() && targetMatchesPipeline()) { 
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
        desiredPose = visionSubsystem.getTargetPoseInFieldSpace(drivetrainSubsystem.getPose(), lineUpOffset);
        //the angle we need to be at to be pointing directly at the apriltag, rather than parallel to it
        pointingToTagAngle = drivetrainSubsystem.getPose().getRotation().minus(Rotation2d.fromDegrees(visionSubsystem.getHorizontalOffsetAngle()));
    }

    private boolean targetMatchesPipeline() {
        if (pipeline == 1) {
            return visionSubsystem.getTargetID() == 6 || visionSubsystem.getTargetID() == 19;
        } else if (pipeline == 2) {
            return visionSubsystem.getTargetID() == 7 || visionSubsystem.getTargetID() == 10 ||visionSubsystem.getTargetID() == 18 || visionSubsystem.getTargetID() == 21; 
        } else if (pipeline == 3){
            return visionSubsystem.getTargetID() == 8 || visionSubsystem.getTargetID() == 17; 
        } else if (pipeline == 4){ 
            return visionSubsystem.getTargetID() == 9 || visionSubsystem.getTargetID() == 22;
        } else if (pipeline == 5){ 
            return visionSubsystem.getTargetID() == 11 || visionSubsystem.getTargetID() == 20;
        } else if (pipeline == 6){ 
            return visionSubsystem.getTargetID() == 3 || visionSubsystem.getTargetID() == 16;
        } else if (pipeline == 7){ 
            return visionSubsystem.getTargetID() == 1 || visionSubsystem.getTargetID() == 2 || visionSubsystem.getTargetID() == 12 || visionSubsystem.getTargetID() == 13;
        }  else if (pipeline == 8){ 
            return visionSubsystem.getTargetID() == 4 || visionSubsystem.getTargetID() == 14 || visionSubsystem.getTargetID() == 5 || visionSubsystem.getTargetID() == 15;
        } else if (pipeline == 9){ 
            return visionSubsystem.getTargetID() == 5 || visionSubsystem.getTargetID() == 15;
        } else{
            return false;
        }
    }
}
