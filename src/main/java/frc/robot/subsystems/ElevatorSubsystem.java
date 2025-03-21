package frc.robot.subsystems;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class ElevatorSubsystem extends SubsystemBase{
    /*
    create pid controller type PID
    PID values tune 
    use set position method to set position of elevator
    use position of PID controller to clauclate a specific output speed
    calcuate method uses ticks5/*
     */
    

     private final PIDController PIDcontroller;

     private final double KP = 0.4;
     private final double KI = 10;
     private final double KD = 2;


     public ElevatorSubsystem(){
        this.PIDcontroller = new PIDController(KP, KI, KD);
     }

     public double setPosition(double current, double target){
        current = current*(Math.PI*1.22/2048);
        target = target*(Math.PI*1.22/2048);
        double speed = PIDcontroller.calculate(current, target);

     }
}
