package frc.robot.humaninput;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.laser.LaserCommand;

public class HumanInput {
    
    XboxController xbox;
    private static JoystickButton laserButton;

    public HumanInput() {
        xbox = new XboxController(1);
        laserButton = new JoystickButton(xbox, 4);
    }



    public void registerButtons() {
        laserButton.whenPressed(new LaserCommand());


    }


}
