package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBras extends CommandBase implements Finissable{

    protected Bras bras = null;
    protected boolean finie = false;

    public CommandeReleverBras(Bras bras)
    {
        this.bras = bras;
        if(bras == null) this.finie = true;
    }

    public void setFinie()
    {
        this.finie = true;
    }

    @Override
    public void execute() {
        this.bras.tourner(-0.1);
    }
        
    @Override
    public void initialize() 
    {
        this.bras.relever();
    }

    @Override
    public boolean isFinished() 
    {
        return this.finie;
    }
}
