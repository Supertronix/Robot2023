package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBrasAvecManette extends CommandBase implements Finissable{

    protected Bras bras = null;
    protected boolean finie = false;
    protected ManetteTestBras manette = null;
    protected double vitesse = 0;

    public CommandeReleverBrasAvecManette(Bras bras, ManetteTestBras manette, double vitesse)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = bras;
        this.manette = manette;
        this.vitesse = vitesse;
        if(bras == null) this.finie = true;
        if(manette == null) this.finie = true;
    }

    public void setFinie()
    {
        System.out.println("setFinie()");
        this.finie = true;
    }

    @Override
    public void execute() {
        System.out.println("CommandeReleverBras.execute() " + vitesse);
        if (manette.getDemandeReleve()) {
            System.out.println("if " + vitesse);
            this.finie = false;
            this.bras.tourner(-vitesse);
        } else {
            if(!finie) this.bras.tourner(0);
            this.setFinie();
         }

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
