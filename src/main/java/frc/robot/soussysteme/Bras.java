package frc.robot.soussysteme;

//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;
import frc.robot.Materiel;
import frc.robot.Cinematique;
import frc.robot.composant.Moteur;
import frc.robot.mesure.DetecteurImmobilite;
import edu.wpi.first.wpilibj.Timer;

// limite switch a 13.9 // homing dans limite switch arriere
@SuppressWarnings("resource") // framework roborio appelle exit
public class Bras extends SousSysteme implements Materiel.Bras, Cinematique.Bras, DetecteurImmobilite.Immobilisable
{
    protected Moteur moteurPrincipal;
    protected Moteur moteurSecondaire;
    public POSITION positionNom;
    public double positionDemandee;


    public Bras()
    {
        System.out.println("new Bras()");
        this.positionNom = POSITION.INCONNUE;
        this.positionDemandee = POSITION_INCONNUE;
        
        // creer
        this.moteurPrincipal = new Moteur(MOTEUR_PRINCIPAL).avecLimites();
        this.moteurSecondaire = new Moteur(MOTEUR_SECONDAIRE).avecLimites();

        // configuration
        this.moteurPrincipal.setOpenLoopRampRate(0); // a reactiver
        this.moteurSecondaire.setOpenLoopRampRate(0); // a reactiver
        this.moteurPrincipal.setInverted(true);

        
        // on n'a pas besoin d'inverser le moteur secondaire meme s'il est inverse car il suit
        // selon les tests les deux fonctionnenet

        // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java/Motor%20Follower
        // REVLibError	follow​(CANSparkMax leader)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID, boolean invert)	
        // REVLibError	follow​(CANSparkMax leader, boolean invert)	
        this.moteurSecondaire.follow(moteurPrincipal);

        //SparkMaxPIDController pidMoteurPrincipal = this.moteurPrincipal.getPIDController();
        //SparkMaxPIDController pidMoteurSecondaire = this.moteurPrincipal.getPIDController();
        this.preparerEncodeur();
    }
    protected RelativeEncoder encodeur;
    protected SparkMaxPIDController pid;
    public void preparerEncodeur()
    {
        this.encodeur = this.moteurPrincipal.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        // the encoder could not be inverted separately from motor in brushless mode        
        // encodeur.setInverted(false);setReferenceValue

        pid = this.moteurPrincipal.getPIDController();
        this.reinitialiser();
        
    }
    public void reinitialiser()
    {
        this.preparerCinematique(0,0,0);
        pid.setOutputRange(-0, 0); 
    }
    public void preparerCinematique(double p, double i, double d)
    {
        pid.setP(p);
        pid.setI(i);
        pid.setD(d);
        pid.setOutputRange(-MAX, MAX); 
    }

    public void aller(double position)
    {
        System.out.println("Bras.aller(" + position + ")");
        pid.setReference(position, CANSparkMax.ControlType.kPosition);

    }

    public double getPosition()
    {
        return this.encodeur.getPosition();
    }
    public double getPosition2()
    {
        return this.moteurSecondaire.getEncoder().getPosition();
    }

    public void tourner(double vitesse)
    {
        //System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(vitesse));
        //SmartDashboard.putBoolean("Forward Limit Switch", limiteAvant.isPressed());
        //SmartDashboard.putBoolean("Reverse Limit Switch", limiteArriere.isPressed());
    }
    public void avancer(double vitesse)
    {
        //System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(vitesse));
        SmartDashboard.putNumber("Vitesse bras", vitesse);
        //SmartDashboard.putBoolean("Forward Limit Switch", limiteAvant.isPressed());
        //SmartDashboard.putBoolean("Reverse Limit Switch", limiteArriere.isPressed());
    }
    public void reculer(double vitesse)
    {
        //System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(-vitesse));
        SmartDashboard.putNumber("Vitesse bras", -vitesse);
        //SmartDashboard.putBoolean("Forward Limit Switch", limiteAvant.isPressed());
        //SmartDashboard.putBoolean("Reverse Limit Switch", limiteArriere.isPressed());
    }

    // https://docs.revrobotics.com/sparkmax/software-resources/migrating-ctre-to-rev
    @Override
    public void abaisser() {
        // this.moteurPrincipal.setReference(100, ControlType.kPosition);
        // pidController.setReference(24.0, ControlType.kPosition);
        //this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
    }
    public void relever() {
        //this.moteurPrincipal.set(ControlMode.Position, -100);
        //this.moteurPrincipal.set(ControlMode.PercentOutput, 0.1);
    }
    public void desactiver()
    {
        this.moteurPrincipal.close();
        this.moteurSecondaire.close();
    }
    public void reglerPointDepart()
    {
        System.out.println("Bras.initialiser()");

        while(Math.abs(this.moteurPrincipal.getEncoder().getPosition()) > 1)
        {
            this.moteurPrincipal.getEncoder().setPosition(0);
            Timer.delay(1.0);
        }
        System.out.println("Position : " + this.moteurPrincipal.getEncoder().getPosition());
        System.out.println("Facteur : " + this.moteurPrincipal.getEncoder().getPositionConversionFactor());
        System.out.println("Position 2 : " + this.moteurSecondaire.getEncoder().getPosition());
        System.out.println("Facteur 2 : " + this.moteurSecondaire.getEncoder().getPositionConversionFactor());
    }
    public void rappelerPosition(double position)
    {
        if(positionDemandee != Cinematique.Bras.POSITION_INCONNUE) 
            this.moteurPrincipal.getEncoder().setPosition(position);
    }
    public boolean estAuDepart()
    {
        System.out.println("Bras.estAuDepart()");
        System.out.println(this.moteurPrincipal.getLimiteArriere().isPressed());
        return this.moteurPrincipal.getLimiteArriere().isPressed();
    }
    public void arreter()
    {
        this.moteurPrincipal.set(0);
    }
    @Override
    public void liberer()
    {
        this.moteurPrincipal.liberer();
        this.moteurSecondaire.liberer();
    }

    @Override
    public double getDistancePourImmobilite() {
        return this.moteurPrincipal.getEncoder().getPosition();
    }

}

/**
    // set PID coefficients
    m_pidController.setP(kP);
    m_pidController.setI(kI);
    m_pidController.setD(kD);
    m_pidController.setIZone(kIz);
    m_pidController.setFF(kFF);
    m_pidController.setOutputRange(kMinOutput, kMaxOutput);
*/
