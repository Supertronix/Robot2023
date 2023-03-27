package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.mesure.DetecteurDelais;
import frc.robot.mesure.DetecteurImmobilite;
import frc.robot.mesure.DetecteurImmobilite.*;

// Pleine vitesse 1235.0
// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeCalibrerBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0.2;
    protected DetecteurImmobilite detecteurImmobilite;
    protected double depart = 0;
    protected double delais = 0;
    protected static int essais = 0;
    protected DetecteurDelais detecteur;

    public CommandeCalibrerBras()
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = Robot.getInstance().bras;
        this.bras.arreter();
        this.bras.reinitialiser();
        this.addRequirements(this.bras);
        this.detecteur = new DetecteurDelais(Cinematique.Bras.TEMPS_MAXIMUM_CALIBRER);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeCalibrerBras.initialize()");
        this.detecteurImmobilite = new DetecteurImmobilite((Immobilisable)this.bras);
        this.depart = System.currentTimeMillis();
        this.detecteur.initialiser();
    }
    
    @Override
    public void execute() {
        System.out.println("CommandeCalibrerBras.execute()");
        this.detecteur.mesurer();

        // pour adoucir l'arrivee
        this.delais = System.currentTimeMillis() - this.depart;
        this.bras.reculer(vitesse / (delais/20));
        
        if(brasEnAvant) this.bras.reculer(vitesse);
        else if(delais < 10)
        {
            this.bras.reculer(vitesse);
        }
        else
        {
            this.bras.reculer(vitesse/delais);
            //this.bras.reculer(vitesse - vitesse*(delais/1000));
        }
        
        //this.bras.reculer(vitesse - vitesse*(delais/1000));
        System.out.println("Delais " + this.delais);

        this.detecteurImmobilite.mesurer();
        SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
    }

    boolean brasEnAvant = false;
    @Override
    public boolean isFinished() 
    {
        System.out.println("CommandeCalibrerBras.isFinished()");
        if(this.bras.estAuDepart() || (this.detecteurImmobilite.estImmobile()&&!brasEnAvant) || this.detecteur.estTropLong())
        {
            System.out.println("this.bras.estAuDepart() == " + this.bras.estAuDepart());
            System.out.println("this.detecteurImmobilite.estImmobile() == " + this.detecteurImmobilite.estImmobile());
            this.bras.arreter();
            this.bras.initialiser();
            SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  

            // EASTER EGG !!! on presse 3x sur le bouton homing pour le ramener à la maison - peu importe sa position
            if(this.bras.estAuDepart() )
            {
                essais = 0;
                brasEnAvant = false;
            }
            if(this.detecteurImmobilite.estImmobile()) 
            {
                essais++;
                if(essais >= 3)
                {
                    essais = 0; 
                    brasEnAvant = true;
                    return false;
                }
            }
            System.out.println("Essais " + essais);
            // Fin EASTER EGG
            
            return true;
        }
        return false;
    }
}
