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

    protected int compteur;
    public static int FOIS = 3;
    protected boolean estFinie = false;
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
        this.estFinie = false;
        this.compteur = 0;
        //this.detecteur.initialiser();
        //this.finie = false;
    }

    public void compterEtArreter()
    {
        compteur++;
        if(compteur > 2*FOIS)
        {
           vitesse = 0;
           this.estFinie = true;
        }
    }
    // roll = avant-arriere
    // pitch = sor les cotes
    // yaw = rotation du robot
    //protected double VITESSE_BASE = 0.05910;
    protected double VITESSE_BASE = 0.02;
    protected double vitesse;
    protected double roll;
    @Override
    public void execute() {
        System.out.println("CommandeAutoBalancer.execute()");
        //this.detecteur.mesurer();

        //// TECHNIQUE MATHEMATIQUE /////// marche pas
        // System.out.println("getRoll() " + this.lecteurEquilibre.getRoll(UNITE.DEGRES));
        // double vitesse = 0.03*Math.max(1,Math.sqrt(this.lecteurEquilibre.getRoll(UNITE.DEGRES)));
        // this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        ////  ===================== ///////

        //// TCHNIQUE par INCREMENTS ///// MARCHE mais ne stabilise pas
        /* 
        roll = this.lecteurEquilibre.getRoll(UNITE.DEGRES);
        System.out.println("getRoll() " + roll);
        if(this.lecteurEquilibre.getRoll(UNITE.DEGRES) > 12)
        {
            this.roues.conduireToutesDirections(vitesse/roll, vitesse, vitesse, vitesse);
        }
        if(this.lecteurEquilibre.getRoll(UNITE.DEGRES) < -12)
        {
            this.roues.conduireToutesDirections(-vitesse, -vitesse, -vitesse, -vitesse);
        }
        */
        //// ===================== ///////

        //// TECHNIQUE par INCREMENTS ///// A tester
        SmartDashboard.putNumber("roll", roll);
        SmartDashboard.putNumber("vitesse", vitesse);

        roll = this.lecteurEquilibre.getRoll(UNITE.DEGRES);
        System.out.println("getRoll() " + roll);
        if(roll >= 12) // avancer au debut
        {
            vitesse = VITESSE_BASE*roll;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= 6 && roll < 12)
        {
            vitesse = (VITESSE_BASE*roll)/1.5;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= 3 && roll < 6)
        {
            vitesse = (VITESSE_BASE*roll)/4;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= 0 && roll < 3)
        {
            vitesse = (VITESSE_BASE*roll)/10;
            this.compterEtArreter();
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= -3 && roll < 0)
        {
            vitesse = (VITESSE_BASE*roll)/10;
            this.compterEtArreter();
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= -6 && roll < 0)
        {
            vitesse = (VITESSE_BASE*roll)/4;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll >= -12 && roll < -6)
        {
            vitesse = (VITESSE_BASE*roll)/1.5;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        if(roll <= -12) // reculer apres
        {
            vitesse = VITESSE_BASE;
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
        }
        //// ===================== ///////


        ///// TECHNIQUE par SEGMENTS //////




        ///// ======================= ///////


    }

    @Override
    public boolean isFinished() 
    {
        //if(lecteurEquilibre.depasseSeuilEquilibre() || Robot.getInstance().equilibre) 
        //{ 
        //    System.out.println("CommandeAutoBalancer.isFinished() == true");
        //    return true;
        //}
        return this.estFinie;
    }
}
