package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.LimelightHelpers;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable LIMELIGHT_TABLE;
    private final double X_OFFSET = 0.2921; // distance from front of robot to limelight in meters

    public LimelightSubsystem() {
        LIMELIGHT_TABLE = NetworkTableInstance.getDefault().getTable("limelight");
        LimelightHelpers.setCameraPose_RobotSpace("limelight", 0.089, 0.324, 0.375, 0.0, 0.0, 0.0); //in meters //TODO: make these numbers variables
    }

    public void turnOnLED() {
        LIMELIGHT_TABLE.getEntry("ledMode").setNumber(3); // 3 means force on
    }

    public void turnOffLED() {
        LIMELIGHT_TABLE.getEntry("ledMode").setNumber(1); // 1 means force off
    }

    public boolean hasValidTarget() {
        return LIMELIGHT_TABLE.getEntry("tv").getDouble(0.0) == 1.0;
    }

    //angle between center of camera and center of apriltag (degrees) -- doesn't account for apriltag angle
    public double getHorizontalOffsetAngle() {
        return LIMELIGHT_TABLE.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffsetAngle() { //we don't trust this method for distance calculations -- don't use for that
        return LIMELIGHT_TABLE.getEntry("ty").getDouble(0.0);
    }

    public double getTargetArea() {
        return LIMELIGHT_TABLE.getEntry("ta").getDouble(0.0);
    }

    public double getSkew() {
        return LIMELIGHT_TABLE.getEntry("ts").getDouble(0.0);
    }

    public double getLatency() {
        return LIMELIGHT_TABLE.getEntry("tl").getDouble(0.0);
    }

    public double getTargetID() {
        return LIMELIGHT_TABLE.getEntry("tid").getDouble(0.0);
    }

    //camera relative angle of apriltag (degrees)
    public double getTagYaw() {
        //returns yaw of april tag relative to camera
        return LIMELIGHT_TABLE.getEntry("targetpose_cameraspace").getDoubleArray(new double[6])[4];
    }

    public void setPipeline(int pipelineID) {
        LIMELIGHT_TABLE.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    public double distanceToTag() {
        double[] targetPose = LIMELIGHT_TABLE.getEntry("targetpose_cameraspace").getDoubleArray(new double[6]);
        // returns Z offset to apriltag, but in the camera relative coordinate system
        double TZ = targetPose[2]; 
        // see above but it's y
        double TY = targetPose[1];
        return Math.sqrt((TZ * TZ) + (TY * TY)); // distance from camera to apriltag as the crow flies
    }

    public double fieldYDistanceToTag(double robotRotation) { // TODO add some sort of offset so that it lines up the way we want
        double d = distanceToTag()
                * Math.sin(Math.toRadians((robotRotation) - getHorizontalOffsetAngle()));
        return d;
    }

    public double fieldXDistanceToTag(double robotRotation) { 
        double d = distanceToTag()
                * Math.cos(Math.toRadians((robotRotation) - getHorizontalOffsetAngle()));
        d -= (X_OFFSET*Math.signum(d));
        return d;
    }

    @Override
    public void periodic() {

    }
}