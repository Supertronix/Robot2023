// Code par l'équipe Supertronix 5910

package frc.robot;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.TimedRobot;

public class RobotControleur extends TimedRobot {

  int ROUE_AVANT_DROITE = 4; // ID 4 
  CANSparkMax roueAvantroite;
  
  @Override
  public void robotInit() {
    System.out.println("robotInit()");
    this.roueAvantroite = new CANSparkMax(ROUE_AVANT_DROITE, MotorType.kBrushless);
    this.roueAvantroite.restoreFactoryDefaults();
  }

  @Override
  public void robotPeriodic() {
    System.out.println("robotPeriodic()");
    System.out.println("Test sur la roue " + ROUE_AVANT_DROITE);
    this.roueAvantroite.set(0.05);
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
