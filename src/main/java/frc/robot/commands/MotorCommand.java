package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingSubsystem;
import frc.robot.subsystems.MotorSubsystem;
public class MotorCommand extends Command {
     private MotorSubsystem motorSubsystem; 
    private final double speed;

    public MotorCommand(MotorSubsystem motorSubsystem, double speed) {
        this.motorSubsystem = motorSubsystem; 
        this.speed = speed; 
        addRequirements(motorSubsystem); 
    }
    
    @Override
    public void execute() {
        motorSubsystem.setSpeed(speed);
    }

    @Override 
    public boolean isFinished() {
        if (speed > 0 ) {
            motorSubsystem.setSpeed(0);
            return true;
        }
        return false; 
  
    }
    
}
