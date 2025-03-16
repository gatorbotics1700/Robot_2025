package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
// import frc.robot.subsystems.LimelightSubsystem;

public class PointToReefTest {
    private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    
    @Test
    public void testPointToReef1(){
        Rotation2d target = drivetrainSubsystem.angleToReef(5, 0);
        System.out.println(target);
        assertEquals(target, new Rotation2d(Math.toRadians(180)));

        target = drivetrainSubsystem.angleToReef(0, 5);
        System.out.println(target);
        assertEquals(target, new Rotation2d(Math.toRadians(-90)));

        target = drivetrainSubsystem.angleToReef(0, -5);
        System.out.println(target);
        assertEquals(target, new Rotation2d(Math.toRadians(90)));
    }
} 