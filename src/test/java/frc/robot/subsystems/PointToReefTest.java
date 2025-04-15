package frc.robot.subsystems;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.robot.subsystems.LimelightSubsystem;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import frc.robot.commands.PointToTagCommand;

// import frc.robot.subsystems.LimelightSubsystem;
import frc.robot.Constants;
import frc.robot.subsystems.DrivetrainSubsystem;

public class PointToReefTest {
    // static DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    // private Translation2d delta(Pose2d currentPose){
    //     return new Translation2d(Constants.BLUE_REEF_POSE.getX()-currentPose.getX(), Constants.BLUE_REEF_POSE.getY()-currentPose.getY());
    // }
    // @Test
    //  void testPointToReef1(){
    //     Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY() + 5, new Rotation2d(0));
       
    //     Rotation2d target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("+x +y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(-135)));

    //     currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY() + 5, new Rotation2d(0));
    //     target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("-x, +y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(-45)));
       
    // }

    // @Test
    // void testPointToReef2(){
       
    //     Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY() - 5, new Rotation2d(0));
    //     Rotation2d target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("+x -y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(135)));

    //     currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY() - 5, new Rotation2d(0));
    //     target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("-x, -y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(45)));
       
    // }

    // @Test
    // void testPointToReef3(){
       
    //     Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 5, Constants.BLUE_REEF_POSE.getY(), new Rotation2d(0));
    //     Rotation2d target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("+x: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(180)));

    //     currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -5, Constants.BLUE_REEF_POSE.getY(), new Rotation2d(0));
    //     target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("-x: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(0)));
       
    // }

    // @Test
    // void testPointToReef4(){
       
    //     Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX(), Constants.BLUE_REEF_POSE.getY() +5, new Rotation2d(0));
    //     Rotation2d target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("+y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(-90)));

    //     currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX(), Constants.BLUE_REEF_POSE.getY()-5, new Rotation2d(0));
    //     target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("-y: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(90)));
       
    // }

    // @Test
    // void testPointToReef5(){
       
    //     Pose2d currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() + 1, Constants.BLUE_REEF_POSE.getY() + Math.sqrt(3), new Rotation2d(0));
    //     Rotation2d target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("x+1, y+root 3: " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(-120)));

    //     currentPose = new Pose2d(Constants.BLUE_REEF_POSE.getX() -1 , Constants.BLUE_REEF_POSE.getY()+ Math.sqrt(3), new Rotation2d(0));
    //     target = drivetrainSubsystem.angleToPoint(delta(currentPose).getX(), delta(currentPose).getY());
    //     System.out.println("x-1, y+root 3 " + target);
    //     assertEquals(target, new Rotation2d(Math.toRadians(-60)));
       
    // }

    @Test
    void testPointToTag(){
       System.out.println("blue center: " + Constants.BLUE_REEF_POSE);
       System.out.println("blue 1: " + PointToTagCommand.getTagCoords("blue", 1));
       System.out.println("blue 2: " + PointToTagCommand.getTagCoords("blue", 2));
       System.out.println("blue 3: " + PointToTagCommand.getTagCoords("blue", 3));
       System.out.println("blue 4: " + PointToTagCommand.getTagCoords("blue", 4));
       System.out.println("blue 5: " + PointToTagCommand.getTagCoords("blue", 5));
       System.out.println("blue 6: " + PointToTagCommand.getTagCoords("blue", 6));

       System.out.println("red center: " + Constants.RED_REEF_POSE);
       System.out.println("red 1: " + PointToTagCommand.getTagCoords("red", 1));
       System.out.println("red 2: " + PointToTagCommand.getTagCoords("red", 2));
       System.out.println("red 3: " + PointToTagCommand.getTagCoords("red", 3));
       System.out.println("red 4: " + PointToTagCommand.getTagCoords("red", 4));
       System.out.println("red 5: " + PointToTagCommand.getTagCoords("red", 5));
       System.out.println("red 6: " + PointToTagCommand.getTagCoords("red", 6));
    }



    // @Test
    // void printingTest(){
    //     System.out.println("left: " + Math.atan(0.0/3.0));
    //     System.out.println("down: " + Math.atan2(0, -3.0));
    //     System.out.println("right: " + Math.atan2(-3.0, 0));
    //     System.out.println("up: " + Math.atan2(0, 3.0));
    // }

    
} 