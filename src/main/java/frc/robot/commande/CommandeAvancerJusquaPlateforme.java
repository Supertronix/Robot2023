package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.interaction.LecteurAccelerometre;
import frc.robot.soussysteme.RouesMecanumSynchro;
import frc.robot.mesure.DetecteurDuree;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAvancerJusquaPlateforme extends CommandBase {

    protected RouesMecanumSynchro roues = null;
    protected LecteurAccelerometre lecteurEquilibre = null;
    //protected boolean finie = false;
    //protected DetecteurDuree detecteur;

    public CommandeAvancerJusquaPlateforme()
    {
        System.out.println("new CommandeAvancerJusquaPlateforme()");
        this.lecteurEquilibre = new LecteurAccelerometre();
        //this.roues = Robot.getInstance().roues;
        //this.addRequirements(this.roues);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeAvancerJusquaPlateforme.initialize()");
        if(!lecteurEquilibre.depasseSeuil())
            this.roues = (RouesMecanumSynchro)Robot.getInstance().roues;
        //this.detecteur.initialiser();
        //this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeAvancerJusquaPlateforme.execute()");
        //this.detecteur.mesurer();

        this.roues.reinitialiser();
        this.roues.avancerAvecVitesse(0.1);
    }

    @Override
    public boolean isFinished() 
    {
        //if(this.machoire.estOuverte() || this.detecteur.estTropLongue())
        return true;
    }
}