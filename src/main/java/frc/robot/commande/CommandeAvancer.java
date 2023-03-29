package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.soussysteme.Roues;
import frc.robot.soussysteme.RouesMecanumSynchro;
import frc.robot.mesure.DetecteurDuree;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAvancer extends CommandBase {

    protected Roues roues = null;
    protected boolean finie = false;
    protected DetecteurDuree detecteur;
    protected double pas;

    public CommandeAvancer(int pas)
    {
        System.out.println("new CommandeAvancer()");
        this.pas = pas;
        //this.roues = Robot.getInstance().roues;
        //this.addRequirements(this.roues);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeAvancer.initialize()");
        this.roues = Robot.getInstance().roues;
        this.roues.avancer(pas);
        //this.detecteur.initialiser();
        //this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeAvancer.execute()");
        //this.detecteur.mesurer();
    }

    @Override
    public boolean isFinished() 
    {
        //if(this.machoire.estOuverte() || this.detecteur.estTropLongue())
        return true;
    }
}
