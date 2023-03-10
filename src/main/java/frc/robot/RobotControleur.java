// Code par l'équipe Supertronix 5910

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


import edu.wpi.first.wpilibj.TimedRobot;

public class RobotControleur extends TimedRobot {

  // roues droites avancent
  int ROUE_AVANT_DROITE = 4; // ID 4 
  int ROUE_ARRIERE_DROITE = 2; // ID 4 

  // roues gauches reculent
  int ROUE_AVANT_GAUCHE = 3; // ID 4 
  int ROUE_ARRIERE_GAUCHE = 1; // ID 4 

  CANSparkMax roueAvantDroite;
  CANSparkMax roueAvantGauche;
  CANSparkMax roueArriereDroite;
  CANSparkMax roueArriereGauche;
  
  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    this.roueAvantDroite = new CANSparkMax(ROUE_AVANT_DROITE, MotorType.kBrushless);
    this.roueAvantGauche = new CANSparkMax(ROUE_AVANT_GAUCHE, MotorType.kBrushless);
    this.roueArriereDroite = new CANSparkMax(ROUE_ARRIERE_DROITE, MotorType.kBrushless);
    this.roueArriereGauche = new CANSparkMax(ROUE_ARRIERE_GAUCHE, MotorType.kBrushless);
    this.roueAvantDroite.restoreFactoryDefaults();
    this.roueAvantGauche.restoreFactoryDefaults();
    this.roueArriereDroite.restoreFactoryDefaults();
    this.roueArriereGauche.restoreFactoryDefaults();;
  }

  @Override
  public void robotPeriodic() {
    System.out.println("robotPeriodic()");
    this.roueAvantDroite.set(0.05);
    this.roueAvantGauche.set(0.05);
    this.roueArriereGauche.set(0.05);
    this.roueArriereDroite.set(0.05);
  }

  @Override
  public void autonomousInit() {
    System.out.println("autonomousInit()");
  }

  @Override
  public void autonomousPeriodic() {
    System.out.println("autonomousPeriodic()");
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
