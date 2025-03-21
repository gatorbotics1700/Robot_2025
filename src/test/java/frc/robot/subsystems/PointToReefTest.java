package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import frc.robot.subsystems.LimelightSubsystem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
// import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class PointToReefTest {
    static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    @Test
     void testPointToReef1(){
        Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY() + 5, new Rotation2d(0));
        double robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        double robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        Rotation2d target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("+x +y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(-135)));

        currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY() + 5, new Rotation2d(0));
        robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("-x, +y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(-45)));
       
    }

    @Test
    void testPointToReef2(){
       
        Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY() - 5, new Rotation2d(0));
        double robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        double robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        Rotation2d target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("+x -y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(135)));

        currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY() - 5, new Rotation2d(0));
        robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("-x, -y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(45)));
       
    }

    @Test
    void testPointToReef3(){
       
        Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY(), new Rotation2d(0));
        double robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        double robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        Rotation2d target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("+x: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(180)));

        currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY(), new Rotation2d(0));
        robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("-x: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(0)));
       
    }

    @Test
    void testPointToReef4(){
       
        Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX(), Constants.BLUE_REEF_POSE.getY() +5, new Rotation2d(0));
        double robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        double robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        Rotation2d target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("+y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(-90)));

        currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX(), Constants.BLUE_REEF_POSE.getY()-5, new Rotation2d(0));
        robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("-y: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(90)));
       
    }

    @Test
    void testPointToReef5(){
       
        Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 1, Constants.BLUE_REEF_POSE.getY() + Math.sqrt(3), new Rotation2d(0));
        double robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        double robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        Rotation2d target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("x+1, y+root 3: " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(-120)));

        currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -1 , Constants.BLUE_REEF_POSE.getY()+ Math.sqrt(3), new Rotation2d(0));
        robotMinusReefX = currentPose.getX() - Constants.BLUE_REEF_POSE.getX();
        robotMinusReefY = currentPose.getY() - Constants.BLUE_REEF_POSE.getY();
        target = drivetrainSubsystem.angleToReef(robotMinusReefX, robotMinusReefY);
        System.out.println("x-1, y+root 3 " + target);
        assertEquals(target, new Rotation2d(Math.toRadians(-30)));
       
    }

    // @Test
    // void printingTest(){
    //     System.out.println("left: " + Math.atan(0.0/3.0));
    //     System.out.println("down: " + Math.atan2(0, -3.0));
    //     System.out.println("right: " + Math.atan2(-3.0, 0));
    //     System.out.println("up: " + Math.atan2(0, 3.0));
    // }

    
} 