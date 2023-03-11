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
public class Bras implements Materiel.Bras
{
    protected CANSparkMax moteurPrincipal;
    protected CANSparkMax moteurSecondaire1;
    protected CANSparkMax moteurSecondaire2;
    double test = 5;

    // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java
    // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java/Motor%20Follower
    public Bras()
    {
        System.out.println("new Bras()");
        this.moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
        this.moteurSecondaire1 = new CANSparkMax(MOTEUR_SECONDAIRE_1, MotorType.kBrushless);
        this.moteurSecondaire2 = new CANSparkMax(MOTEUR_SECONDAIRE_2, MotorType.kBrushless);       
        
        // REVLibError	follow​(CANSparkMax leader)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID, boolean invert)	
        // REVLibError	follow​(CANSparkMax leader, boolean invert)	
        this.moteurSecondaire1.follow(moteurPrincipal);
        this.moteurSecondaire2.follow(moteurPrincipal);

        SparkMaxPIDController pidMoteurPrincipal = this.moteurPrincipal.getPIDController();
        SparkMaxPIDController pidMoteurSecondaire1 = this.moteurPrincipal.getPIDController();
        SparkMaxPIDController pidMoteurSecondaire2 = this.moteurPrincipal.getPIDController();
        SmartDashboard.putNumber("test",test );
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
