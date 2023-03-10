// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.soussysteme.*;

public class RobotControleur extends TimedRobot {


  protected Roues roues;
  
  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    this.roues = new RouesMecanum();
  }

  @Override
  public void robotPeriodic() {
    System.out.println("robotPeriodic()");
  }

  @Override
  public void autonomousInit() {
    System.out.println("autonomousInit()");
  }

  @Override
  public void autonomousPeriodic() {
    System.out.println("autonomousPeriodic()");
    this.roues.conduireAvecAngle(0.05, 1, 0.05);
  }

  @Override
  public void autonomousExit() {
    System.out.println("autonomousExit()");
  }

  @Override
  public void teleopInit() {
    System.out.println("teleopInit()");
  }

  @Override
  public void teleopPeriodic() {
    System.out.println("teleopPeriodic()");   
  }

  @Override
  public void teleopExit() {
    System.out.println("teleopExit()");
  }

  @Override
  public void testInit() {
    System.out.println("testInit()");
  }

  @Override
  public void testPeriodic() {
    System.out.println("testPeriodic()");
  }

  @Override
  public void testExit() {
    System.out.println("testExit()");
  }
}
