package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Transform2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
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
        // boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.2 ||
        // Math.abs(controller.getLeftY()) > 0.2 ||
        // Math.abs(controller.getRightX()) > 0.2 ||
        // Math.abs(controller.getRightY()) > 0.2;
        // if (joystickMoved) {
        //     System.out.println("Joystick moved, ending command.");
        //     return true;
        // }
        if(!DriverStation.getAlliance().isPresent()){
            return true;
        }
        return false;
    }

    private Translation2d getTagCoords(){
        double rotation = 0;
        Translation2d reefCenter = Constants.BLUE_REEF_POSE;
        var alliance = DriverStation.getAlliance();
        if(quadrant == 1){
           rotation = 0;
        }else if(quadrant == 2){
            rotation = -60;
        }
        else if(quadrant == 3){
            rotation = -120;
        }
        else if(quadrant == 4){
            rotation = 180;
        }
        else if(quadrant == 5){
            rotation = 120;
        } else if (quadrant == 6){
            rotation = 60;
        }else{     
            System.out.println("INVALID QUADRANT");
            return new Translation2d();
        }
        if(alliance.equals(DriverStation.Alliance.Red)){
            reefCenter = Constants.RED_REEF_POSE;
            rotation += 180;
            
        }
        rotation = MathUtil.angleModulus(Math.toRadians(rotation)); //now it's in radians
        Pose2d reefPose = new Pose2d(reefCenter, new Rotation2d(rotation));
        Transform2d centerToTag = new Transform2d(new Translation2d(Constants.REEF_RADIUS,0), new Rotation2d());
        Pose2d aprilTagCoord = reefPose.transformBy(centerToTag);
        return new Translation2d(aprilTagCoord.getX(), aprilTagCoord.getY());
    }
}