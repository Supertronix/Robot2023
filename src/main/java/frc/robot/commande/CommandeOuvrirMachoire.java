package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Machoire;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.mesure.DetecteurDuree;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeOuvrirMachoire extends CommandBase {

    protected Machoire machoire = null;
    protected boolean finie = false;
    protected double vitesse = Machoire.VITESSE_OUVRIR;
    protected DetecteurDuree detecteur;

    public CommandeOuvrirMachoire()
    {
        System.out.println("new CommandeOuvrirMachoire()");
        this.machoire = Robot.getInstance().machoire;
        this.addRequirements(this.machoire);
        this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeOuvrirMachoire.initialize()");
        this.detecteur.initialiser();
        this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeOuvrirMachoire.execute()");
        this.machoire.ouvrirAvecVitesse(vitesse);
        this.detecteur.mesurer();
    }

    @Override
    public boolean isFinished() 
    {
        if(this.machoire.estOuverte() || this.detecteur.estTropLongue())
        {
            System.out.println("CommandeOuvrirMachoire.isFinished() == true");
            this.finie = true;
            this.machoire.arreter();
        }
        return this.finie;
    }
}
