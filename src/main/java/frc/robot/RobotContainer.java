package frc.robot;

import frc.robot.commands.DriveCommand;
import frc.robot.commands.LimelightControlCommand;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;

public class RobotContainer {
    private final DrivetrainSubsystem drivetrain = new DrivetrainSubsystem();
    private static final LimelightSubsystem m_limelightsub = new LimelightSubsystem();

    private final XboxController controller = new XboxController(0);

    public RobotContainer() {
        drivetrain.register();
        m_limelightsub.register();

        drivetrain.setDefaultCommand(new DriveCommand(
                drivetrain,
                () -> -modifyAxis(controller.getRightY()), // Axes are flipped here on purpose
                () -> -modifyAxis(controller.getRightX()),
                () -> -modifyAxis(controller.getLeftX())
        ));


        new Trigger(controller::getBackButtonPressed)
                .onTrue(new InstantCommand(drivetrain::zeroGyroscope));

        new Trigger(controller::getRightBumperPressed)
                .onTrue(new InstantCommand(drivetrain::setSlowDrive));
        new Trigger(controller::getAButtonPressed)
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 0));
        new Trigger(controller::getBButtonPressed)
                .onTrue(new LimelightControlCommand(m_limelightsub, drivetrain, 1));


    }

    //instant command means that it runs one time rather then run command which runs continuosly

    public DrivetrainSubsystem getDrivetrain() {
        return drivetrain;
    }

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

    private double modifyAxis(double value) {
        value = deadband(value, 0.05);

        // Square the axis
        value = Math.copySign(value * value, value);

        if(drivetrain.getSlowDrive()){
            return (0.5 * value);
        }

        return value;
    }

    public Command getLimelightCommand() {
        return new LimelightControlCommand(m_limelightsub, drivetrain, 1);
    }
}
