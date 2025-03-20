package frc.robot.subsystems;

import com.ctre.phoenix6.configs.MotorOutputConfigs;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.InvertedValue;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class RolyWheelsSubsystem extends SubsystemBase {
    public final TalonFX motor;
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);

    public RolyWheelsSubsystem(){
        motor = new TalonFX(Constants.MOTOR, Constants.CANIVORE_BUS_NAME);

        motor.getConfigurator().apply(new TalonFXConfiguration()
        .withMotorOutput(new MotorOutputConfigs()
        .withInverted(InvertedValue.Clockwise_Positive)));
    }
    
    @Override
    public void periodic(){
    }

    public void setMotorSpeed(double speed){
        motor.setControl(dutyCycleOut.withOutput(speed));
    }




    

}
