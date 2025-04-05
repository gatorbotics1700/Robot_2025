package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfigurator;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class CoralShooterSubsystem extends SubsystemBase{
    public final TalonFX topMotorLeft;
    public final TalonFX topMotorRight;
    // public final TalonFX bottomMotor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    private static double voltageTune = 0.0;
    private static double voltagE; //anaika is anti this. so this was the solution.... the typo is in fact intentional :/
    private final DigitalInput limitSwitch;
    
    public CoralShooterSubsystem(){
        topMotorLeft = new TalonFX(Constants.SHOOTER_MOTOR_TOP_LEFT_CAN_ID, Constants.CANIVORE_BUS_NAME);
        topMotorRight = new TalonFX(Constants.SHOOTER_MOTOR_TOP_RIGHT_CAN_ID, Constants.CANIVORE_BUS_NAME);
        // bottomMotor = new TalonFX(Constants.SHOOTER_MOTOR_BOTTOM_ID, Constants.CANIVORE_BUS_NAME);

        // topMotorRight.setInverted(true);
        topMotorRight.getConfigurator().apply(new TalonFXConfiguration()
            .withMotorOutput(new MotorOutputConfigs()
                .withInverted(InvertedValue.Clockwise_Positive)));
        // bottomMotor.getConfigurator().apply(new TalonFXConfiguration()
        //     .withMotorOutput(new MotorOutputConfigs()
        //         .withInverted(InvertedValue.Clockwise_Positive)));
       limitSwitch = new DigitalInput(9);
       voltagE = Constants.CORAL_L4_SHOOTING_VOLTAGE;
    }

    @Override
    public void periodic(){
        // SmartDashboard.putNumber("bottom motor current",  getBottomMotorStatorCurrent());
        SmartDashboard.putNumber("top motor left current", getTopMotorLeftStatorCurrent());
        SmartDashboard.putNumber("shooting voltage", voltagE);
        SmartDashboard.putBoolean("shooter limit switch", getLimitSwitch());
       
    }

    // public void setSpeed(double speed){
    //     topMotorLeft.setControl(dutyCycleOut.withOutput(speed));
    //     topMotorRight.setControl(dutyCycleOut.withOutput(speed));
    //     bottomMotor.setControl(dutyCycleOut.withOutput(speed));
    //    // System.out.println("motor stator current: " + motor.getStatorCurrent() + ", motor2 stator current: " + motor2.getStatorCurrent());
    // }

    // public void setTopMotorSpeed(double speed){
    //     topMotorLeft.setControl(dutyCycleOut.withOutput(speed));
    //     topMotorRight.setControl(dutyCycleOut.withOutput(speed));
    // }

    public void setMotorVoltage(double voltage){
        if(voltage<-8){
            voltagE = voltage - voltageTune;
        }else{
            voltagE = voltage;
        }
        
        System.out.println("voltage we are setting the motor to: "+voltagE);
        topMotorLeft.setVoltage(voltagE);
        topMotorRight.setVoltage(voltagE);
        // bottomMotor.setVoltage(voltage);
        //voltagE = voltage;
    }

    public void setBottomMotorSpeed(double speed){
        // bottomMotor.setControl(dutyCycleOut.withOutput(speed));
    }

    public double getTopMotorLeftStatorCurrent(){
        return topMotorLeft.getStatorCurrent().getValueAsDouble();
    }

    // public double getBottomMotorStatorCurrent(){
    //     return bottomMotor.getStatorCurrent().getValueAsDouble();
    // }

    public double getTopMotorLeftSpeed(){
        return topMotorLeft.getVelocity().getValueAsDouble();
    }

    public double getTopMotorRightSpeed(){
        return topMotorRight.getVelocity().getValueAsDouble();
    }

    // public double getBottomMotorSpeed(){
    //     return bottomMotor.getVelocity().getValueAsDouble();
    // }

    public boolean getLimitSwitch(){
        return limitSwitch.get();
    }

    public void increaseVoltageTune(){
        voltageTune += 0.1;
        System.out.print("New Voltage= " + (voltagE + voltageTune));
    }

    public void decreaseVoltageTune(){
        voltageTune -= 0.1;
        System.out.print("New Voltage= " + (voltagE + voltageTune));
    }

    public double getVoltageTune(){
        return voltageTune;
    }

    public double getVoltage(){
        return voltagE;
    }

    public void setTopVoltage(double voltage){
        topMotorLeft.setVoltage(voltage);
        topMotorRight.setVoltage(voltage);
    }

    // public void setBottomVoltage(double voltage){
    //     bottomMotor.setVoltage(voltage);
    // }
}
