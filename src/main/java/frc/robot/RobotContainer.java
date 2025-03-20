package frc.robot;

import frc.robot.commands.RolyWheelsCommand;
import frc.robot.commands.TeleopDriveCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.RolyWheelsSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    // private final DrivetrainSubsystem drivetrainSubsystem = new DrivetrainSubsystem();
    private final RolyWheelsSubsystem rolyWheelsSubsystem = new RolyWheelsSubsystem();
    
    private final XboxController controller_1 = new XboxController(0);
    private final XboxController controller_2 = new XboxController(1);
    

    public RobotContainer() {
        
        // Print initial joystick values
        System.out.println("RobotContainer initializing");

        // Zero gyroscope button binding
    //    // new Trigger(controller_1::getBackButtonPressed)
    //             .onTrue(new InstantCommand(drivetrainSubsystem::zeroGyroscope));

    //    // new Trigger(controller_1::getRightBumperPressed)
    //             .onTrue(new InstantCommand(drivetrainSubsystem::setSlowDrive));

        new Trigger(controller_2::getBButtonPressed)
                .onTrue(new RolyWheelsCommand(rolyWheelsSubsystem, 0.2));
                
        new Trigger(controller_2::getAButtonPressed)
                .onTrue(new RolyWheelsCommand(rolyWheelsSubsystem, 0.0));
            
    }

    // public void setDefaultTeleopCommand(){
    //     System.out.println("SETTING DEFAULT TELEOP COMMAND");
    //     drivetrainSubsystem.setDefaultCommand(
    //         new TeleopDriveCommand(
    //             drivetrainSubsystem,
    //             () -> -modifyAxis(0.9*controller_1.getRightY()),    // Changed to raw values
    //             () -> -modifyAxis(0.9*controller_1.getRightX()),     // Changed to raw values
    //             () -> -modifyAxis(0.8*controller_1.getLeftX())    // Changed to raw values
    //         )
    //     );
    // }

    // public DrivetrainSubsystem getDrivetrainSubsystem(){
    //     return drivetrainSubsystem;
    // }


    private double deadband(double value, double deadband) {
        if (Math.abs(value) > deadband) {
            if (value > 0.0) {
                return (value - deadband) / (1.0 - deadband);
            } else {
                return (value + deadband) / (1.0 - deadband);
            }
        } else {
            return 0.0;
        }
    }

    // private double modifyAxis(double value) {
    //     value = deadband(value, 0.05);

    //     // Square the axis
    //     value = Math.copySign(value * value, value);

    //     if(drivetrainSubsystem.getSlowDrive()){
    //         return (0.5 * value);

    //     }

    //     return value;
    // }
}
