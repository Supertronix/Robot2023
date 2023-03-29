package frc.robot.commande;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.soussysteme.Bras;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Cinematique;
import frc.robot.Robot;
import frc.robot.mesure.DetecteurDuree;
import frc.robot.mesure.DetecteurImmobilite;
import frc.robot.mesure.DetecteurImmobilite.*;
import edu.wpi.first.wpilibj.Timer;

// Pleine vitesse 1235.0
// https://docs.wpilib.org/en/stable/docs/software/commandbased/commands.html
public class CommandeCalibrerBras extends CommandBase {

    protected Bras bras = null;
    protected boolean finie = false;
    protected double vitesse = 0.2;
    protected DetecteurImmobilite detecteurImmobilite;
    protected double depart = 0;
    protected double delais = 0;
    protected DetecteurDuree detecteurDuree;

    public enum ETAT {INACTIF, PREMIER_JET, SECOND_JET};
    protected ETAT etat = ETAT.INACTIF;
    public enum SOUS_ETAT {INACTIF, REMONTER, REDESCENDRE};
    protected SOUS_ETAT sousetat = SOUS_ETAT.INACTIF;
    protected double debutSousEtat = 0;

    // pour recommencer apres 3 essais
    protected static int essais = 0;
    protected boolean brasEnAvant = false;

    public CommandeCalibrerBras()
    {
        System.out.println("new CommandeAbaisserBras()");
        this.bras = Robot.getInstance().bras;
        //this.bras.arreter();
        //this.bras.reinitialiser();
        this.addRequirements(this.bras);
        this.detecteurDuree = new DetecteurDuree(Cinematique.Bras.TEMPS_MAXIMUM_CALIBRER);
        this.etat = ETAT.INACTIF;
    }
       
    @Override
    public void initialize() 
    {
        System.out.println("CommandeCalibrerBras.initialize()");
        this.commencer();
        this.bras.reinitialiser();
    }
    public void commencer()
    {
        this.depart = System.currentTimeMillis();
        double tempsMaximal = (brasEnAvant)?Cinematique.Bras.TEMPS_MAXIMUM_CALIBRER_AVANT:Cinematique.Bras.TEMPS_MAXIMUM_CALIBRER;
        this.detecteurDuree = new DetecteurDuree(tempsMaximal);
        this.detecteurDuree.initialiser();
        this.detecteurImmobilite = new DetecteurImmobilite((Immobilisable)this.bras);

        this.etat = ETAT.PREMIER_JET;  
        this.bras.arreter();
    }
    
    public double calculerVitesseAdoucie()
    {
        double vitesseAdoucie = 0;
        if(brasEnAvant) 
        {
            if(delais < 1000)
            {
                vitesseAdoucie = vitesse;
            }
            else
            {
                vitesseAdoucie = vitesse/delais; // vitesse*(delais/1000));
            }            
        }
        else
        {
            if(delais < 10)
            {
                vitesseAdoucie = vitesse;
            }
            else
            {
                vitesseAdoucie = vitesse/delais; //(vitesse - vitesse*(delais/1000));
            }
        } 
        return vitesseAdoucie;
    }

    @Override
    public void execute() {
        System.out.println("CommandeCalibrerBras.execute()");
        this.detecteurDuree.mesurer();
        this.delais = System.currentTimeMillis() - this.depart;
        //this.bras.reculer(vitesse / (delais/20));         // pour adoucir l'arrivee
        
        // ADOUCISSEMENT du mouvement apres un certain temps selon position
        this.bras.reculer(calculerVitesseAdoucie());
        /*
        if(this.etat == ETAT.PREMIER_JET)
        {
            this.bras.reculer(calculerVitesseAdoucie());
        }
        else if (this.etat == ETAT.SECOND_JET)
        {
            if(this.sousetat == SOUS_ETAT.REMONTER)
            {
                System.out.println("REMONTER");
                this.bras.avancer(0.2);
            }
        } 
        */

        //this.bras.reculer(vitesse - vitesse*(delais/1000));
        System.out.println("Delais " + this.delais);

        this.detecteurImmobilite.mesurer();
        SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  
    }

    // EASTER EGG !!! on presse 3x sur le bouton homing pour le ramener à la maison 
    // - peu importe sa position
    private boolean recommencerApresTroisEssais()
    {
        essais++;
        System.out.println("Essais " + essais);
        if(essais >= 2)
        {
            essais = 0; 
            brasEnAvant = true;

            // repartir a zero
            this.commencer();
            return true;
        }
        return false;
    }
    // Fin EASTER EGG

    private void transitionnerEtat()
    {
        if(this.etat == ETAT.PREMIER_JET) 
        {
            System.out.println("SECOND_JET");
            System.out.println("REMONTER");
            this.etat = ETAT.SECOND_JET;
            this.sousetat = SOUS_ETAT.REMONTER;
            this.debutSousEtat = System.currentTimeMillis();
        }
        if(this.etat == ETAT.SECOND_JET)
        {
            System.out.println("SECOND_JET");
            double maintenant = System.currentTimeMillis();
            if(maintenant - this.debutSousEtat > 300)
            {
                System.out.println("REDESCENDRE");
                this.sousetat = SOUS_ETAT.REDESCENDRE;
            }
        }
    }

    boolean enReglage = false;
    double debutReglage = 0;
    
    @Override
    public boolean isFinished() 
    {
        System.out.println("CommandeCalibrerBras.isFinished()");
        if(this.detecteurImmobilite.estImmobile() ) 
        {
            System.out.println("this.detecteurImmobilite.estImmobile() == " + this.detecteurImmobilite.estImmobile());
            if(this.recommencerApresTroisEssais()) return false;
        }

        if(this.bras.estAuDepart() 
        || (this.detecteurImmobilite.estImmobile()&&!brasEnAvant) 
        || this.detecteurDuree.estTropLongue())
        {
            SmartDashboard.putNumber("Position Bras", this.bras.getPosition());  

            if(this.bras.estAuDepart() || this.detecteurDuree.estTropLongue())
            {
                System.out.println("this.bras.estAuDepart() == " + this.bras.estAuDepart());
                System.out.println("this.detecteurDuree.estTropLongue() == " + this.detecteurDuree.estTropLongue());
                essais = 0; brasEnAvant = false; // recommencer les trois essais
                this.bras.arreter();
            }

            //this.transitionnerEtat();
            //this.bras.reinitialiser();
            /* 
            if(!enReglage) 
            {
                enReglage = true;
                this.debutReglage = System.currentTimeMillis();
                return false;
            }
            if(enReglage)
            {
                double maintenant = System.currentTimeMillis();
                if((maintenant - debutReglage) > 1000)
                {
                    this.bras.reglerPointDepart(); 
                    return true;
                }
                return false;
            }
            */
            Timer.delay(1.0);
            this.bras.reglerPointDepart(); 
            return true;
        }
        
        return false;
    }
}
