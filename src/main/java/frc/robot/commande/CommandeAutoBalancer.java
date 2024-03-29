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
    protected DetecteurDuree detecteurDuree;
    //protected boolean finie = false;
    //protected DetecteurDuree detecteur;

    public CommandeAutoBalancer()
    {
        System.out.println("new CommandeAutoBalancer()");
        this.lecteurEquilibre = LecteurAccelerometre.getInstance();
        this.detecteurDuree = new DetecteurDuree(2000);
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
        this.detecteurDuree.initialiser();
        //this.detecteur.initialiser();
        //this.finie = false;
    }

    public void compterEtArreter(int decompte)
    {
        if(decompte < 0) decompte = 3;
        compteur++;
        if(compteur > decompte*FOIS)
        {
           vitesse = 0;
           this.estFinie = true;
        }
    }
    // roll = avant-arriere
    // pitch = sor les cotes
    // yaw = rotation du robot
    //protected double VITESSE_BASE = 0.05910;
    protected double VITESSE_BASE = 0.04;
    protected double vitesse;
    protected double roll;
    enum INCLINAISON {ELEVEE, MOYENNE, PETITE, PLAT};
    enum SENS {DECOLLAGE, ATTERRISSAGE};
    INCLINAISON inclinaison;
    SENS sens;
    INCLINAISON inclinaisonPrecedente;
    SENS sensPrecedent;
/**
 * 
    float LIMITE_1 = 4;
    float LIMITE_2 = 6;
    float LIMITE_3 = 12;
    int iterations = 0;

 */

    float LIMITE_1 = 2;
    float LIMITE_2 = 4;
    float LIMITE_3 = 10;

    int iterations = 0;
    void classifierInclinaison()
    {
        this.inclinaisonPrecedente = this.inclinaison;
        this.sensPrecedent = this.sens;
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
        if(this.sensPrecedent != this.sens) this.iterations++;
        System.out.println("INCLINAISON = " + this.inclinaison);
        System.out.println("SENS = " + this.sens);
        System.out.println("iterations = " + this.iterations);
    }

    @Override
    public void execute() {
        System.out.println("CommandeAutoBalancer.execute()");
        this.detecteurDuree.mesurer();
        this.classifierInclinaison();

        SmartDashboard.putNumber("roll", roll);
        SmartDashboard.putNumber("vitesse", vitesse);

        roll = this.lecteurEquilibre.getRoll(UNITE.DEGRES);
        System.out.println("getRoll() " + roll);
        //// ===================== ///////

        if(this.inclinaison == INCLINAISON.ELEVEE)
        {
            vitesse = (VITESSE_BASE*roll)/3;
            //if(sens == SENS.DECOLLAGE) vitesse = (VITESSE_BASE*roll)/3;
            //if(sens == SENS.ATTERRISSAGE) vitesse = VITESSE_BASE;
            if(this.iterations > 2) vitesse /= (this.iterations/2);
        }
        if(this.inclinaison == INCLINAISON.MOYENNE)
        {
            vitesse = (VITESSE_BASE*roll)/3;
            if(this.iterations > 2) vitesse /= (this.iterations/2);
        }
        if(this.inclinaison == INCLINAISON.PETITE)
        {
            vitesse = (VITESSE_BASE*roll)/5;
            if(this.iterations > 2) vitesse /= (this.iterations/2);
        }
        if(this.inclinaison == INCLINAISON.PLAT)
        {
            //vitesse = (VITESSE_BASE*roll)/10;
            //if(this.iterations > 2) vitesse /= (this.iterations/2);
            vitesse = 0;
            this.compterEtArreter(10);
        }

        if(!this.estFinie) 
            this.roues.conduireToutesDirections(vitesse, vitesse, vitesse, vitesse);
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
