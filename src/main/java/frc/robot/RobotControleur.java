// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.interaction.*;
import frc.robot.test.Testeur;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class RobotControleur extends TimedRobot {

  protected Manette manette;
  protected Robot robot;
  //protected Testeur testeur;
  protected Contexte conteneur = null;

  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    conteneur = new Contexte();

    this.manette = Manette.getInstance();
    this.robot = Robot.getInstance();
    //this.testeur = new Testeur();
  }

  @Override
  public void robotPeriodic() {
    //System.out.println("robotPeriodic()");
    CommandScheduler.getInstance().run();
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
    //this.testeur.initialiser();
    //this.testeur.lancer();
  }

  @Override
  public void teleopPeriodic() {
    //System.out.println("teleopPeriodic()");   
    
    robot.roues.conduireAvecManette(this.manette);
    SmartDashboard.putNumber("Position Bras", this.robot.bras.getPosition());  

    //testeur.executer();
  }

  @Override
  public void teleopExit() {
    System.out.println("teleopExit()");
  }

  @Override
  public void testInit() {
    System.out.println("testInit()");
    //this.testeur = new Testeur();
  }

  @Override
  public void testPeriodic() {
    //System.out.println("testPeriodic()");
    //this.testeur.executer();
  }

  @Override
  public void testExit() {
    System.out.println("testExit()");
    this.robot.fermer();
  }
}
