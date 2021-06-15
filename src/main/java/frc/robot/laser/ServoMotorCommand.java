package frc.robot.laser;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;

public class ServoMotorCommand extends CommandBase {
    double speed;
    /** Creates a new ServoMotorCommand. */
    public ServoMotorCommand(double speed) {
      // Use addRequirements() here to declare subsystem dependencies.
      addRequirements(Robot.laserSys);
      this.speed = speed;
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {}
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      Robot.laserSys.setServoSpeed(speed);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      Robot.laserSys.setServoSpeed(0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      return false;
    }
  }
