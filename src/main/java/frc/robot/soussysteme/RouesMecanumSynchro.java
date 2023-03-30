package frc.robot.soussysteme;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html    
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMax;

// diametre 6 pouces
public class RouesMecanumSynchro extends RouesMecanum {

    protected MecanumDrive mecanum;
    protected RelativeEncoder encodeur;
    protected SparkMaxPIDController pid;

    protected double circonference = 18.45;
    protected int ticParTour = 42;


    public RouesMecanumSynchro()
    {
        this.activerModeSynchronisees();
        this.preparerConsigneInitiale();
    }

    public RouesMecanum convertirEnRouesHolonomiques()
    {
        this.annulerConsigneInitiale();
        this.desactiverModeSynchronisees();
        this.reinitialiserMoteurs();
        this.activerModeHolonomique();
        return (RouesMecanum)this;
    }
    public RouesMecanum convertirEnRouesSynchro()
    {
        this.annulerConsigneInitiale();
        //this.desactiverModeSynchronisees();
        this.reinitialiserMoteurs();
        this.activerModeSynchronisees();
        return (RouesMecanum)this;
    }
    public void reinitialiserMoteurs()
    {
        this.roueAvantDroite.initialiser();
        this.roueAvantGauche.initialiser();
        this.roueArriereDroite.initialiser();
        this.roueArriereGauche.initialiser();
    }

    public void activerModeSynchronisees()
    {
        this.roueAvantGauche.follow(this.roueAvantDroite, true);
        this.roueArriereDroite.follow(this.roueAvantDroite, false);
        this.roueArriereGauche.follow(this.roueAvantDroite, true);
    }
    public void desactiverModeSynchronisees()
    {
        //this.roueAvantGauche.follow(null);
        //this.roueArriereDroite.follow(null);
        //this.roueArriereGauche.follow(null);
    }

    public void annulerConsigneInitiale()
    {
        this.avancerAvecVitesse(0);
        this.preparerCinematique(0,0,0);
        this.pid.setOutputRange(-0, 0); 
        //this.pid.setFeedbackDevice(null);

    }
    public void preparerConsigneInitiale()
    {
        this.encodeur = this.roueAvantDroite.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, ticParTour);
        this.pid = this.roueAvantDroite.getPIDController();
        this.pid.setFeedbackDevice(encodeur);
        this.preparerCinematique(0.2, 0, 0); // test a la place de this.pid.setP(0.2);
        this.pid.setOutputRange(-0.2, 0.2); 
    }
    public void avancer(double pas)
    {
        System.out.println("avancer()");
        // 40 = barre jusqua plateforme = 6 pieds
        this.encodeur.setPosition(0);
        pid.setReference(pas, CANSparkMax.ControlType.kPosition); // reculer 
    }
    public void avancerAvecVitesse(double vitesse)
    {
        //System.out.println("avancerAvecVitesse()");
        this.roueAvantDroite.set(vitesse);
    }
    public void reinitialiser()
    {
        this.preparerCinematique(0,0,0);
        this.pid.setOutputRange(-0, 0); 
        this.avancerAvecVitesse(0);
    }
    public void preparerCinematique(double p, double i, double d)
    {
        this.pid.setP(p);
        this.pid.setI(i);
        this.pid.setD(d);
        //this.pid.setOutputRange(-MAX, MAX); 
    }
}
    //pid.setI(1e-4);
        //pid.setD(1);
