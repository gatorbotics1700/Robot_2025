package frc.robot.commands;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.LimelightHelpers;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;
import edu.wpi.first.wpilibj.XboxController;

public class CageDetectionCommand extends Command {
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final LimelightSubsystem limelightSubsystem;
    private final XboxController controller;
    private final boolean isRed;

    public CageDetectionCommand(DrivetrainSubsystem drivetrainSubsystem, LimelightSubsystem limelightSubsystem, XboxController controller, boolean isRed){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.controller = controller;
        this.isRed = isRed;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize(){
        if(isRed==true){
            limelightSubsystem.setPipeline(4);
        } else {
            limelightSubsystem.setPipeline(5);
        }  
    }

    @Override
    public void execute(){
        if (isRed==true){
            drivetrainSubsystem.faceCenterCage(4);
        } else {
            drivetrainSubsystem.faceCenterCage(5);
        }
    }

    @Override
    public boolean isFinished(){
        boolean joystickMoved = Math.abs(controller.getLeftX()) > 0.2 ||
                Math.abs(controller.getLeftY()) > 0.2 ||
                Math.abs(controller.getRightX()) > 0.2 ||
                Math.abs(controller.getRightY()) > 0.2;
        if (joystickMoved) {
            System.out.println("Joystick moved, ending command.");
            return true;
        }

        if(Math.abs(LimelightHelpers.getTX("limelight"))<Constants.CAGE_DEADBAND){
            System.out.println("Reached cage, ending command");
            return true;            
        }
        return false;
    }


}
