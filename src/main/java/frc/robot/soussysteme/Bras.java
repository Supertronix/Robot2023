package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.SparkMaxPIDController;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Materiel;
import frc.robot.composant.Moteur;
// https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html
public class Bras extends SousSysteme implements Materiel.Bras
{

    protected Moteur moteurPrincipal;
    protected Moteur moteurSecondaire;

    public Bras()
    {
        System.out.println("new Bras()");
        
        // creer
        this.moteurPrincipal = new Moteur(MOTEUR_PRINCIPAL).avecLimites();
        this.moteurSecondaire = new Moteur(MOTEUR_SECONDAIRE).avecLimites();

        // configuration
        this.moteurPrincipal.setOpenLoopRampRate(0);
        this.moteurSecondaire.setOpenLoopRampRate(0);
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
        // encodeur.setInverted(false);
        pid = this.moteurPrincipal.getPIDController();
        pid.setFeedbackDevice(encodeur);
        //this.roue.setFeedbackDevice(encoder);
        pid.setP(0.1);
        //pid.setI(1e-4);
        //pid.setD(1);
        pid.setOutputRange(-0.1, 0.1); 
    }
    public void aller(double position)
    {
        System.out.println("Bras.aller()" + position);
        pid.setReference(position, CANSparkMax.ControlType.kPosition);

    }

    public double getPosition()
    {
        return this.encodeur.getPosition();
    }


    public void tourner(double vitesse)
    {
        //System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(vitesse));
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
    public void initialiser()
    {
        this.moteurPrincipal.getEncoder().setPosition(0);
    }
    public boolean estAuDepart()
    {
        return this.moteurPrincipal.getLimiteAvant().isPressed();
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
