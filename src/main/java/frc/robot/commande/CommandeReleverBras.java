package frc.robot.commande;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import frc.robot.Cinematique;
import frc.robot.Robot;
//import frc.robot.mesure.DetecteurDelais;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBras extends CommandBase implements Cinematique.Bras
{
    protected Bras bras = null;
    protected double position = 0;
    //protected DetecteurDelais detecteur;

    public CommandeReleverBras(double position)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = Robot.getInstance().bras;
        this.addRequirements(this.bras);
        this.position = position;
        //this.detecteur = new DetecteurDelais(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
    @Override
    public void initialize() 
    {
        System.out.println("CommandeReleverBras.initialize()");
        this.bras.preparerCinematique(P, I, D);
        this.bras.aller(position);
        //this.detecteur.initialiser();
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
        //SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
        return true;
    }
}
