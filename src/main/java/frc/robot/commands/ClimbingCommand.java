package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ClimbingSubsystem;

public class ClimbingCommand extends Command {
   
    private ClimbingSubsystem climbingSubsystem; 
    
    private final double speed;
    
    private double startTime;

    
    public ClimbingCommand(ClimbingSubsystem climbingSubsystem, double speed) {
        this.climbingSubsystem = climbingSubsystem; 
        this.speed = speed; 
        addRequirements(climbingSubsystem); 
    }

    @Override
    public void initialize() {
        startTime = System.currentTimeMillis();
        System.out.println("CLIMBING COMMAND");
    }
    
    @Override
    public void execute() {
        climbingSubsystem.setSpeed(speed);

        // TODO: FYI these inequalities have been reversed to match the change in the climbing buttons
        if (speed < 0) {
            System.out.println("CLIMBING");
        } else if (speed > 0) {
            System.out.println("DETACHING");
        } else if (speed == 0) {
            System.out.println("CLIMBER STOPPING");
        }
    }

    @Override 
    public boolean isFinished() {

        double timePassed = System.currentTimeMillis() - startTime;
        System.out.println("Milliseconds passed: " +  timePassed);  
        
        // TODO did not change these inequalities - not sure why they match with our changes already  
        if(speed > 0){ // if detatching / reverse climbing
            if(System.currentTimeMillis() - startTime > 30000){
                climbingSubsystem.setSpeed(0);
                System.out.println ("Finished detaching - TIMED OUT");
                return true;
            }
        } else if(speed == 0){
            climbingSubsystem.setSpeed(0);
            return true;
        } else if(speed < 0){ // if climbing
            if(System.currentTimeMillis() - startTime > 30000){
                climbingSubsystem.setSpeed(0);
                System.out.println("Finished climbing - TIMED OUT");
                return true;
            } 
        }
        if(climbingSubsystem.isAnyLimitSwitchPressed()){
            climbingSubsystem.setSpeed(0);
            System.out.println("LIMIT SWITCH TRIGGERED");
            return true;
        }
        return false;
    }
}
