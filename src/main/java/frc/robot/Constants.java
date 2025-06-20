package frc.robot;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Pose3d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Rotation3d;
import edu.wpi.first.math.geometry.Translation2d;
import frc.com.swervedrivespecialties.swervelib.MechanicalConfiguration;
import frc.com.swervedrivespecialties.swervelib.SdsModuleConfigurations;

public class Constants {
    //for a 25x25 drivetrain
    // public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.508;
    // public static final double DRIVETRAIN_WHEELBASE_METERS = 0.508;

    //for a 30x30 drivetrain
    public static final double DRIVETRAIN_TRACKWIDTH_METERS = 0.508 + 0.127;
    public static final double DRIVETRAIN_WHEELBASE_METERS = 0.508 + 0.127;

    public static final int DRIVETRAIN_PIGEON_ID = 6;
    public static final double CENTER_TO_POST = 0.1778;

    // hulk
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(118.6253437499);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(283.7109375);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(124.0988159179);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(38.75976562499);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;
    // public static final String CANIVORE_BUS_NAME = "";

    //nemo
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "";
    // // public static final Pose3d LIMELIGHT_OFFSETS = new Pose3d(0.172, 0.325, 0.197, new Rotation3d(Math.toRadians(10.0), Math.toRadians(-23.0), Math.toRadians(-46.0)));
    // public static final Pose3d LIMELIGHT_OFFSETS = new Pose3d(0.138, 0.346075, 0.2651125, new Rotation3d(Math.toRadians(2), Math.toRadians(0), Math.toRadians(0)));
    // public static final double CENTER_TO_BUMPER_OFFSET = 0.3937;


    // dory
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "";
    // public static final Pose3d LIMELIGHT_OFFSETS = new Pose3d(0.26035, 0.00635, 1.143, new Rotation3d(Math.toRadians(0.0), Math.toRadians(-32.0), Math.toRadians(-4.0)));

    //comp bot
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(268.0664);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(231.3281);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(229.57);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(52.1191);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    public static final String CANIVORE_BUS_NAME = "TRex";
    public static final Pose3d LIMELIGHT_OFFSETS = new Pose3d(0.3394, 0.039 -0.03, 0.196, new Rotation3d(Math.toRadians(1), Math.toRadians(-19.5), Math.toRadians(-3.0)));
    public static final double CENTER_TO_BUMPER_OFFSET = 0.4445;
    public static final double CENTER_TO_CHASSIS_OFFSET = 0.381;
    
    public static final Pose2d FRONT_CENTER_ALIGN_OFFSET = new Pose2d(CENTER_TO_BUMPER_OFFSET, 0, new Rotation2d(0)); //offset from center of robot to where we want to line up with the april tag
    public static final Pose2d INTAKE_ALIGN_OFFSET = new Pose2d(CENTER_TO_BUMPER_OFFSET - 0.3, 0, new Rotation2d(0)); //offset from center of robot to where we want to line up with the april tag
    public static final Pose2d SHOOTING_L4_LEFT_OFFSET = new Pose2d(CENTER_TO_CHASSIS_OFFSET + 0.073025, -CENTER_TO_POST, new Rotation2d(0)); //offset from center of robot to where we want to line up with the april tag
    public static final Pose2d SHOOTING_L4_RIGHT_OFFSET = new Pose2d(CENTER_TO_CHASSIS_OFFSET + 0.073025, CENTER_TO_POST, new Rotation2d(0)); //offset from center of robot to where we want to line up with the april tag
    // //the line up offsets are the point (in robot relative coordinates) that needs to align with the apriltag in order for us to score left/right post 
    // //(we flip the values in our offset method so that we can find the pose the center of the robot needs to be at, but they should not be flipped here!)

    public static final int FRONT_LEFT_MODULE_DRIVE_MOTOR = 21;
    public static final int FRONT_LEFT_MODULE_STEER_MOTOR = 20; 
    public static final int FRONT_LEFT_MODULE_STEER_ENCODER = 2;

    public static final int FRONT_RIGHT_MODULE_DRIVE_MOTOR = 23; 
    public static final int FRONT_RIGHT_MODULE_STEER_MOTOR = 22; 
    public static final int FRONT_RIGHT_MODULE_STEER_ENCODER = 3;

    public static final int BACK_LEFT_MODULE_DRIVE_MOTOR = 27; 
    public static final int BACK_LEFT_MODULE_STEER_MOTOR = 26; 
    public static final int BACK_LEFT_MODULE_STEER_ENCODER = 5; 

    public static final int BACK_RIGHT_MODULE_DRIVE_MOTOR = 25; 
    public static final int BACK_RIGHT_MODULE_STEER_MOTOR = 24; 
    public static final int BACK_RIGHT_MODULE_STEER_ENCODER = 4;
    
    public static final double KRAKEN_TICKS_PER_REV = 2048; //same for falcons
    public static final double NEO_TICKS_PER_REV = 42;
    

    // CORAL SHOOTER MECHANISM
    public static final int SHOOTER_MOTOR_TOP_LEFT_CAN_ID = 30;
    public static final int SHOOTER_MOTOR_TOP_RIGHT_CAN_ID = 32; 
    public static final double CORAL_INTAKING_VOLTAGE = 4.5;
    public static final double CORAL_PREINTAKE_SHOOTING_VOLTAGE = -1.0; 
    public static final double CORAL_L4_SHOOTING_VOLTAGE = -7.8;
    public static final double CORAL_TROUGH_SHOOTING_VOLTAGE = -5.5;
    public static final double CORAL_VOMIT_VOLTAGE = -0.3;

    // CLIMBING MECHANISM
    public static final double CLIMBING_SPEED = 0.5; 
    public static final int CLIMBING_MOTOR_CAN_ID = 35; 
    public static final int UNWINCH_TIME = 3400;

    /* other information
     * degrees to ticks conversion: ticks per rev * gear ratio / 360
     */
    public static final double LOOPTIME_SECONDS = 0.02;

    public static final Translation2d RED_REEF_POSE = new Translation2d(12.933, 4.025);
    public static final Translation2d BLUE_REEF_POSE = new Translation2d(4.5567, 4.025);

    public static final double REEF_RADIUS = 0.8232394; //not actually the radius, but distance from center to apriltag
}   

