package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.RolyWheelsSubsystem;

public class ElevatorCommand extends Command {
    private ElevatorSubsystem elevatorSubsystem;
    private final double targetPosition;
    final static double startingPosition = ElevatorSubsystem.getCurrent();
    
    
       // private double startTime;
    
        public ElevatorCommand(ElevatorSubsystem elevatorSubsystem, double targetPosition) {
            this.elevatorSubsystem = elevatorSubsystem;
            this.targetPosition = targetPosition;
            addRequirements(elevatorSubsystem);
    
        }
    
        @Override
        //public void initialize() {
            //startTime = System.currentTimeMillis();
      //  }
    
        public void execute() {
            elevatorSubsystem.setPosition(targetPosition);
            //if(speed > 0) {
                //System.out.println("SHOOTING");
           // }
        
        }
    
        @Override
        public boolean isFinished() {
            //double timePassed = System.currentTimeMillis() - startTime;
            double startingPosition = ElevatorCommand.startingPosition;
                if (targetPosition == ElevatorSubsystem.getCurrent()) {
                    return true;
                }else if(elevatorSubsystem.atTopLimitSwitch()){
                    return true;
                }else if(elevatorSubsystem.atBottomLimitSwitch()){
                    return true; 
                }else if((elevatorSubsystem.atTopLimitSwitch()||elevatorSubsystem.atBottomLimitSwitch())&& startingPosition == ElevatorSubsystem.getCurrent()){
            return false;
        }
        return false;
    }

    //make variable for the starting position 
    //If at top limit switch AND  current position equals starting position then return false for isFinished
    //If at bottom limit switch ANd current position equals starting position then return false for isFinished

}

