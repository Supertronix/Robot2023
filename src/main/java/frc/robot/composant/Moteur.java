package frc.robot.composant;

// https://github.com/REVrobotics/SPARK-MAX-Examples/tree/master/Java
import com.revrobotics.CANSparkMax;

// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel.motortype
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax

public class Moteur extends CANSparkMax{
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

}

//import com.revrobotics.ControlType;
