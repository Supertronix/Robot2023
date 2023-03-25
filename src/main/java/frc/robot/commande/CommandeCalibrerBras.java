package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeCalibrerBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0.1;

    public CommandeCalibrerBras(Bras bras)
    {
        System.out.println("new CommandeAbaisserBras()");
        // addRequirements(bras);
        this.bras = bras;
        //if(bras == null) this.finie = true;
    }

    public void setFinie()
    {
        //System.out.println("setFinie()");
        this.finie = true;
    }
        
    @Override
    public void initialize() 
    {
        System.out.println("initialize()");
        //this.bras.abaisser();

    }
    @Override
    public void execute() {
        System.out.println("CommandeCalibrerBras.execute()");
        this.bras.tourner(vitesse);
        //this.finie = false;
    }

    @Override
    public boolean isFinished() 
    {
        System.out.println("isFinished()");
        if(this.bras.estAuDepart())
        {
            this.bras.initialiser();
            return true;
        }
        //return this.finie;
        return false;
    }
}
