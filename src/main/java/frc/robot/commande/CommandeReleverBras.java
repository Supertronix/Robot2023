package frc.robot.commande;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import frc.robot.Robot;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeReleverBras extends CommandBase 
{
    protected Bras bras = null;
    protected double position = 0;

    public CommandeReleverBras(double position)
    {
        System.out.println("new CommandeReleverBras()");
        this.bras = Robot.getInstance().bras;
        this.addRequirements(this.bras);
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
        //SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
    }
        

    @Override
    public boolean isFinished() 
    {
        System.out.println("CommandeReleverBras.isFinished()");
        //SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
        return true;
    }
}
