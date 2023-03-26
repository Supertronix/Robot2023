package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeCalibrerBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0.1;

    public CommandeCalibrerBras(Bras bras)
    {
        System.out.println("new CommandeAbaisserBras()");
        // addRequirements(bras);
        this.bras = bras;
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeCalibrerBras.initialize()");
    }
    @Override
    public void execute() {
        //System.out.println("CommandeCalibrerBras.execute()");
        this.bras.tourner(-vitesse);
        SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
    }

    @Override
    public boolean isFinished() 
    {
        System.out.println("isFinished()");
        if(this.bras.estAuDepart())
        {
            this.bras.initialiser();
            SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
            return true;
        }
        return false;
    }
}
