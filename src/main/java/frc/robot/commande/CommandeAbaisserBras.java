package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.soussysteme.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAbaisserBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double position = 0;

    public CommandeAbaisserBras(Bras bras, double position)
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = Robot.getInstance().bras;
        this.position = position;
        if(bras == null) this.finie = true;
    }

    @Override
    public void execute() {
        //System.out.println("CommandeAbaisserBras.execute() " + position);
    }
        
    @Override
    public void initialize() 
    {
        System.out.println("CommandeAbaisserBras.initialize()");

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
