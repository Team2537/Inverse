package frc.robot.humaninput;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.laser.LaserCommand;
import frc.robot.nav.DriveStraightCommand;
import frc.robot.nav.RotateCommand;

public class HumanInput {
    
    XboxController xbox;
    private static JoystickButton laserButton, rotateButton, driveStraightButton;

    public HumanInput() {
        xbox = new XboxController(1);
        laserButton = new JoystickButton(xbox, 4);
        rotateButton = new JoystickButton(xbox, 1);
        driveStraightButton = new JoystickButton(xbox, 2);
    }

    public double getLeftJoystick() {
        return xbox.getRawAxis(1);
    }

    public double getRightJoystick() {
        return xbox.getRawAxis(5);
    }

    public void registerButtons() {
        laserButton.whenPressed(new LaserCommand());
        rotateButton.whenPressed(new RotateCommand(90));
        driveStraightButton.whenPressed(new DriveStraightCommand(30));

    }


}
