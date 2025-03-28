package frc.robot.config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Named GatorConfig because RobotConfig was already taken by wpilibj.
 * The intention here is  to replace Constants.java with this class.
 * Instead of having a bunch of commented out constants for different chassis,
 * we can just have a different config for each chassis, and load the right
 * one based on the RoboRIOserial number.
 */
public class GatorConfig {

    // these are still actual constants.
    // they're static so they can be reused in specific configs,
    // but they're private so that other places in the code still
    // go through a config object.

    //for a 25x25 dirvetrain
    private static final double DRIVETRAIN_TRACKWIDTH_METERS_25X25 = 0.508;
    private static final double DRIVETRAIN_WHEELBASE_METERS_25X25 = 0.508;

    //for a 30x30 drivetrain
    private static final double DRIVETRAIN_TRACKWIDTH_METERS_30X30 = 0.508 + 0.127;
    private static final double DRIVETRAIN_WHEELBASE_METERS_30X30 = 0.508 + 0.127;

    // Pigeon ID never changes
    private static final int DRIVETRAIN_PIGEON_ID = 6;

    // these are member variables for the actual config.
    private double driveTrainTrackWidthMeters;
    private double driveTrainWheelBaseMeters;
    private int driveTrainPigeonId;
    private double driveTrainFrontLeftModuleSteerOffset;
    private double driveTrainFrontRightModuleSteerOffset;
    private double driveTrainBackLeftModuleSteerOffset;
    private double driveTrainBackRightModuleSteerOffset;
    
    // your average run of the mill constructor
    public GatorConfig(
            double driveTrainTrackWidthMeters,
            double driveTrainWheelBaseMeters,
            int driveTrainPigeonId,
            double driveTrainFrontLeftModuleSteerOffset,
            double driveTrainFrontRightModuleSteerOffset,
            double driveTrainBackLeftModuleSteerOffset,
            double driveTrainBackRightModuleSteerOffset) {
        this.driveTrainTrackWidthMeters = driveTrainTrackWidthMeters;
        this.driveTrainWheelBaseMeters = driveTrainWheelBaseMeters;
        this.driveTrainPigeonId = driveTrainPigeonId;
        this.driveTrainFrontLeftModuleSteerOffset = driveTrainFrontLeftModuleSteerOffset;
        this.driveTrainFrontRightModuleSteerOffset = driveTrainFrontRightModuleSteerOffset;
        this.driveTrainBackLeftModuleSteerOffset = driveTrainBackLeftModuleSteerOffset;
        this.driveTrainBackRightModuleSteerOffset = driveTrainBackRightModuleSteerOffset;
    }

    public double getDriveTrainTrackWidthMeters() {
        return driveTrainTrackWidthMeters;
    }

    public double getDriveTrainWheelBaseMeters() {
        return driveTrainWheelBaseMeters;
    }

    public int getDriveTrainPigeonId() {
        return driveTrainPigeonId;
    }

    public double getDriveTrainFrontLeftModuleSteerOffset() {
        return driveTrainFrontLeftModuleSteerOffset;
    }

    public double getDriveTrainFrontRightModuleSteerOffset() {
        return driveTrainFrontRightModuleSteerOffset;
    }

    public double getDriveTrainBackLeftModuleSteerOffset() {
        return driveTrainBackLeftModuleSteerOffset;
    }

    public double getDriveTrainBackRightModuleSteerOffset() {
        return driveTrainBackRightModuleSteerOffset;
    }

    // this is a static method that returns a new config based on the RoboRIO serial number.
    // you can call this in robot container, drivetrainsubsystem, whatever.
    public static GatorConfig getConfig() {
        String serialNum;
        // try to read the serial number from the RoboRIO.
        // if we can't, log an error pick a good default to return.
        try {
            serialNum = edu.wpi.first.wpilibj.RobotController.getSerialNumber();
        } catch (Exception e) {
            System.err.println("Failed to read RoboRIO serial number. Defaulting to practice bot config.");
            return getCompBotConfig();
        }

        // based on the serial number, return the correct config.
        switch (serialNum) {
            case "031b1d4b": // Comp Bot
                return getCompBotConfig();
            case "nemo_serial": // Replace with actual serial
                return getNemoConfig();
            case "hulk_serial": // Replace with actual serial
                return getHulkConfig();
            case "dory_serial": // Replace with actual serial
                return getDoryConfig();
            default:
                // if we don't have a config for this robot, log an error and return the comp bot config.
                System.err.println("Unknown robot serial number: " + serialNum + ". Defaulting to comp bot config.");
                return getCompBotConfig();
        }
    }

    private static GatorConfig getCompBotConfig() {
        return new GatorConfig(
            DRIVETRAIN_TRACKWIDTH_METERS_25X25,
            DRIVETRAIN_WHEELBASE_METERS_25X25,
            DRIVETRAIN_PIGEON_ID,
            -268.0664, // Direct from Constants.java
            -231.3281, // Direct from Constants.java
            -169.3652, // Direct from Constants.java
            -52.1191   // Direct from Constants.java
        );
    }

    private static GatorConfig getNemoConfig() {
        return new GatorConfig(
            DRIVETRAIN_TRACKWIDTH_METERS_30X30,
            DRIVETRAIN_WHEELBASE_METERS_30X30,
            DRIVETRAIN_PIGEON_ID,
            336.094,    // These values were in degrees in Constants
            225.176,
            243.369,
            204.256
        );
    }

    private static GatorConfig getHulkConfig() {
        return new GatorConfig(
            DRIVETRAIN_TRACKWIDTH_METERS_25X25,
            DRIVETRAIN_WHEELBASE_METERS_25X25,
            DRIVETRAIN_PIGEON_ID,
            118.6253437499,
            283.7109375,
            124.0988159179,
            38.75976562499
        );
    }

    private static GatorConfig getDoryConfig() {
        return new GatorConfig(
            DRIVETRAIN_TRACKWIDTH_METERS_30X30,
            DRIVETRAIN_TRACKWIDTH_METERS_30X30,
            DRIVETRAIN_PIGEON_ID,
            98.173828125,
            149.23828125,
            333.10546875,
            102.8320312500
        );
    }
}