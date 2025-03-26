package frc.robot.subsystems;

import com.ctre.phoenix6.controls.DutyCycleOut;
import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.signals.NeutralModeValue;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ClimbingSubsystem extends SubsystemBase{
    private final DutyCycleOut dutyCycleOut = new DutyCycleOut(0);
    DigitalInput baseLimitSwitch = new DigitalInput(0);
    DigitalInput shooterLeftLimitSwitch = new DigitalInput(1);
    DigitalInput shooterRightLimitSwitch = new DigitalInput(2);
    
    private final TalonFX motor;

    public ClimbingSubsystem() {
        motor = new TalonFX(Constants.CLIMBING_MOTOR_CAN_ID, Constants.CANIVORE_BUS_NAME);
        motor.setNeutralMode(NeutralModeValue.Brake);
    }

    @Override
    public void periodic() {
        SmartDashboard.putBoolean("base limit switch",  baseLimitSwitch.get());
        SmartDashboard.putBoolean("shooter left limit switch",  shooterLeftLimitSwitch.get());
        SmartDashboard.putBoolean("shooter right limit switch",  shooterRightLimitSwitch.get());
    }

    public void setSpeed(double speed) {
        System.out.println("Setting climbing speed");
        motor.setControl(dutyCycleOut.withOutput(speed)); 
    }
    
    public boolean isAnyLimitSwitchPressed() {
        return baseLimitSwitch.get() || shooterLeftLimitSwitch.get() || shooterRightLimitSwitch.get();
    }
}
