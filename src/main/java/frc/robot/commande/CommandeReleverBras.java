package frc.robot.commande;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import frc.robot.Cinematique;
import frc.robot.Robot;
//import frc.robot.mesure.DetecteurDuree;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBras extends CommandBase implements Cinematique.Bras
{
    protected Bras bras = null;
    protected double position = 0;
    //protected DetecteurDuree detecteur;
    public CommandeReleverBras(POSITION nom)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = Robot.getInstance().bras;
        this.bras.positionNom = nom;
        this.position = POSITION_NUMERIQUE.get(nom);
        this.addRequirements(this.bras);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }

    public CommandeReleverBras(POSITION nom, double position)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = Robot.getInstance().bras;
        this.bras.positionNom = nom;
        this.addRequirements(this.bras);
        this.position = position;
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
    
    @Override
    public void initialize() 
    {
        System.out.println("CommandeReleverBras.initialize()");
        System.out.println("Position de depart du bras : " + this.bras.getPosition());

        // rappeler au bras sa derniere position et lui indiquer la nouvelle
        this.bras.rappelerPosition(this.bras.positionDemandee);
        this.bras.positionDemandee = position;

        // effectivement aller a la position
        this.bras.preparerCinematique(P, I, D);
        this.bras.aller(position);
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
