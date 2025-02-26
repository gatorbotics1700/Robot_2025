package frc.robot;

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

    // hulk
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(118.6253437499); //116.806640625
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(283.7109375); //282.919921875
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(124.0988159179); //121.640624999
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(38.75976562499); //227.548
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4_L2;
    // public static final String CANIVORE_BUS_NAME = "";

    //nemo
    public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(336.094);
    public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(225.176);
    public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(243.369);
    public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(204.256);
    public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    public static final String CANIVORE_BUS_NAME = "CANivore Bus 1";
    public static final double LIMELIGHT_FORWARD_OFFSET = 0.13335; //limelight x offset from center of robot
    public static final double LIMELIGHT_SIDE_OFFSET = -0.30559;
    public static final double LIMELIGHT_UP_OFFSET = 0.314325;
    public static final double LIMELIGHT_YAW_OFFSET = 0.0;
    public static final double LIMELIGHT_PITCH_OFFSET = 0.0;
    public static final double LIMELIGHT_ROLL_OFFSET = 0.0;
    public static final double CENTER_TO_BUMPER_OFFSET = 0.3937;

    // dory
    // public static final double FRONT_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(98.173828125);
    // public static final double FRONT_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(149.23828125);
    // public static final double BACK_LEFT_MODULE_STEER_OFFSET = -Math.toRadians(333.10546875);
    // public static final double BACK_RIGHT_MODULE_STEER_OFFSET = -Math.toRadians(102.8320312500);
    // public static final MechanicalConfiguration MODULE_CONFIGURATION = SdsModuleConfigurations.MK4I_L2;
    // public static final String CANIVORE_BUS_NAME = "";
    // public static final double LIMELIGHT_FORWARD_OFFSET = 0.36195;
    // public static final double LIMELIGHT_SIDE_OFFSET = -0.2413;
    // public static final double LIMELIGHT_UP_OFFSET = 0.200025;
    // public static final double LIMELIGHT_YAW_OFFSET = 2.0;
    // public static final double LIMELIGHT_PITCH_OFFSET = 28.0;
    // public static final double LIMELIGHT_ROLL_OFFSET = 0.0;

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

    public static final double LOOPTIME_SECONDS = 0.02;
}
