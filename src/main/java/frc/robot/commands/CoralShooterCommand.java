package frc.robot.commands;
import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.CoralShooterSubsystem;

public class CoralShooterCommand extends Command {
    private CoralShooterSubsystem coralShooterSubsystem;
    private final double voltage;
    // private final double speed;
    private double startTime;
    private boolean shootingCurrentPeaked; 
    private boolean intakeCurrentPeaked; 
    private boolean readyToShoot; 
    private boolean lim;
    
    public CoralShooterCommand(CoralShooterSubsystem coralShooterSubsystem, double voltage){ //double speed){
        this.coralShooterSubsystem = coralShooterSubsystem; 
        // this.speed = speed;
        this.voltage = voltage;
        addRequirements(coralShooterSubsystem);
    }
    
    @Override
    public void initialize (){
        startTime = System.currentTimeMillis();
    }
    
    @Override
    public void execute() {
            // if (coralShooterSubsystem.getLimitSwitch() ==true){
            //     lim = false;
            // } else{
            //     lim = true;
            // }
            //coralShooterSubsystem.setSpeed(speed);
            coralShooterSubsystem.setMotorVoltage(voltage);
            // SmartDashboard.putNumber("shooter speed", coralShooterSubsystem.getTopMotorLeftSpeed());
            // SmartDashboard.putNumber("velocity delta", speed - coralShooterSubsystem.getBottomMotorSpeed());
       
        
        // if(speed > 0) {
        //     System.out.println("INTAKING");
        // } else if (speed < 0) {
        //     System.out.println ("SHOOTING");
        //     if(speed == Constants.CORAL_L4_SHOOTING_SPEED){
        //         System.out.println("L4 L4 L4");
        //     } else if (speed == Constants.CORAL_TROUGH_SHOOTING_SPEED){
        //         System.out.println("TROUGH TROUGH TROUGH");
        //     }
        // }

        if(voltage > 0) {
            coralShooterSubsystem.setMotorVoltage(voltage);
            System.out.println("INTAKING");
            System.out.println("top left motor current: " + coralShooterSubsystem.getTopMotorLeftStatorCurrent());

        } else if (voltage < 0){
            System.out.println("SHOOTING");
            if(voltage == Constants.CORAL_L4_SHOOTING_VOLTAGE){
                coralShooterSubsystem.setTopVoltage(voltage);
                if(coralShooterSubsystem.getTopMotorLeftSpeed() >= Constants.CORAL_L4_SHOOTING_SPEED && coralShooterSubsystem.getTopMotorRightSpeed() == coralShooterSubsystem.getTopMotorLeftSpeed()){
                    readyToShoot = true;
                }
                // if(readyToShoot){
                //     coralShooterSubsystem.setBottomVoltage(voltage);
                // }
                System.out.println("L4 L4 L4");
            } else if(voltage == Constants.CORAL_TROUGH_SHOOTING_VOLTAGE){
                coralShooterSubsystem.setMotorVoltage(voltage);
                System.out.println("TROUGH TROUGH TROUGH");
            }
        }
        // System.out.println("MOTOR VOLTAGE: " + voltage); 
        // System.out.println("SPEED: " + speed);
        // System.out.println("battery voltage: " + RobotController.getBatteryVoltage());   
    }
    
    @Override
    public boolean isFinished() {  
        double timePassed = System.currentTimeMillis() - startTime;
        // System.out.println("Milliseconds passed: " +  timePassed);  
        
        // System.out.println("MOTOR2 CURRENT: " + coralShooterSubsystem.getBottomMotorStatorCurrent());
        
        //if(speed > 0){ // if intaking
        if(voltage > 0){
            if(timePassed > 5000){      //1500 - if no limit switch - 5000 - if lim switch
                // coralShooterSubsystem.setSpeed(0);
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println ("Finished intaking");
                return true;
            }
            // } else if(coralShooterSubsystem.getTopMotorLeftStatorCurrent() < Constants.CORAL_INTAKING_CURRENT_LIMIT && timePassed > 1000){ 
            //     // intakeCurrentPeaked = true; // notifies us that the coral in the shooter, still outtaking
            //     System.out.println("INTAKING CURRENT PEAKED: " + coralShooterSubsystem.getTopMotorLeftStatorCurrent());
            //     //coralShooterSubsystem.setSpeed(0);
            //     coralShooterSubsystem.setMotorVoltage(0);
            //     return true;

            else if (coralShooterSubsystem.getLimitSwitch()){//lim == false){
                System.out.println("limit switch triggered -- ending intaking");
                coralShooterSubsystem.setMotorVoltage(0);
                return true;
            }
            
            
            // else if(intakeCurrentPeaked && coralShooterSubsystem.getTopMotorLeftStatorCurrent() < Constants.CORAL_INTAKE_MIN_CURRENT){ // if the coral has left the shooter
            //     coralShooterSubsystem.setMotorVoltage(0); // stop because we have finished outtaking
            //     shootingCurrentPeaked = false;
            //     return true;
            // }
        //} else if(speed == 0){ // if stopped, end command
        } else if (voltage == 0){
            System.out.println("MOTOR VOLTAGE: 0, STOPPING");
            coralShooterSubsystem.setMotorVoltage(0);
            // System.out.println("SPEED: 0, STOPPING");
            return true;
        //if moving up after intaking
        } else if (voltage == Constants.CORAL_PREINTAKE_SHOOTING_VOLTAGE){
            if(timePassed > 90){
                // coralShooterSubsystem.setSpeed(0);
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println("pre intake move up done");
                return true;
            }
     //} else if(speed < 0){ // if shooting and not just moving up
        } else if(voltage < -1.0){
            if(timePassed > 1500){
                // coralShooterSubsystem.setSpeed(0);
                coralShooterSubsystem.setMotorVoltage(0);
                System.out.println("Finished shooting");
                return true;
            }
            //  else if(coralShooterSubsystem.getMotorStatorCurrent() > Constants.CORAL_SHOOTING_MAX_CURRENT){ 
            //     shootingCurrentPeaked = true; // notifies us that the coral in the shooter, still outtaking
            //     System.out.println("SHOOTING CURRENT PEAKED: " + coralShooterSubsystem.getMotorStatorCurrent());
            // } else if(shootingCurrentPeaked && coralShooterSubsystem.getMotorStatorCurrent() < Constants.CORAL_SHOOTING_MIN_CURRENT){ // if the coral has left the shooter
            //     coralShooterSubsystem.setVoltage(0); // stop because we have finished outtaking
            //     shootingCurrentPeaked = false;
            //     return true;
            // }
        }
        return false;
    }
}