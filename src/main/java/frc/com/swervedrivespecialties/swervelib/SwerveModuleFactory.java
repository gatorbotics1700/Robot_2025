package frc.com.swervedrivespecialties.swervelib;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;

public class SwerveModuleFactory<DC, SC> {
    private final MechanicalConfiguration mechConfiguration;
    private final DriveControllerFactory<?, DC> driveControllerFactory;
    private final SteerControllerFactory<?, SC> steerControllerFactory;

    public SwerveModuleFactory(MechanicalConfiguration mechConfiguration,
                               DriveControllerFactory<?, DC> driveControllerFactory,
                               SteerControllerFactory<?, SC> steerControllerFactory) {
        this.mechConfiguration = mechConfiguration;
        this.driveControllerFactory = driveControllerFactory;
        this.steerControllerFactory = steerControllerFactory;
    }

    public SwerveModule create(DC driveConfiguration, String driveCanbus, SC steerConfiguration, String steerCanbus) {
        var driveController = driveControllerFactory.create(
                driveConfiguration, 
                driveCanbus,
                mechConfiguration
        );
        var steerController = steerControllerFactory.create(
                steerConfiguration, 
                steerCanbus,
                mechConfiguration
        );

        return new ModuleImplementation(driveController, steerController);
    }

    public SwerveModule create(DC driveConfiguration, SC steerConfiguration) {
        var driveController = driveControllerFactory.create(
                driveConfiguration, 
                mechConfiguration
        );
        var steerController = steerControllerFactory.create(
                steerConfiguration, 
                mechConfiguration
        );

        return new ModuleImplementation(driveController, steerController);
    }

    public SwerveModule create(ShuffleboardLayout container, DC driveConfiguration, String driveCanbus, SC steerConfiguration, String steerCanbus) {
        var driveController = driveControllerFactory.create(
                container,
                driveConfiguration,
                driveCanbus,
                mechConfiguration
        );
        var steerContainer = steerControllerFactory.create(
                container,
                steerConfiguration,
                steerCanbus,
                mechConfiguration
        );

        return new ModuleImplementation(driveController, steerContainer);
    }

    public SwerveModule create(ShuffleboardLayout container, DC driveConfiguration, SC steerConfiguration) {
        var driveController = driveControllerFactory.create(
                container,
                driveConfiguration,
                mechConfiguration
        );
        var steerContainer = steerControllerFactory.create(
                container,
                steerConfiguration,
                mechConfiguration
        );

        return new ModuleImplementation(driveController, steerContainer);
    }

    private static class ModuleImplementation implements SwerveModule {
        private final DriveController driveController;
        private final SteerController steerController;

        private ModuleImplementation(DriveController driveController, SteerController steerController) {
            this.driveController = driveController;
            this.steerController = steerController;
        }

        @Override
        public MotorController getDriveMotor() {
            return driveController.getDriveMotor();
        }

        @Override
        public MotorController getSteerMotor() {
            return steerController.getSteerMotor();
        }

        @Override
        public AbsoluteEncoder getSteerEncoder() {
            return steerController.getSteerEncoder();
        }

        @Override
        public double getDriveVelocity() {
            return driveController.getStateVelocity();
        }

        @Override
        public double getDriveDistance() {
            return driveController.getStateDistance();
        }

        @Override
        public double getSteerAngle() {
            return steerController.getStateAngle();
        }

        @Override
        public void resetToAbsolute() {
            steerController.resetToAbsolute();
        }

        @Override
        public void set(double driveVoltage, double steerAngle) {
            steerAngle %= (2.0 * Math.PI);
            if (steerAngle < 0.0) {
                steerAngle += 2.0 * Math.PI;
            }

            double currentAngle = getSteerAngle();

            // [0, 2pi) - [0, 2pi) = (-2pi, 2pi)
            double difference = steerAngle - currentAngle;
            // Change the target angle so the difference is in the range [-pi, pi) instead of (-2pi, 2pi)
            if (difference >= Math.PI) {
                steerAngle -= 2.0 * Math.PI;
            } else if (difference < -Math.PI) {
                steerAngle += 2.0 * Math.PI;
            }
            difference = steerAngle - currentAngle; // Recalculate difference

            // If the difference is greater than 90 deg or less than -90 deg the drive can be inverted so the total
            // movement of the module is less than 90 deg
            if (difference > Math.PI / 2.0 || difference < -Math.PI / 2.0) {
                // Only need to add 180 deg here because the target angle will be put back into the range [0, 2pi)
                steerAngle += Math.PI;
                driveVoltage *= -1.0;
            }

            // Put the target angle back into the range [0, 2pi)
            steerAngle %= (2.0 * Math.PI);
            if (steerAngle < 0.0) {
                steerAngle += 2.0 * Math.PI;
            }

            driveController.setReferenceVoltage(driveVoltage);
            steerController.setReferenceAngle(steerAngle);
        }
    }
}
