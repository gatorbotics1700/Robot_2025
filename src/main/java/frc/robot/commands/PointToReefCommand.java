package frc.robot.commands;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants;
import frc.robot.Constants.Mode;
import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.SwerveDriveInterface;

import java.util.function.DoubleSupplier;

import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.AutoLogOutputManager;
public class PointToReefCommand extends Command {
    private final SwerveDriveInterface drive;
    @AutoLogOutput(key="PointToReef/Reef")
    private Pose2d reef;
    private boolean finished;
    @AutoLogOutput(key="PointToReef/TargetRotation")
    private Rotation2d targetRotation;
    @AutoLogOutput(key="PointToReef/RotationDelta")
    private Rotation2d delta;
    @AutoLogOutput(key="PointToReef/RotationCurrent")
    private Rotation2d current;
    public PointToReefCommand(SwerveDriveInterface drive) {
        AutoLogOutputManager.addObject(this);
        this.drive = drive;
        this.addRequirements(drive);
        var alliance = DriverStation.getAlliance();
        double reefX = 0;
        double reefY = 0;
        if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Red){
            reefX = Constants.RED_REEF_POSE.getX();
            reefY = Constants.RED_REEF_POSE.getY();

        } else if (alliance.isPresent() && alliance.get() == DriverStation.Alliance.Blue) {
            reefX = Constants.BLUE_REEF_POSE.getX();
            reefY = Constants.BLUE_REEF_POSE.getY();
        } else if (Constants.currentMode == Mode.SIM) {
            reefX = Constants.BLUE_REEF_POSE.getX();
            reefY = Constants.BLUE_REEF_POSE.getY();
        }
        reef = new Pose2d(reefX, reefY, new Rotation2d());
    }

    @Override
    public void initialize() {
        finished = false;
    }

    @Override
    public void execute() {
        finished = false;
        Pose2d currentPose = drive.getPose();
        current = currentPose.getRotation();
        double deltaX = reef.getX() - currentPose.getX();
        double deltaY = reef.getY() - currentPose.getY();
        targetRotation = new Rotation2d(Math.atan2(deltaY, deltaX));
        delta = new Rotation2d(MathUtil.angleModulus(currentPose.getRotation().minus(targetRotation).getRadians()));
        if (MathUtil.applyDeadband(delta.getDegrees(), 5) == 0.0) {
            finished = true;
        } else {
            drive.drive(new ChassisSpeeds(0.0, 0.0, -0.1*delta.getDegrees()));
        }
    }
    @Override
    public boolean isFinished() {
        return finished;
    }
}