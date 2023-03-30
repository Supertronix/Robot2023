package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.soussysteme.Roues;
import frc.robot.soussysteme.RouesMecanum;
import frc.robot.soussysteme.RouesMecanumSynchro;
import frc.robot.mesure.DetecteurDuree;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeMaintenirRobot extends CommandBase {

    protected RouesMecanumSynchro roues = null;
    //protected DetecteurDuree detecteur;

    public CommandeMaintenirRobot()
    {
        System.out.println("new CommandeMaintenirRobot()");
        //this.roues = Robot.getInstance().roues;
        //this.addRequirements(this.roues);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeMaintenirRobot.initialize()");
        Robot.getInstance().equilibre = true;
        this.roues = (RouesMecanumSynchro)Robot.getInstance().roues;
        this.roues.convertirEnRouesSynchro();
        this.roues.avancer(this.roues.getPosition());
        //this.detecteur.initialiser();
        //this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeMaintenirRobot.execute()");
        //this.detecteur.mesurer();
    }

    @Override
    public boolean isFinished() 
    {
        //if(this.machoire.estOuverte() || this.detecteur.estTropLongue())
        return true;
    }
}
