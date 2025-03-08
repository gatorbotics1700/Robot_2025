package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Robot;
import frc.robot.RobotContainer;

public class TestingCommand extends Command {

    private final RobotContainer robotContainer = Robot.getRobotContainer();
    
    @Override
    public void execute(){
        System.out.println("VARIABLE: " + robotContainer.getVariable());
    }

    @Override
    public boolean isFinished(){
        return false;
    }

}
