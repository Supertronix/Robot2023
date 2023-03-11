// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import frc.robot.interaction.*;

public class RobotControleur extends TimedRobot {

  protected Manette manette;
  
  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    this.manette = Manette.getInstance();
  }

  @Override
  public void robotPeriodic() {
    //System.out.println("robotPeriodic()");
  }

  @Override
  public void autonomousInit() {
    System.out.println("autonomousInit()");
  }

  @Override
  public void autonomousPeriodic() {
    //System.out.println("autonomousPeriodic()");
    //this.roues.avancer(0.05);
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
    //System.out.println("teleopPeriodic()");   
    //this.roues.
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
    //System.out.println("testPeriodic()");
    //this.roues.conduireAvecManette(this.manette);
    //this.roues.avancer(0.05);
  }

  @Override
  public void testExit() {
    System.out.println("testExit()");
  }
}
