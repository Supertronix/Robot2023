package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBras extends CommandBase 
{
    protected Bras bras = null;
    protected double position = 0;

    public CommandeReleverBras(Bras bras, double position)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = bras;
        this.position = position;
    }
    @Override
    public void initialize() 
    {
        System.out.println("CommandeReleverBras.initialize()");
        this.bras.aller(position);
    }

    @Override
    public void execute() {
    }
        

    @Override
    public boolean isFinished() 
    {
        System.out.println("isFinished()");
        return true;
    }
}
