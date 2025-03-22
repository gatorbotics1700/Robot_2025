package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

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
     public static TalonFX motor;
               private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
               private DigitalInput topLimitSwitch = new DigitalInput(Constants.TOP_LIMIT_SWITCH_PORT);
               private  DigitalInput bottomLimitSwitch = new DigitalInput(Constants.BOTTOM_LIMIT_SWITCH_PORT);
                  
               
               
                    public ElevatorSubsystem(){
                       this.PIDcontroller = new PIDController(KP, KI, KD);
                       motor = new TalonFX(Constants.ELEVATOR_MOTOR);
          
                  motor.getConfigurator().apply(new TalonFXConfiguration()
                  .withMotorOutput(new MotorOutputConfigs()
                  .withInverted(InvertedValue.Clockwise_Positive)));
               }
          
               public void setSpeed(double speed){
                motor.setControl(dutyCycleOut.withOutput(speed));
          
          
               }
          
               public void setPosition(double target){
                  double current = motor.getPosition().getValueAsDouble();
                  target = target*(Math.PI * 1.22/Constants.KRAKEN_TICKS_PER_REV);
                  double speed = PIDcontroller.calculate(current, target);
                  motor.setControl(dutyCycleOut.withOutput(speed));
                  if (current==target){
                   motor.setControl(dutyCycleOut.withOutput(0));
                  }
               }
          
               public static double getCurrent() {
                return motor.getPosition().getValueAsDouble();
          }
     
          public boolean atTopLimitSwitch(){
                  return topLimitSwitch.get();


     }

     public boolean atBottomLimitSwitch(){
                  return bottomLimitSwitch.get();
     }


}
