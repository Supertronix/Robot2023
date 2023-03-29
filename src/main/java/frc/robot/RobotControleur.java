// Code par l'équipe Supertronix 5910

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Cinematique.Bras.POSITION;
import frc.robot.commande.CommandeAvancer;
import frc.robot.commande.CommandeAvancerJusquaPlateforme;
import frc.robot.commande.CommandeCalibrerBras;
import frc.robot.commande.CommandeDeplacerBras;
import frc.robot.commande.CommandeDormir;
import frc.robot.commande.CommandeFermerMachoire;
import frc.robot.commande.CommandeMaintenirRobot;
import frc.robot.commande.CommandeMonterPlateforme;
import frc.robot.commande.CommandeOuvrirMachoire;
import frc.robot.interaction.*;
import frc.robot.interaction.LecteurAccelerometre.UNITE;
//import frc.robot.test.Testeur;
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

    //fooCommand.andThen(barCommand)
    //new SequentialCommandGroup(new FooCommand(), new BarCommand());
    protected SequentialCommandGroup modeAutonome;
    protected SelecteurPositionAutonome selecteurPositionAutonome;
    @Override
  public void autonomousInit() {
    System.out.println("autonomousInit()");
    this.selecteurPositionAutonome = SelecteurPositionAutonome.getInstance();
    int choix = this.selecteurPositionAutonome.lireChoix();
    System.out.println("CHOIX MODE AUTONOME = " + choix);
    switch(choix)
    {
      case 3:
      modeAutonome = new SequentialCommandGroup(
        //new CommandeFermerMachoire(),
        new CommandeAvancer(6),
        new CommandeCalibrerBras(),
        new CommandeOuvrirMachoire(),
        new CommandeDeplacerBras(POSITION.POSTIION_MILIEU),
        //new CommandeAvancer(6),
        new CommandeAvancerJusquaPlateforme(),
        new ParallelCommandGroup(
          new SequentialCommandGroup(
              new CommandeDeplacerBras(POSITION.POSITION_AVANT),
              new CommandeDormir(100),
              new CommandeDeplacerBras(POSITION.POSITION_ARRIERE),
              new CommandeMaintenirRobot()
          ),
          new CommandeMonterPlateforme()
        )
        );
      break;
      case 0:
        modeAutonome = new SequentialCommandGroup(
          //new CommandeFermerMachoire(),
          new CommandeAvancer(6),
          new CommandeCalibrerBras(),
          new CommandeOuvrirMachoire(),
          new CommandeDeplacerBras(POSITION.POSTIION_MILIEU),
          new CommandeAvancer(6)
          );
      break;
    }
      if(modeAutonome != null)modeAutonome.schedule(); 
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

  LecteurAccelerometre lecteurAccelerometre;
  @Override
  public void teleopInit() {
    System.out.println("teleopInit()");
    //lecteurAccelerometre = new LecteurAccelerometre.getInstance();
    //this.testeur.initialiser();
    //this.testeur.lancer();
  }

  @Override
  public void teleopPeriodic() {
    //System.out.println("teleopPeriodic()");   
    
    robot.roues.conduireAvecManette(this.manette);
    manette.executerActions();
    SmartDashboard.putNumber("Position Bras", this.robot.bras.getPosition());  
    //SmartDashboard.putNumber("Roll", lecteurAccelerometre.getRoll(UNITE.DEGRES));
    //SmartDashboard.putNumber("Pitch", lecteurAccelerometre.getPitch(UNITE.DEGRES));
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
