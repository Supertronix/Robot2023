package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Encoder;
import frc.robot.Materiel;
import frc.robot.interaction.Manette;

interface Roulable
{
    public void arreter();
    public void avancer(double vitesse);
    public void reculer(double vitesse);
    public void tasserDroite(double vitesse);
    public void tasserGauche(double vitesse);
    public void tournerDroite(double vitesse);
    public void tournerGauche(double vitesse);
    public void tourner(double vitesseGauche, double vitesseDroite);
    public void avancerEtTournerDroite(double vitesse);
    public void avancerEtTournerGauche(double vitesse);
    public void reculerEtTournerDroite(double vitesse);
    public void reculerEtTournerGauche(double vitesse);
}
interface Dirigeable
{
    public void conduireAvecManette(Manette manette);
    public void conduire(double vitesseX, double vitesseY);
    public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite);
    public void conduireAvecAngle(double vitesse, double angle, double vitesseRotation);
    public void conduireSelonGyro(double x, double y, double z, double gyro); 
}

abstract public class Roues implements Roulable, Dirigeable, Materiel.Roues {

    protected CANSparkMax roueAvantDroite;
    protected CANSparkMax roueAvantGauche;
    protected CANSparkMax roueArriereDroite;
    protected CANSparkMax roueArriereGauche;
    protected RelativeEncoder encodeurAvantDroit;
    protected RelativeEncoder encodeurAvantGauche;
    protected RelativeEncoder encodeurArriereDroit;
    protected RelativeEncoder encodeurArriereGauche;        

    public Roues()
    {
        this.roueAvantDroite = new CANSparkMax(ROUE_AVANT_DROITE, MotorType.kBrushless);
        this.roueAvantGauche = new CANSparkMax(ROUE_AVANT_GAUCHE, MotorType.kBrushless);
        this.roueArriereDroite = new CANSparkMax(ROUE_ARRIERE_DROITE, MotorType.kBrushless);
        this.roueArriereGauche = new CANSparkMax(ROUE_ARRIERE_GAUCHE, MotorType.kBrushless);
        this.roueAvantDroite.restoreFactoryDefaults();
        this.roueAvantGauche.restoreFactoryDefaults();
        this.roueArriereDroite.restoreFactoryDefaults();
        this.roueArriereGauche.restoreFactoryDefaults();   

        // lire sur getAbsoluteEncoder et getAlternateEncoder()
        // this.encodeurAvantDroit = this.roueAvantDroite.getEncoder();
        // this.encodeurArriereDroit = this.roueArriereDroite.getEncoder();
        // this.encodeurAvantGauche = this.roueAvantGauche.getEncoder();
        // this.encodeurArriereGauche = this.roueArriereGauche.getEncoder();

        this.roueAvantDroite.setIdleMode(IdleMode.kBrake);
        this.roueAvantGauche.setIdleMode(IdleMode.kBrake);
        this.roueArriereDroite.setIdleMode(IdleMode.kBrake);
        this.roueArriereGauche.setIdleMode(IdleMode.kBrake);
        
        this.arreter();
    }

    public void arreter()
    {
      this.roueAvantDroite.stopMotor();
      this.roueAvantGauche.stopMotor();
      this.roueArriereDroite.stopMotor();
      this.roueArriereGauche.stopMotor();

      // this.roueAvantGauche.set(0);
      // this.roueArriereGauche.set(0);
      // this.roueAvantDroite.set(0);
      // this.roueArriereDroite.set(0);
    }
 
	public double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}

}
