package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.soussysteme.Machoire;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeFermerMachoire extends CommandBase {

    protected Machoire machoire = null;
    protected boolean finie = false;
    protected double vitesse = 0.8;

    public CommandeFermerMachoire()
    {
        System.out.println("new CommandeFermerMachoire()");
        this.machoire = Robot.getInstance().machoire;
        this.addRequirements(this.machoire);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeFermerMachoire.initialize()");
        this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeFermerMachoire.execute()");
        this.machoire.fermerAvecVitesse(vitesse);
    }

    @Override
    public boolean isFinished() 
    {
        if(this.machoire.estFermee())
        {
            System.out.println("CommandeFermerMachoire.isFinished() == true");
            this.finie = true;
        }
        return this.finie;
    }
}
