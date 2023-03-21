package frc.robot.soussysteme;

import com.revrobotics.SparkMaxLimitSwitch;
import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.SparkMaxPIDController;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel

import frc.robot.Materiel;
import frc.robot.composant.Moteur;
// https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html
public class Bras extends SousSysteme implements Materiel.Bras
{
    protected Moteur moteurPrincipal;
    protected Moteur moteurSecondaire;
    private SparkMaxLimitSwitch limiteAvant;
    private SparkMaxLimitSwitch limiteArriere;

    public Bras()
    {
        System.out.println("new Bras()");
        
        // creer
        this.moteurPrincipal = new Moteur(MOTEUR_PRINCIPAL);
        this.moteurSecondaire = new Moteur(MOTEUR_SECONDAIRE);

        // configuration
        this.moteurPrincipal.setOpenLoopRampRate(0);
        this.moteurSecondaire.setOpenLoopRampRate(0);
        this.moteurPrincipal.setIdleMode(IdleMode.kBrake);
        this.moteurSecondaire.setIdleMode(IdleMode.kBrake);
        this.moteurPrincipal.setInverted(true);
        // on n'a pas besoin d'inverser le moteur secondaire meme s'il est inverse car il suit
        // selon les tests les deux fonctionnenet

        // https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java/Motor%20Follower
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

        // https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Soft%20Limits/src/main/java/frc/robot/Robot.java
        // https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Limit%20Switch/src/main/java/frc/robot/Robot.java
        // https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
        // this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
        // this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);

        //https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Soft%20Limits/src/main/java/frc/robot/Robot.java
        // SparkMaxLimitSwitch	getForwardLimitSwitch
        // this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 200);
        // this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -200);
        
        // nouveau code
        this.limiteAvant = this.moteurPrincipal.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        this.limiteArriere = this.moteurPrincipal.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        this.limiteAvant.enableLimitSwitch(true);
        this.limiteArriere.enableLimitSwitch(true);

        //SparkMaxPIDController pidMoteurPrincipal = this.moteurPrincipal.getPIDController();
        //SparkMaxPIDController pidMoteurSecondaire = this.moteurPrincipal.getPIDController();

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
