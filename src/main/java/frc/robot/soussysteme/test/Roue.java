package frc.robot.soussysteme.test;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class Roue {

    protected CANSparkMax roue;
    protected RelativeEncoder encodeur;

    public Roue(int numero)
    {
        this.roue = new CANSparkMax(numero, MotorType.kBrushless);
        this.roue.restoreFactoryDefaults();

        // lire sur getAbsoluteEncoder et getAlternateEncoder()
        // this.encodeur= this.roue.getEncoder();

        this.roue.setIdleMode(IdleMode.kBrake);
        
        //this.roue.stopMotor();
    }

    public void tourner(double vitesse)
    {
        System.out.println("tourner()");
        this.roue.set(limiter(vitesse));
    }

	public double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}

}
