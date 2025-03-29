package frc.robot.commands;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DrivetrainSubsystem;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.function.DoubleSupplier;

public class PointToTagCommand extends Command {
    private final DrivetrainSubsystem drivetrain;
    private final XboxController controller;
    private final int quadrant;
   

    public PointToTagCommand(DrivetrainSubsystem drivetrain, XboxController controller, int quadrant) {
        this.drivetrain = drivetrain;
        this.controller = controller;
        this.quadrant = quadrant;
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
        drivetrain.facePoint(getTagCoords());
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
        if(!DriverStation.getAlliance().isPresent()){
            return true;
        }
        return false;
    }

    private Translation2d getTagCoords(){
        var alliance = DriverStation.getAlliance();
        if(quadrant == 1){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 21
                return new Translation2d(5.321046,4.0259);
            }else{
                //tagID = 10
                return new Translation2d(12.227306,	4.0259);
            }
        }else if(quadrant == 2){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 22
                return new Translation2d(4.90474,	3.306318);
            }else{
                //tagID = 9
                return new Translation2d(12.643358,	4.745482);
            }
        }
        else if(quadrant == 3){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 17
                return new Translation2d(4.073906,3.306318);
            }else{
                //tagID = 8
                return new Translation2d(13.474446,4.745482);
            }
        }
        else if(quadrant == 4){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 18
                return new Translation2d(3.6576,4.0259);
            }else{
                //tagID = 7
                return new Translation2d(13.890498,4.0259);
            }
        }
        else if(quadrant == 5){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 19
                return new Translation2d(4.073906,4.745482);
            }else{
                //tagID = 6
                return new Translation2d(13.474446,3.306318);
            }
        } else if (quadrant == 6){
            if(alliance.get() == DriverStation.Alliance.Blue){
                //tagID = 20
                return new Translation2d(4.90474,4.745482);
            }else{
                //tagID = 11
                return new Translation2d(12.643358,3.306318);
            }
        }
        System.out.println("INVALID QUADRANT");
        return new Translation2d();
    }
}