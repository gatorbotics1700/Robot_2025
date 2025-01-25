package frc.robot.commands;

import edu.wpi.first.hal.can.CANStatus;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.subsystems.BlinkinLEDController;
import frc.robot.subsystems.BlinkinLEDController.BlinkinPattern;


public class LEDsControlCommand extends InstantCommand{
    private final BlinkinLEDController blinkinLEDController;
    private final BlinkinPattern blinkinPattern;
    
    public LEDsControlCommand(BlinkinLEDController blinkinLEDController, BlinkinPattern blinkinPattern) {
        this.blinkinLEDController = blinkinLEDController;
        this.blinkinPattern = blinkinPattern;

        // Declare subsystem dependencies
        addRequirements(blinkinLEDController);
    }

    @Override
    public void initialize() {
        System.out.println("Setting LED pattern to: " + blinkinPattern.name() + " (" + blinkinPattern.value + ")");
        blinkinLEDController.setPattern(blinkinPattern);
    }

}