package frc.robot.commande;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import frc.robot.Cinematique;
import frc.robot.Robot;
//import frc.robot.mesure.DetecteurDuree;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeDeplacerBras extends CommandBase implements Cinematique.Bras
{
    protected Bras bras = null;
    protected double position = 0;
    //protected DetecteurDuree detecteur;
    public CommandeDeplacerBras(POSITION nom)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = Robot.getInstance().bras;
        this.bras.positionNom = nom;
        this.position = POSITION_NUMERIQUE.get(nom);
        this.addRequirements(this.bras);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }

    public CommandeDeplacerBras(POSITION nom, double position)
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
        double deplacement = this.position - this.bras.positionDemandee;
        if(deplacement > SEUIL_GRAND_MOUVEMENT)
            this.bras.preparerCinematique(GRAND_MOUVEMENT.P, GRAND_MOUVEMENT.I, GRAND_MOUVEMENT.D);
        else if(deplacement > SEUIL_PETIT_MOUVEMENT)
            this.bras.preparerCinematique(GRAND_MOUVEMENT.P, GRAND_MOUVEMENT.I, GRAND_MOUVEMENT.D);
        else 
            this.bras.preparerCinematique(GRAND_MOUVEMENT.P, GRAND_MOUVEMENT.I, GRAND_MOUVEMENT.D);
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
