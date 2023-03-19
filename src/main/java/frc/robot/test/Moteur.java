package frc.robot.test;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.soussysteme.SousSysteme;

public class Moteur extends SousSysteme{

    protected CANSparkMax roue;
    protected RelativeEncoder encodeur;

    public Moteur(int numero)
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
