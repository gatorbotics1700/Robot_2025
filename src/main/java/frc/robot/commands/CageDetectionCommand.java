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

    public CageDetectionCommand(DrivetrainSubsystem drivetrainSubsystem, LimelightSubsystem limelightSubsystem, XboxController controller){
        this.drivetrainSubsystem = drivetrainSubsystem;
        this.limelightSubsystem = limelightSubsystem;
        this.controller = controller;

        addRequirements(limelightSubsystem, drivetrainSubsystem);
    }

    @Override
    public void initialize(){
        limelightSubsystem.setPipeline(4);
    }

    @Override
    public void execute(){
        //if (limelightSubsystem.getObjectArray()[0] == 1){
            drivetrainSubsystem.faceCenterCage();
        //}
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
