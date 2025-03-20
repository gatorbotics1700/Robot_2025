package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;

import java.util.function.DoubleSupplier;

public class PointToReefCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final XboxController controller;
   

    public PointToReefCommand(DrivetrainSubsystem drivetrain, XboxController controller) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        addRequirements(drivetrain);
    }

    @Override
    public void initialize() {
        // System.out.println("TeleopDriveCommand initialized - stopping drivetrain");
        drivetrain.setNotAtDesiredPose();
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public void execute() {
        drivetrain.faceReef();
    }

    @Override
    public void end(boolean interrupted) {
        // System.out.println("TeleopDriveCommand ended");
        drivetrain.drive(new ChassisSpeeds(0.0, 0.0, 0.0));
    }

    @Override
    public boolean isFinished() {
        if(drivetrain.getAtDesiredPose()){
            return true;
        }
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.2 ||
        Math.abs(controller.getLeftY()) > 0.2 ||
        Math.abs(controller.getRightX()) > 0.2 ||
        Math.abs(controller.getRightY()) > 0.2;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            return true;
        }
        return false;
    }
}