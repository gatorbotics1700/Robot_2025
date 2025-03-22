package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ElevatorSubsystem;
import frc.robot.subsystems.RolyWheelsSubsystem;

public class ElevatorCommand extends Command {
    private ElevatorSubsystem elevatorSubsystem;
    private final double targetPosition;
    private double startTime;

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
        if (targetPosition == elevatorSubsystem.getCurrent()) {
            return true;
        }
        return false;
    }

}

