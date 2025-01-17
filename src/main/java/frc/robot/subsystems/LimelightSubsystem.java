package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
//import edu.wpi.first.math.*;

public class LimelightSubsystem extends SubsystemBase {

    private final NetworkTable limelightTable;
    private final double LIMELIGHT_HEIGHT = 0.37; // in meters
    private final double APRILTAG_HEIGHT = 0.7; //also in meters

    public LimelightSubsystem() {
        limelightTable = NetworkTableInstance.getDefault().getTable("limelight");
    }

    public void turnOnLED() {
        limelightTable.getEntry("ledMode").setNumber(3); // 3 means force on
    }

    public void turnOffLED() {
        limelightTable.getEntry("ledMode").setNumber(1); // 1 means force off
    }

    public boolean hasValidTarget() {
        return limelightTable.getEntry("tv").getDouble(0.0) == 1.0;
    }

    public double getHorizontalOffsetAngle() {
        return limelightTable.getEntry("tx").getDouble(0.0);
    }

    public double getVerticalOffsetAngle() {
        return limelightTable.getEntry("ty").getDouble(0.0);
    }

    public double getTargetArea() {
        return limelightTable.getEntry("ta").getDouble(0.0);
    }

    public double getSkew() {
        return limelightTable.getEntry("ts").getDouble(0.0);
    }

    public double getLatency() {
        return limelightTable.getEntry("tl").getDouble(0.0);
    }

    public double getTargetID() {
        return limelightTable.getEntry("tid").getDouble(0.0);
    }

    public void setPipeline(int pipelineID) {
        limelightTable.getEntry("pipeline").setNumber(pipelineID); // Set the pipeline ID
    }

    public double distanceToTag() {
        double d = (APRILTAG_HEIGHT-LIMELIGHT_HEIGHT)/Math.tan(getVerticalOffsetAngle());
       return d;//(0.33/*APRILTAG_HEIGHT-LIMELIGHT_HEIGHT*/)/0.31/*Math.tan(getVerticalOffset())*/; //returns 2D distance to apriltag (so like distance from base of robot to the point on the floor directly below the apriltag) - Elise

    }

    @Override
    public void periodic() {
        
    }
}