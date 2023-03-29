package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.interaction.LecteurAccelerometre;
import frc.robot.interaction.LecteurAccelerometre.*;
import frc.robot.soussysteme.RouesMecanumSynchro;
import frc.robot.mesure.DetecteurDuree;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeAutoBalancer extends CommandBase {

    protected RouesMecanumSynchro roues = null;
    protected LecteurAccelerometre lecteurEquilibre = null;
    //protected boolean finie = false;
    //protected DetecteurDuree detecteur;

    public CommandeAutoBalancer()
    {
        System.out.println("new CommandeAutoBalancer()");
        this.lecteurEquilibre = LecteurAccelerometre.getInstance();
        //this.roues = Robot.getInstance().roues;
        //this.addRequirements(this.roues);
        //this.detecteur = new DetecteurDuree(Cinematique.Machoire.TEMPS_MAXIMUM_OUVRIR);
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeAutoBalancer.initialize()");
        this.roues = (RouesMecanumSynchro)Robot.getInstance().roues;
        this.roues.convertirEnRouesHolonomiques();
        //this.detecteur.initialiser();
        //this.finie = false;
    }
    @Override
    public void execute() {
        System.out.println("CommandeAutoBalancer.execute()");
        //this.detecteur.mesurer();

        System.out.println("getRoll() " + this.lecteurEquilibre.getRoll(UNITE.DEGRES));
        double vitesse = 0.1;
        if(this.lecteurEquilibre.getRoll(UNITE.DEGRES) > 12)
        {
            this.roues.conduireToutesDirections(vitesse/roll, vitesse, vitesse, vitesse);
        }
        if(this.lecteurEquilibre.getRoll(UNITE.DEGRES) < -12)
        {
            this.roues.conduireToutesDirections(-vitesse, -vitesse, -vitesse, -vitesse);
        }
    }

    @Override
    public boolean isFinished() 
    {
        //if(lecteurEquilibre.depasseSeuilEquilibre() || Robot.getInstance().equilibre) 
        //{ 
        //    System.out.println("CommandeAutoBalancer.isFinished() == true");
        //    return true;
        //}
        return false;
    }
}
