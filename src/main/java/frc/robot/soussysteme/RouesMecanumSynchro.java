package frc.robot.soussysteme;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html    
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
        this.roueAvantGauche.follow(this.roueAvantDroite, true);
        this.roueArriereDroite.follow(this.roueAvantDroite, false);
        this.roueArriereGauche.follow(this.roueAvantDroite, true);

        // import edu.wpi.first.math.geometry.Rotation2d;
        // this.mecanum = new MecanumDrive(this.roueArriereDroite, this.roueArriereGauche, this.roueAvantDroite, this.roueAvantGauche);
        this.preparerConsigne();
    }

    public void preparerConsigne()
    {
        this.encodeur = this.roueAvantDroite.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, ticParTour);
        // Calibrez l'encodeur
        //this.encodeur.setDistancePerPulse(2); // Remplacez distancePerPulse par la distance parcourue par une impulsion de l'encodeur
        //this.encodeur.reset(); // Réinitialisez l'encodeur
        //this.encodeur.calibrate(); // Calibrez l'encodeur

        // Obtenez la valeur initiale de l'encodeur après la calibration
        //double initPosition = this.encodeur.getDistance();        // the encoder could not be inverted separately from motor in brushless mode        
        // encodeur.setInverted(false);

        this.pid = this.roueAvantDroite.getPIDController();
        this.pid.setFeedbackDevice(encodeur);
        //this.roue.setFeedbackDevice(encoder);
        this.pid.setP(0.2);
        //pid.setI(1e-4);
        //pid.setD(1);
        this.pid.setOutputRange(-0.2, 0.2); 
    }
    public void avancer(double pas)
    {
        System.out.println("avancer()");
        // 40 = barre jusqua plateforme = 6 pieds
        this.encodeur.setPosition(0);
        pid.setReference(pas, CANSparkMax.ControlType.kPosition); // reculer 
        //pid.setReference(50, CANSparkMax.ControlType.kPosition);
    }
}
