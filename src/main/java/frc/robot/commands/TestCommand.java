package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;

public class TestCommand extends Command {
    
    public TestCommand(){
        
    }

    @Override
    public void execute(){
        System.out.println("PRESSING BUTTON ONE!!!!!!!!!!!!!!");
    }

    @Override
    public boolean isFinished(){
        return false;
    }
}
