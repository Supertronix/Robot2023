// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.interaction.*;
import frc.robot.test.Testeur;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class RobotControleur extends TimedRobot {

  protected Manette manette;
  protected Robot robot;
  protected Testeur testeur;
  protected Contexte conteneur = null;
  protected CANSparkMax roueAvantGauche;
  protected CANSparkMax roueArriereGauche;
  protected CANSparkMax roueArriereDroite;
  protected CANSparkMax roueAvantDroite;
  protected double speed = 0.05;

  
  @Override
  public void robotInit() {
    //System.out.println("robotInit()");
    //conteneur = new Contexte();
    this.manette = Manette.getInstance();
    this.robot = Robot.getInstance();
    this.roueAvantGauche = new CANSparkMax(1, MotorType.kBrushless);
    this.roueAvantGauche.restoreFactoryDefaults();
    this.roueAvantGauche.stopMotor();
    this.roueArriereGauche = new CANSparkMax(2, MotorType.kBrushless);
    this.roueArriereGauche.restoreFactoryDefaults();
    this.roueArriereGauche.stopMotor();
    this.roueArriereDroite = new CANSparkMax(3, MotorType.kBrushless);
    this.roueArriereDroite.restoreFactoryDefaults();
    this.roueArriereDroite.stopMotor();
    this.roueAvantDroite = new CANSparkMax(4, MotorType.kBrushless);
    this.roueAvantDroite.restoreFactoryDefaults();
    this.roueAvantDroite.stopMotor();
    //on inverse le sens des roues car elles sont montée de façon symétrique
    this.roueArriereGauche.setInverted(true);
    this.roueAvantGauche.setInverted(true);
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
    //this.testeur = new Testeur();
  }

  @Override
  public void teleopPeriodic() {  
    robot.roues.conduireAvecManette(this.manette);
    //testeur.executer();
    //diagonaleGauche();
    //diagonaleDroite();
    //diagonaleArriereGauche();
    //diagonaleArriereDroite();
  }

  public void avancer(){
    this.roueAvantGauche.set(speed);
    this.roueArriereGauche.set(speed);
    this.roueAvantDroite.set(speed);
    this.roueArriereDroite.set(speed);
  }

  public void diagonaleDroite(){
    this.roueAvantGauche.set(speed);
    this.roueArriereGauche.set(0);
    this.roueAvantDroite.set(0);
    this.roueArriereDroite.set(speed);
  }

  public void diagonaleGauche(){
    this.roueAvantGauche.set(0);
    this.roueArriereGauche.set(speed);
    this.roueAvantDroite.set(speed);
    this.roueArriereDroite.set(0);
  }

  public void diagonaleArriereDroite(){
    this.roueAvantGauche.set(0);
    this.roueArriereGauche.set(-speed);
    this.roueAvantDroite.set(-speed);
    this.roueArriereDroite.set(0);
  }

  public void diagonaleArriereGauche(){
    this.roueAvantGauche.set(-speed);
    this.roueArriereGauche.set(0);
    this.roueAvantDroite.set(0);
    this.roueArriereDroite.set(-speed);
  }

  public void pivoterGauche(){
    //schéma d
    this.roueAvantGauche.set(0);
    this.roueArriereGauche.set(0);
    this.roueAvantDroite.set(speed);
    this.roueArriereDroite.set(speed);
  }

  public void pivoterDroite(){
    //schéma d
    this.roueAvantGauche.set(speed);
    this.roueArriereGauche.set(speed);
    this.roueAvantDroite.set(0);
    this.roueArriereDroite.set(0);
  }

  public void demitourDroite(){
    //schéma e
    this.roueAvantGauche.set(speed);
    this.roueArriereGauche.set(speed);
    this.roueAvantDroite.set(-speed);
    this.roueArriereDroite.set(-speed);
  }

  public void demitourGauche(){
    //schéma e
    this.roueAvantGauche.set(-speed);
    this.roueArriereGauche.set(-speed);
    this.roueAvantDroite.set(speed);
    this.roueArriereDroite.set(speed);
  }

  public void pivoterFGauche(){
    //schéma f
    this.roueAvantGauche.set(-speed);
    this.roueArriereGauche.set(0);
    this.roueAvantDroite.set(speed);
    this.roueArriereDroite.set(0);
  }

  public void pivoterFDroite(){
    //schéma f
    this.roueAvantGauche.set(speed);
    this.roueArriereGauche.set(0);
    this.roueAvantDroite.set(-speed);
    this.roueArriereDroite.set(0);
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
  }
}
