package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.RolyWheelsSubsystem;

public class RolyWheelsCommand extends Command {
    private RolyWheelsSubsystem rolyWheelsSubsystem;
    private final double speed;
    private double startTime;

    public RolyWheelsCommand(RolyWheelsSubsystem rolyWheelsSubsystem, double speed) {
        this.rolyWheelsSubsystem = rolyWheelsSubsystem;
        this.speed = speed;
        addRequirements(rolyWheelsSubsystem);
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
    }

    public void execute() {
        rolyWheelsSubsystem.setMotorSpeed(speed);

        if(speed > 0) {
            System.out.println("SHOOTING");
        }
    
    }

    @Override
    public boolean isFinished() {
        double timePassed = System.currentTimeMillis() - startTime;

        if (speed == 0) {
            System.out.println("MOTOR SPEED: 0, STOPPING");
            rolyWheelsSubsystem.setMotorSpeed(0);
            return true;
        } else if (speed > 0) {
            if (timePassed > 1500) {
                rolyWheelsSubsystem.setMotorSpeed(0);
                System.out.println("Finished shooting");
                return true;
            }
        }
        return false;
    }

}

