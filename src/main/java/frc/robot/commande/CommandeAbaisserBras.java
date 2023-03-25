package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAbaisserBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0;

    public CommandeAbaisserBras(Bras bras, double vitesse)
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = bras;
        this.vitesse = vitesse;
        if(bras == null) this.finie = true;
    }

    public void setFinie()
    {
        //System.out.println("setFinie()");
        this.finie = true;
    }

    @Override
    public void execute() {
        System.out.println("CommandeAbaisserBras.execute() " + vitesse);
        //this.finie = false;
        //this.bras.tourner(vitesse);
    }
        
    @Override
    public void initialize() 
    {
        System.out.println("initialize()");

        this.bras.abaisser();
    }

    @Override
    public boolean isFinished() 
    {
        System.out.println("isFinished()");
        //return this.finie;
        return false;
    }
}
