package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Machoire;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeOuvrirMachoire extends CommandBase {

    protected Machoire machoire = null;
    protected boolean finie = false;
    protected double vitesse = 0.1;

    public CommandeOuvrirMachoire(Machoire machoire)
    {
        System.out.println("new CommandeOuvrirMachoire()");
        this.machoire = machoire;
        // addRequirements(machoire);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeOuvrirMachoire.initialize()");
    }
    @Override
    public void execute() {
        //System.out.println("CommandeOuvrirMachoire.execute()");
        this.machoire.ouvrirAvecVitesse(vitesse);
    }

    @Override
    public boolean isFinished() 
    {
        if(this.machoire.estFermee())
        {
            System.out.println("CommandeOuvrirMachoire.isFinished() == true");
            return true;
        }
        return false;
    }
}
