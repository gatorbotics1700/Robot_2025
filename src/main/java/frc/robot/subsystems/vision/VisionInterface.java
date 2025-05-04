package frc.robot.subsystems.vision;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;

/**
 * Interface for vision subsystems that can detect targets and provide pose information.
 * This interface abstracts the common functionality between LimelightSubsystem and Vision.
 */
public interface VisionInterface {
    /**
     * Returns whether the vision system has a valid target.
     * @return true if a valid target is detected, false otherwise
     */
    boolean hasValidTarget();

    /**
     * Gets the horizontal offset angle to the target in degrees.
     * @return the horizontal offset angle
     */
    double getHorizontalOffsetAngle();

    /**
     * Gets the vertical offset angle to the target in degrees.
     * @return the vertical offset angle
     */
    double getVerticalOffsetAngle();

    /**
     * Gets the target area.
     * @return the target area
     */
    double getTargetArea();

    /**
     * Gets the target ID.
     * @return the target ID
     */
    double getTargetID();

    /**
     * Gets the target pose in field space.
     * @param robotPoseInFieldSpace The current pose of the robot in field space
     * @param lineUpOffset The offset to apply to the target pose
     * @return The target pose in field space, or null if no valid target
     */
    Pose2d getTargetPoseInFieldSpace(Pose2d robotPoseInFieldSpace, Pose2d lineUpOffset);

    /**
     * Sets the pipeline ID for the vision system.
     * @param pipelineID The pipeline ID to set
     */
    void setPipeline(int pipelineID);

    /**
     * Turns on the LED.
     */
    void turnOnLED();

    /**
     * Turns off the LED.
     */
    void turnOffLED();
} 