package frc.robot.laser;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;



public class LaserCommand extends CommandBase {
    /** Creates a new LaserCommand. */
    public LaserCommand() {
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(Robot.laserSys);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      Robot.laserSys.laserOn();
      Timer.delay(0.5);
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {}
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      Robot.laserSys.laserOff();
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return true;
    }
  }
