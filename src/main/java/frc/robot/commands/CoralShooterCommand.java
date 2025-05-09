package frc.robot.commands;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;

public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    private final double voltage;
    private double startTime;
    
    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem, double voltage){ //double speed){
        this.coralShooterSubsystem = coralShooterSubsystem; 
        this.voltage = voltage;
        addRequirements(coralShooterSubsystem);
    }
    
    @Override
    public void initialize (){
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute() {
        coralShooterSubsystem.setMotorVoltage(voltage);

        if(voltage > 0) {
            coralShooterSubsystem.setMotorVoltage(voltage);
            System.out.println("INTAKING");
            System.out.println("top left motor current: " + coralShooterSubsystem.getTopMotorLeftStatorCurrent());

        } else if (voltage < 0){
            System.out.println("SHOOTING");
            if(voltage == Constants.CORAL_L4_SHOOTING_VOLTAGE){
                coralShooterSubsystem.setTopVoltage(voltage);
                System.out.println("L4 L4 L4");
            } else if(voltage == Constants.CORAL_TROUGH_SHOOTING_VOLTAGE){
                coralShooterSubsystem.setMotorVoltage(voltage);
                System.out.println("TROUGH TROUGH TROUGH");
            }
        }
    }
    
    @Override
    public boolean isFinished() {  
        double timePassed = System.currentTimeMillis() - startTime;
        
        if(voltage > 0){ // if intaking
            if(timePassed > 5000){      //1500 - if no limit switch - 5000 - if lim switch
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println ("Finished intaking");
                return true;
            } else if (coralShooterSubsystem.getLimitSwitch()){ // if limit switch is pressed
                System.out.println("limit switch triggered -- ending intaking");
                coralShooterSubsystem.setMotorVoltage(0);
                return true;
            }
            
        } else if (voltage == 0){
            System.out.println("MOTOR VOLTAGE: 0, STOPPING");
            coralShooterSubsystem.setMotorVoltage(0);
            return true;
        //if moving up after intaking
        } else if (voltage == Constants.CORAL_PREINTAKE_SHOOTING_VOLTAGE){
            if(timePassed > 10){
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println("pre intake move up done");
                return true;
            }
        } else if(voltage < -1.0){
            if(timePassed > 1500){
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println("Finished shooting");
                return true;
            }
        }
        return false;
    }
}