package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.mesure.DetecteurImmobilite;
import frc.robot.mesure.DetecteurImmobilite.*;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeCalibrerBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0.1;
    protected DetecteurImmobilite detecteurImmobilite;

    public CommandeCalibrerBras()
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = Robot.getInstance().bras;
        this.addRequirements(this.bras);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeCalibrerBras.initialize()");
        this.detecteurImmobilite = new DetecteurImmobilite((Immobilisable)this.bras);
    }
    @Override
    public void execute() {
        System.out.println("CommandeCalibrerBras.execute()");
        this.bras.tourner(-vitesse);
        SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
        this.detecteurImmobilite.mesurer();
    }

    @Override
    public boolean isFinished() 
    {
        System.out.println("CommandeCalibrerBras.isFinished()");
        if(this.bras.estAuDepart() || this.detecteurImmobilite.estImmobile())
        {
            System.out.println("CommandeCalibrerBras.estAuDepart() == true");
            this.bras.initialiser();
            SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
            return true;
        }
        return false;
    }
}
