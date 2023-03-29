package frc.robot.commande;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import frc.robot.Cinematique;
import frc.robot.Robot;
//import frc.robot.mesure.DetecteurDuree;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAjusterBras extends CommandBase implements Cinematique.Bras
{
    protected Bras bras = null;
    protected double position = 0;
    //protected DetecteurDuree detecteur;
    protected double increment = 0;

    public CommandeAjusterBras(double increment)
    {
        System.out.println("new CommandeAjusterBras()");
        this.increment = increment;
        this.bras = Robot.getInstance().bras;
        this.addRequirements(this.bras);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }

    boolean subiGravite()
    {
        boolean aRenforcer = false;
        if(this.bras.getPosition() < 6.5)
        {
            if(increment >= 1) // augmenter
            {
                aRenforcer = true;
            }
        }
        else if(this.bras.getPosition() > 6.5)
        {
            if(increment < 1) // augmenter
            {
                aRenforcer = true;
            }
        }
        return aRenforcer;
    }
    
    @Override
    public void initialize() 
    {
        System.out.println("CommandeAjusterBras.initialize()");
        System.out.println("Position de depart du bras : " + this.bras.getPosition());
        SmartDashboard.putNumber("Position demandee", this.position);

        if(subiGravite()) 
        {
            increment *=3;
        }

        // calculer la nouvelle position
        this.position = this.bras.getPosition() + this.increment;
        System.out.println("PositionActuelle = " + this.bras.getPosition());
        System.out.println("Increment demande = " + this.increment);
        System.out.println("Position demandee = " + this.position);

        // rappeler au bras sa derniere position et lui indiquer la nouvelle
        //this.bras.rappelerPosition(this.bras.positionDemandee);
        this.bras.positionDemandee = position;


        // effectivement aller a la position
        this.bras.preparerCinematique(INCREMENT.P, INCREMENT.I, INCREMENT.D);
        this.bras.aller(this.position);
    }

    @Override
    public void execute() {
        //SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
        //this.detecteur.mesurer();
    }
        

    @Override
    public boolean isFinished() 
    {
        System.out.println("CommandeReleverBras.isFinished()");
        this.bras.positionDemandee = position;
        //SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
        return true;
    }
}
