package frc.robot.soussysteme;

// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxPIDController;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel.motortype
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Materiel;

// https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html
public class Bras extends SousSysteme implements Materiel.Bras
{
    protected CANSparkMax moteurPrincipal;
    protected CANSparkMax moteurSecondaire;

    // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java
    // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java/Motor%20Follower
    public Bras()
    {
        System.out.println("new Bras()");
        this.moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
        this.moteurSecondaire = new CANSparkMax(MOTEUR_SECONDAIRE, MotorType.kBrushless);
        this.moteurPrincipal.restoreFactoryDefaults();
        this.moteurSecondaire.restoreFactoryDefaults();
        
        // REVLibError	follow​(CANSparkMax leader)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID, boolean invert)	
        // REVLibError	follow​(CANSparkMax leader, boolean invert)	
        this.moteurSecondaire.follow(moteurPrincipal);

        //SparkMaxPIDController pidMoteurPrincipal = this.moteurPrincipal.getPIDController();
        //SparkMaxPIDController pidMoteurSecondaire = this.moteurPrincipal.getPIDController();
    }

    public void tourner(double vitesse)
    {
        System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(vitesse));
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
