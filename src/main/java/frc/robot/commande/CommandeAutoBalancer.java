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
        if(compteur > 3*FOIS)
        {
           vitesse = 0;
           this.estFinie = true;
        }
    }
    // roll = avant-arriere
    // pitch = sor les cotes
    // yaw = rotation du robot
    //protected double VITESSE_BASE = 0.05910;
    float LIMITE_1 = 3.5f;
    float LIMITE_2 = 6;
    float LIMITE_3 = 12;
    protected double VITESSE_BASE = 0.05;
    protected double vitesse;
    protected double roll;
    enum INCLINAISON {ELEVEE, MOYENNE, PETITE, PLAT};
    enum SENS {DECOLLAGE, ATTERRISSAGE};
    INCLINAISON inclinaison;
    SENS sens;
    @Override
    public void execute() {
        System.out.println("CommandeAutoBalancer.execute()");
        //this.detecteur.mesurer();


        //// TECHNIQUE par INCREMENTS ///// A tester
        SmartDashboard.putNumber("roll", roll);
        SmartDashboard.putNumber("vitesse", vitesse);

        roll = this.lecteurEquilibre.getRoll(UNITE.DEGRES);
        System.out.println("getRoll() " + roll);

        LIMITE_1 = 3.5f;
        LIMITE_2 = 6;
        LIMITE_3 = 12;
 
        if(roll >= LIMITE_3) // avancer au debut
        {
            this.inclinaison = INCLINAISON.ELEVEE;
            this.sens = SENS.DECOLLAGE;
        }
        if(roll <= -LIMITE_3) // reculer apres
        {
            this.inclinaison = INCLINAISON.ELEVEE;
            this.sens = SENS.ATTERRISSAGE;
        }
        if(roll >= LIMITE_2 && roll < LIMITE_3)
        {
            this.inclinaison = INCLINAISON.MOYENNE;
            this.sens = SENS.DECOLLAGE;
        }
        if(roll >= -LIMITE_3 && roll < -LIMITE_2)
        {
            this.inclinaison = INCLINAISON.MOYENNE;
            this.sens = SENS.ATTERRISSAGE;
        }
        if(roll >= LIMITE_1 && roll < LIMITE_2)
        {
            this.inclinaison = INCLINAISON.PETITE;
            this.sens = SENS.DECOLLAGE;
        }
        if(roll >= -LIMITE_2 && roll < -LIMITE_1)
        {
            this.inclinaison = INCLINAISON.PETITE;
            this.sens = SENS.ATTERRISSAGE;
        }
        if(roll >= 0 && roll < LIMITE_1)
        {
            this.inclinaison = INCLINAISON.PLAT;
            this.sens = SENS.DECOLLAGE;
        }
        if(roll >= -LIMITE_1 && roll < 0)
        {
            this.inclinaison = INCLINAISON.PLAT;
            this.sens = SENS.ATTERRISSAGE;
        }
        

        //// ===================== ///////
        if(this.inclinaison == INCLINAISON.ELEVEE)
        {
            if(sens == SENS.DECOLLAGE) vitesse = (VITESSE_BASE*roll)/3;
            if(sens == SENS.ATTERRISSAGE) vitesse = VITESSE_BASE;
        }
        if(this.inclinaison == INCLINAISON.MOYENNE)
        {
            vitesse = (VITESSE_BASE*roll)/3;
        }
        if(this.inclinaison == INCLINAISON.PETITE)
        {
            vitesse = (VITESSE_BASE*roll)/5;
        }
        if(this.inclinaison == INCLINAISON.PLAT)
        {
            vitesse = (VITESSE_BASE*roll)/10;
            this.compterEtArreter();
        }
        if(!this.estFinie) this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
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
