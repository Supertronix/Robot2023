package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;

public class CommandeArreterCommande extends CommandBase{

    protected Finissable commande = null;

    public CommandeArreterCommande(Finissable finissable)
    {
        this.commande = finissable;
    }
    @Override
    public void execute() {

    }
        
    @Override
    public void initialize() 
    {
        this.commande.setFinie();
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }

}
