package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class Limelight2ControlCommand extends Command {
    private final LimelightSubsystem limelightSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final XboxController controller;
    private final int pipeline;
    private Pose2d desiredPose;
    private Pose2d lineUpOffset;
    private Rotation2d pointingToTagAngle; //field relative angle to point the robot at the apriltag

    public Limelight2ControlCommand(LimelightSubsystem limelightSubsystem, DrivetrainSubsystem drivetrainSubsystem,
            int pipeline, XboxController controller, Pose2d lineUpOffset) {
        this.limelightSubsystem = limelightSubsystem;
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.pipeline = pipeline;
        this.controller = controller;
        this.lineUpOffset = lineUpOffset;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize() {
        limelightSubsystem.setPipeline(pipeline);
    }

    @Override
    public void execute() {
        // makes sure we are looking at the correct id 

        if (limelightSubsystem.LL2HasValidTarget() && targetMatchesPipeline()){
            updateDesiredPose();
        }else {
            System.out.println("\tNo valid target detected.");
        }
        if (desiredPose != null) {
            drivetrainSubsystem.driveToPoseWithInitialAngle(desiredPose, pointingToTagAngle);

        }
    }

    @Override
    public boolean isFinished() {
        // Check if either stick on the Xbox controller is moved
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.1 ||
                Math.abs(controller.getLeftY()) > 0.1 ||
                Math.abs(controller.getRightX()) > 0.1 ||
                Math.abs(controller.getRightY()) > 0.1;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            desiredPose = null;
            return true;
        }
        
        return false;
        // TODO: allow mech commands to end this as well
    }

    private void updateDesiredPose() { 
        desiredPose = limelightSubsystem.aprilTagPoseInFieldSpace(drivetrainSubsystem.getPose(), lineUpOffset, "limelight-2");
        //the angle we need to be at to be pointing directly at the apriltag, rather than parallel to it
        pointingToTagAngle = drivetrainSubsystem.getPose().getRotation().minus(Rotation2d.fromDegrees(limelightSubsystem.getLL2HorizontalOffsetAngle()));
    }

    private boolean targetMatchesPipeline() {
        if (pipeline == 1) {
            return limelightSubsystem.getTargetID() == 6 || limelightSubsystem.getTargetID() == 19;
        }else{
            return false;
        }
    }

}

