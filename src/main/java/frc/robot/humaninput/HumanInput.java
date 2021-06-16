package frc.robot.humaninput;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.laser.LaserCommand;
import frc.robot.nav.DriveBackCommand;
import frc.robot.nav.DriveStraightCommand;
import frc.robot.nav.RotateCommand;

public class HumanInput {
    
    XboxController xbox;
    private static JoystickButton laserButtonY, rotateButtonA, driveStraightButtonB, driveBackButtonX;

    public HumanInput() {
        xbox = new XboxController(1);
        laserButtonY = new JoystickButton(xbox, 4);
        rotateButtonA = new JoystickButton(xbox, 1);
        driveStraightButtonB = new JoystickButton(xbox, 2);
        driveBackButtonX = new JoystickButton(xbox, 3);
    }

    public double getLeftJoystick() {
        return xbox.getRawAxis(1);
    }

    public double getRightJoystick() {
        return xbox.getRawAxis(5);
    }

    public void registerButtons() {
        laserButtonY.whenPressed(new LaserCommand());
        rotateButtonA.whenPressed(new RotateCommand(90));
        driveStraightButtonB.whenPressed(new DriveStraightCommand(20));
        driveBackButtonX.whenPressed(new DriveBackCommand(20));

    }


}
