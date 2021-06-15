package frc.robot.laser;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class LaserSubsystem extends SubsystemBase {
    private DigitalOutput laser = new DigitalOutput(0);
    private Servo motorServo = new Servo(1);
    public LaserSubsystem(){}

    public void setServo(double value) {
        motorServo.set(value);
    }
    public void setServoSpeed(double value) {
        double kp = 0.01;
        double preValue = motorServo.get();
        motorServo.set(preValue + kp * value);
    }
    public void laserOn() {
        laser.set(true);
    }
    public void laserOff() {
        laser.set(false);
    }
    @Override
    public void periodic() {
        setDefaultCommand(new ServoMotorCommand(0));
    }
}