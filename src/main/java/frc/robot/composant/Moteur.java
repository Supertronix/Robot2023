package frc.robot.composant;

import com.revrobotics.CANSparkMax;

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
