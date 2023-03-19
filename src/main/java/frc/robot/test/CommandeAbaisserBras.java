package frc.robot.test;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Materiel.Bras;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAbaisserBras extends CommandBase {

    protected Bras bras = null;

    public CommandeAbaisserBras(Bras bras)
    {
        this.bras = bras;
    }

    @Override
    public void initialize() 
    {
        this.bras.abaisser();
    }

    @Override
    public boolean isFinished() 
    {
        return true;
    }
}
