package frc.robot.driveSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Robot;

public class DriveSubsystem extends SubsystemBase {
    
    private static TalonSRX backLeftTalon, backRightTalon;
    private static ControlMode controlMode;
    private static FeedbackDevice feedbackDevice;
    private static final double WHEEL_DIAMETER = 8.25;
    private static final double ENC_ROTATION = 1440;
    private static final double PERCENT_OUTPUT = 0.5;

    public DriveSubsystem() {

        backLeftTalon = new TalonSRX(0);
        backRightTalon = new TalonSRX(1);

        controlMode = ControlMode.PercentOutput;
        feedbackDevice = FeedbackDevice.QuadEncoder;

        backLeftTalon.configSelectedFeedbackSensor(feedbackDevice, 0, 0);
        backRightTalon.configSelectedFeedbackSensor(feedbackDevice, 0, 0);

        backLeftTalon.setInverted(false);
        backRightTalon.setInverted(true);
    }

    public void setLeftTalons(double speed) {
        backLeftTalon.set(controlMode, speed);
    }

    public void setRightTalons(double speed) {
        backRightTalon.set(controlMode, speed);
    }

    public void setTalons(double speed) {
        setLeftTalons(speed);
        setRightTalons(speed);
    }

    public double getBackLeftEncoder() {
        return backLeftTalon.getSelectedSensorPosition();
    }

   
    public double getBackRightEncoder() {
        return backRightTalon.getSelectedSensorPosition();
    }

    public void resetEncoders() {
        backLeftTalon.setSelectedSensorPosition(0);
        backRightTalon.setSelectedSensorPosition(0);
    }

    public void displayEncoderValues() {
        System.out.println("Back Left Encoder Value: " + getBackLeftEncoder());
        System.out.println("Back Right Encoder Value: " + getBackRightEncoder());
    }
    
    public void stopMotors() {
        backLeftTalon.set(ControlMode.Disabled, 0);
        backRightTalon.set(ControlMode.Disabled, 0);

    }
    //get encoder distances
    public double getEncoderDistance() {
        double avgEncoders = (getBackLeftEncoder() + getBackRightEncoder()) / 2;
        //double distance = avgEncoders * WHEEL_DIAMETER * Math.PI / ENC_ROTATION;
        double distance = avgEncoders / 53.5;
        return distance;

    }

    public void teleopDrive() {
        setLeftTalons(-Robot.input.getLeftJoystick() * PERCENT_OUTPUT);
        setRightTalons(-Robot.input.getRightJoystick() * PERCENT_OUTPUT);
    }

    @Override
    public void periodic() {
        setDefaultCommand(new DriveCommand());
    }

}