package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAbaisserBras extends CommandBase implements Finissable{

    protected Bras bras = null;
    protected boolean finie = false;

    public CommandeAbaisserBras(Bras bras)
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = bras;
        if(bras == null) this.finie = true;
    }

    public void setFinie()
    {
        System.out.println("setFinie()");
        this.finie = true;
    }

    @Override
    public void execute() {
        System.out.println("execute()");

        this.bras.tourner(0.1);
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
        return this.finie;
    }
}
