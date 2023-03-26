package frc.robot.composant;

// https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
import com.revrobotics.CANSparkMax;

// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel.motortype
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax

// https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Limit%20Switch/src/main/java/frc/robot/Robot.java
import com.revrobotics.SparkMaxLimitSwitch;

public class Moteur extends CANSparkMax{
    private SparkMaxLimitSwitch limiteAvant;
    private SparkMaxLimitSwitch limiteArriere;
    public Moteur(int id)
    {
        // CANSparkMax​(int deviceId, CANSparkMaxLowLevel.MotorType type)        
        super(id, MotorType.kBrushless);
        this.restoreFactoryDefaults();
        this.setIdleMode(IdleMode.kBrake);
    }
	public static double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}

    // pattern Builder = Monteur
    public Moteur avecLimites()
    {
        this.limiteAvant = this.getForwardLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        this.limiteArriere = this.getReverseLimitSwitch(SparkMaxLimitSwitch.Type.kNormallyOpen);
        this.limiteAvant.enableLimitSwitch(true);
        this.limiteArriere.enableLimitSwitch(true);
        return this;
    }

    public SparkMaxLimitSwitch getLimiteAvant()
    {
        return this.limiteAvant;
    }

    public SparkMaxLimitSwitch getLimiteArriere()
    {
        return this.limiteArriere;
    }

    public boolean testerLimiteAvant()
    {
        return this.limiteAvant.isPressed();
    }

    // https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Soft%20Limits/src/main/java/frc/robot/Robot.java
    // this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kForward, true);
    // this.moteurPrincipal.enableSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, true);
    // this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kForward, 200);
    // this.moteurPrincipal.setSoftLimit(CANSparkMax.SoftLimitDirection.kReverse, -200);
    public void liberer()
    {
        this.close();
    }

}

//import com.revrobotics.ControlType;
// ancien code
// limit switch
// this.moteurPrincipal.configForwardSoftLimitEnable(true, Constants.kTimeoutMs);
// this.moteurPrincipal.configReverseSoftLimitEnable(true, Constants.kTimeoutMs);
// this.moteurPrincipal.configForwardSoftLimitThreshold(100, Constants.kTimeoutMs);
// this.moteurPrincipal.configReverseSoftLimitThreshold(-100, Constants.kTimeoutMs);

