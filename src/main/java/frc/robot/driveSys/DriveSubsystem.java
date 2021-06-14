package frc.robot.driveSys;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveSubsystem extends SubsystemBase {
    
    private static TalonSRX backLeftTalon, backRightTalon;
    private static ControlMode controlMode;
    private static FeedbackDevice feedbackDevice;

    public DriveSubsystem() {

        backLeftTalon = new TalonSRX(0);
        backRightTalon = new TalonSRX(1);

        controlMode = ControlMode.PercentOutput;
        feedbackDevice = FeedbackDevice.QuadEncoder;

        backLeftTalon.configSelectedFeedbackSensor(feedbackDevice, 0, 0);
        backRightTalon.configSelectedFeedbackSensor(feedbackDevice, 0, 0);
        
        backLeftTalon.setInverted(true);
        backRightTalon.setInverted(false);
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

    public static double getBackLeftEncoder() {
        return backLeftTalon.getSelectedSensorPosition();
    }

   
    public static double getBackRightEncoder() {
        return backRightTalon.getSelectedSensorPosition();
    }

  

    public static void displayEncoderValues() {
        System.out.println("Back Left Encoder Value: " + getBackLeftEncoder());
        System.out.println("Back Right Encoder Value: " + getBackRightEncoder());
    }
    
    public void stopMotors(){
        setTalons(0);
    }
    //get encoder distances

    public void resetEncoders(){
        backLeftTalon.setSelectedSensorPosition(0);
        backRightTalon.setSelectedSensorPosition(0);
    }

}