package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.mesure.DetecteurDuree;
import frc.robot.soussysteme.Machoire;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeFermerMachoire extends CommandBase {

    protected Machoire machoire = null;
    protected boolean finie = false;
    protected double vitesse = Machoire.VITESSE_OUVRIR;
    protected DetecteurDuree detecteur;

    public CommandeFermerMachoire()
    {
        System.out.println("new CommandeFermerMachoire()");
        this.machoire = Robot.getInstance().machoire;
        this.addRequirements(this.machoire);
        this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_FERMER);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeFermerMachoire.initialize()");
        this.detecteur.initialiser();
        this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeFermerMachoire.execute()");
        this.machoire.fermerAvecVitesse(vitesse);
        this.detecteur.mesurer();
    }

    @Override
    public boolean isFinished() 
    {
        if(this.machoire.estFermee() || this.detecteur.estTropLongue())
        {
            System.out.println("CommandeFermerMachoire.isFinished() == true");
            this.machoire.arreter();
            this.finie = true;
        }
        return this.finie;
    }
}
