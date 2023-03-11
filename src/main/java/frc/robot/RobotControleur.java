// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.interaction.*;

public class RobotControleur extends TimedRobot {

  protected Manette manette;
  protected Robot robot;
  double test = 5;
  
  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    this.manette = Manette.getInstance();
    this.robot = new Robot();
    SmartDashboard.putNumber("test", test); 
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
    //robot.roues.avancer(0.05);
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
    //robot.roues.
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
    robot.roues.conduireAvecManette(this.manette);
    //robot.roues.avancer(0.05);
  }

  @Override
  public void testExit() {
    System.out.println("testExit()");
  }
}
