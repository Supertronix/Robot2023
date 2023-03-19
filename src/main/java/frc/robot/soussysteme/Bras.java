package frc.robot.soussysteme;

// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
import com.revrobotics.CANSparkMax;
import com.revrobotics.ControlType;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.CANSparkMax.IdleMode;
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
        
        // creer
        this.moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
        this.moteurSecondaire = new CANSparkMax(MOTEUR_SECONDAIRE, MotorType.kBrushless);
        this.moteurPrincipal.restoreFactoryDefaults();
        this.moteurSecondaire.restoreFactoryDefaults();

        // configuration
        this.moteurPrincipal.setOpenLoopRampRate(0);
        this.moteurSecondaire.setOpenLoopRampRate(0);
        this.moteurPrincipal.setIdleMode(IdleMode.kBrake);
        this.moteurSecondaire.setIdleMode(IdleMode.kBrake);
        this.moteurPrincipal.setInverted(true);
        // on n'a pas besoin d'inverser le moteur secondaire meme s'il est inverse car il suit
        // selon les tests les deux fonctionnenet

        // REVLibError	follow​(CANSparkMax leader)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID, boolean invert)	
        // REVLibError	follow​(CANSparkMax leader, boolean invert)	
        this.moteurSecondaire.follow(moteurPrincipal);

        // limit switch
        //this.moteurPrincipal.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);
		//this.moteurPrincipal.configReverseSoftLimitEnable(true, Constants.kTimeoutMs);
		//this.moteurPrincipal.configForwardSoftLimitThreshold(100, Constants.kTimeoutMs);
		//this.moteurPrincipal.configReverseSoftLimitThreshold(-100, Constants.kTimeoutMs);

        //https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Soft%20Limits/src/main/java/frc/robot/Robot.java
        this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
        this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 200);
        this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -200);

        //SparkMaxPIDController pidMoteurPrincipal = this.moteurPrincipal.getPIDController();
        //SparkMaxPIDController pidMoteurSecondaire = this.moteurPrincipal.getPIDController();
    }

    public void tourner(double vitesse)
    {
        System.out.println("tourner()");
        this.moteurPrincipal.set(limiter(vitesse));
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
