package frc.robot.nav;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;


public class DriveBackCommand extends CommandBase {

  
  private double currentAngle;
  private double targetDistance;
  private double remainingDistance;
  private double percentOutputLeft = 1;
  private double percentOutputRight;
  
  private static final double DEFAULT_PERCENT_OUTPUT = 0.5;
  private static final double MIN_PERCENT_OUTPUT = 0.1;
  private static final double ANGLE_kP = .1;
  private static final double SLOWING_ADJUSTMENT = 7;
  private static final double TOLERANCE = 1; // degrees
  private static final double DISTANCE_TOLERANCE = 2;
  private static final double SLOWING_DISTANCE = 50;


  public DriveBackCommand(double targetDistance) {
    addRequirements(Robot.driveSys);
    this.targetDistance = targetDistance;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
      Navx.getInstance().updateTotalAngle();
      Navx.getInstance().reset();
      Navx.getInstance().reset();
      Robot.driveSys.resetEncoders();

      currentAngle = Navx.getInstance().getYaw();
      remainingDistance = targetDistance + Robot.driveSys.getEncoderDistance();

      
      Robot.driveSys.setTalons(DEFAULT_PERCENT_OUTPUT);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
      currentAngle = Navx.getInstance().getYaw();
      double power = DEFAULT_PERCENT_OUTPUT;
      remainingDistance = targetDistance + Robot.driveSys.getEncoderDistance();
      System.out.println("Encoder" + Robot.driveSys.getEncoderDistance());

      if(remainingDistance <= SLOWING_DISTANCE) {
        power *= (remainingDistance + SLOWING_ADJUSTMENT) / (SLOWING_DISTANCE + SLOWING_ADJUSTMENT);
      }

      double powerAdjustment = 0;

      if(Math.abs(currentAngle) >= TOLERANCE) {
        powerAdjustment = (currentAngle / 180) * power * ANGLE_kP;
      }
      power = Math.max(Math.abs(power), MIN_PERCENT_OUTPUT);

       Robot.driveSys.setLeftTalons(-(power - powerAdjustment));
      Robot.driveSys.setRightTalons(-(power + powerAdjustment));
      
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(final boolean interrupted) {
      Robot.driveSys.stopMotors();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return (Math.abs(remainingDistance) <= DISTANCE_TOLERANCE);
  }
}