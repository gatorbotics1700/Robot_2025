// package frc.robot.subsystems;

// import static org.junit.jupiter.api.Assertions.*;
// import org.junit.jupiter.api.Test;
// import edu.wpi.first.math.geometry.Pose2d;
// import edu.wpi.first.math.geometry.Pose3d;
// import edu.wpi.first.math.geometry.Rotation2d;
// import edu.wpi.first.math.geometry.Rotation3d;
// import edu.wpi.first.math.geometry.Transform2d;
// import edu.wpi.first.math.geometry.Translation2d;
// import frc.robot.subsystems.LimelightSubsystem;
// import frc.robot.Constants;


// public class CoordinateTransforms {
    
//     @Test
//     public void calculateReefCoordsL4Red() {
//         //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//         double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET+Constants.L4_SHOOTING_DISTANCE + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//         Translation2d l4Right = new Translation2d(botToReefX,Constants.POST_DISTANCE);
//         Translation2d l4Left = new Translation2d(botToReefX,-Constants.POST_DISTANCE);
//         Translation2d reefCenterRed = new Translation2d(12.9933, 4.025);

//         Pose2d id7RightCoord = new Pose2d(reefCenterRed, new Rotation2d(0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id7LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id8RightCoord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id8LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id9RightCoord = new Pose2d(reefCenterRed, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id9LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id10RightCoord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id10LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id11RightCoord = new Pose2d(reefCenterRed, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id11LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id6RightCoord = new Pose2d(reefCenterRed, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id6LeftCoord = new Pose2d(reefCenterRed, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));
        
//         // System.out.println("id7 Right: " + id7RightCoord);
//         // System.out.println("id7 Left: " + id7LeftCoord);
//         System.out.println("q3 id8 Right: " + id8RightCoord);
//         System.out.println("q3 id8 Left: " + id8LeftCoord);
//         System.out.println("q2 id9 Right: " + id9RightCoord);
//         System.out.println("q2 id9 Left: " + id9LeftCoord);
//         System.out.println("q1 id10 Right: " + id10RightCoord);
//         System.out.println("q1 id10 Left: " + id10LeftCoord);
//         System.out.println("q6 id11 Right: " + id11RightCoord);
//         System.out.println("q6 id11 Left: " + id11LeftCoord);
//         System.out.println("q5 id6 Right: " + id6RightCoord);
//         System.out.println("q5 id6 Left: " + id6LeftCoord);
       

//     }

//     @Test
//     public void calculateReefCoordsL4Blue() {
//         //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//         double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET+Constants.L4_SHOOTING_DISTANCE + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//         Translation2d l4Right = new Translation2d(botToReefX,Constants.POST_DISTANCE);
//         Translation2d l4Left = new Translation2d(botToReefX,-Constants.POST_DISTANCE);
//         Translation2d reefCenterBlue = new Translation2d(4.5567, 4.025);

//         Pose2d id21RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id21LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id20RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id20LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id19RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id19LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id18RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id18LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id17RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id17LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));

//         Pose2d id22RightCoord = new Pose2d(reefCenterBlue, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(l4Right, new Rotation2d(Math.PI)));
//         Pose2d id22LeftCoord = new Pose2d(reefCenterBlue, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(l4Left, new Rotation2d(Math.PI)));
        
//         System.out.println("q1 id21 Right: " + id21RightCoord);
//         System.out.println("q1 id21 Left: " + id21LeftCoord);
//         System.out.println("q6 id20 Right: " + id20RightCoord);
//         System.out.println("q6 id20 Left: " + id20LeftCoord);
//         System.out.println("q5 id19 Right: " + id19RightCoord);
//         System.out.println("q5 id19 Left: " + id19LeftCoord);
//         // System.out.println("id18 Right: " + id18RightCoord);
//         // System.out.println("id18 Left: " + id18LeftCoord);
//         System.out.println("q3 id17 Right: " + id17RightCoord);
//         System.out.println("q3 id17 Left: " + id17LeftCoord);
//         System.out.println("q2 id22 Right: " + id22RightCoord);
//         System.out.println("q2 id22 Left: " + id22LeftCoord);
//     }

//     // @Test
//     // public void calculateReefCoordsTroughRed() {
//     //     //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//     //     double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//     //     Translation2d trough = new Translation2d(botToReefX,0);
//     //     Translation2d reefCenterRed = new Translation2d(12.9933, 4.025);

//     //     Pose2d id7Coord = new Pose2d(reefCenterRed, new Rotation2d(0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id8Coord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id9Coord = new Pose2d(reefCenterRed, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id10Coord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id11Coord = new Pose2d(reefCenterRed, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id6Coord = new Pose2d(reefCenterRed, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));
        
//     //     System.out.println("id7: " + id7Coord);
//     //     System.out.println("id8: " + id8Coord);
//     //     System.out.println("id9: " + id9Coord);
//     //     System.out.println("id10: " + id10Coord);
//     //     System.out.println("id11: " + id11Coord);
//     //     System.out.println("id6: " + id6Coord);
       

//     // }

//     // @Test
//     // public void calculateReefCoordsTroughBlue() {
//     //     //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//     //     double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//     //     Translation2d trough = new Translation2d(botToReefX,0);
//     //     Translation2d reefCenterBlue = new Translation2d(4.5567, 4.025);

//     //     Pose2d id21Coord = new Pose2d(reefCenterBlue, new Rotation2d(0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id20Coord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id19Coord = new Pose2d(reefCenterBlue, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id18Coord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id17Coord = new Pose2d(reefCenterBlue, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id22Coord = new Pose2d(reefCenterBlue, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));
        
//     //     System.out.println("id21: " + id21Coord);
//     //     System.out.println("id20: " + id20Coord);
//     //     System.out.println("id19: " + id19Coord);
//     //     System.out.println("id18: " + id18Coord);
//     //     System.out.println("id17: " + id17Coord);
//     //     System.out.println("id22: " + id22Coord);
//     // }


//     // @Test
//     // public void calculateReefCoordsRed() {
//     //     //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//     //     double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//     //     Translation2d trough = new Translation2d(botToReefX + 0.72,0);
//     //     Translation2d reefCenterRed = new Translation2d(12.9933, 4.025);

//     //     Pose2d id7Coord = new Pose2d(reefCenterRed, new Rotation2d(0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id8Coord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id9Coord = new Pose2d(reefCenterRed, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id10Coord = new Pose2d(reefCenterRed, new Rotation2d(Math.PI)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id11Coord = new Pose2d(reefCenterRed, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id6Coord = new Pose2d(reefCenterRed, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));
        
//     //     System.out.println("id7: " + id7Coord);
//     //     System.out.println("id8: " + id8Coord);
//     //     System.out.println("id9: " + id9Coord);
//     //     System.out.println("id10: " + id10Coord);
//     //     System.out.println("id11: " + id11Coord);
//     //     System.out.println("id6: " + id6Coord);
       

//     // }

//     // @Test
//     // public void calculateReefCoordsBlue() {
//     //     //imagine you're looking at a field diagram, with the origin in the bottom right corner. for these numbers, imagine the robot is at the top position on the reef, for red or blue, trying to score on the robot's right, or the field's left
//     //     double botToReefX = Constants.CENTER_TO_BUMPER_OFFSET + Constants.REEF_RADIUS; //distance in meters from robot center to center of reef, but only in the x (assume the robot is vertical with the reef center)
//     //     Translation2d trough = new Translation2d(botToReefX + 0.72,0);
//     //     Translation2d reefCenterBlue = new Translation2d(4.5567, 4.025);

//     //     Pose2d id21Coord = new Pose2d(reefCenterBlue, new Rotation2d(0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id20Coord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id19Coord = new Pose2d(reefCenterBlue, new Rotation2d(2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id18Coord = new Pose2d(reefCenterBlue, new Rotation2d(Math.PI)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id17Coord = new Pose2d(reefCenterBlue, new Rotation2d(-2.0*(Math.PI/3.0))).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));

//     //     Pose2d id22Coord = new Pose2d(reefCenterBlue, new Rotation2d(-Math.PI/3.0)).transformBy(new Transform2d(trough, new Rotation2d(Math.PI)));
        
//     //     System.out.println("id21: " + id21Coord);
//     //     System.out.println("id20: " + id20Coord);
//     //     System.out.println("id19: " + id19Coord);
//     //     System.out.println("id18: " + id18Coord);
//     //     System.out.println("id17: " + id17Coord);
//     //     System.out.println("id22: " + id22Coord);
//     // }
// }
