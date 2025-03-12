package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;
public class ScoreCommands{

    public static Command Shoot(CoralShooterSubsystem m_coralShooterSub){
            System.out.println("shooting!");
            CoralShooterCommand coralShooterCommand = new CoralShooterCommand(m_coralShooterSub,  Constants.CORAL_L4_SHOOTING_SPEED);
            return coralShooterCommand; //TODO: Change speed
    }
}
