package frc.robot.subsystems;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.SwerveModuleState;
import edu.wpi.first.wpilibj2.command.Subsystem;

/**
 * Interface defining the core functionality needed for swerve drive control. This allows for different swerve drive
 * implementations to be used interchangeably.
 */
public interface SwerveDriveInterface extends Subsystem {
    /**
     * Drives the robot using field-relative chassis speeds
     *
     * @param speeds The desired chassis speeds
     */
    void drive(ChassisSpeeds speeds);

    /**
     * Drives the robot using robot-relative chassis speeds
     *
     * @param speeds The desired chassis speeds
     */
    void driveRobotRelative(ChassisSpeeds speeds);

    /**
     * Gets the current pose of the robot
     *
     * @return The current pose
     */
    Pose2d getPose();

    /**
     * Gets the current rotation of the robot
     *
     * @return The current rotation
     */
    Rotation2d getRotation();

    /**
     * Resets the robot's pose
     *
     * @param pose The new pose
     */
    void resetPose(Pose2d pose);

    /**
     * Gets the current chassis speeds
     *
     * @return The current chassis speeds
     */
    ChassisSpeeds getRobotRelativeSpeeds();

    /**
     * Gets the current module states
     *
     * @return The current module states
     */
    SwerveModuleState[] getModuleStates();

    /** Stops all drive motors */
    void stop();

    /**
     * Sets the flag indicating the robot is not at its desired pose. This is used for pose control state management.
     */
    void setNotAtDesiredPose();

    /**
     * Drives the robot to a desired pose using pose-based control. This method handles both translation and rotation
     * control.
     *
     * @param desiredPose The target pose to drive to
     */
    void driveToPose(Pose2d desiredPose);

    /**
     * Checks if the robot has reached its desired pose.
     *
     * @return true if the robot is at the desired pose, false otherwise
     */
    boolean getAtDesiredPose();

    /** Toggles between robot-relative and field-relative drive modes */
    void toggleRobotRelativeDrive();

    /** Toggles slow drive mode on/off */
    void setSlowDrive();

    /**
     * Gets whether slow drive mode is enabled
     *
     * @return true if slow drive is enabled, false otherwise
     */
    boolean getSlowDrive();

    /**
     * Zeros the gyroscope based on the current alliance. For Red alliance, zeros to 180 degrees. For Blue alliance,
     * zeros to 0 degrees.
     */
    void zeroGyroscope();

    /**
     * Makes the robot face a specific point in the field coordinate system.
     * The robot will rotate to face the target point while maintaining its current position.
     *
     * @param targetPoint The target point to face in field coordinates
     */
    void facePoint(edu.wpi.first.math.geometry.Translation2d targetPoint);
}
