package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

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
    public void conduireAvecManette();
    public void conduire(double vitesseX, double vitesseY);
    public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite);
    public void conduireAvecAngle(double vitesse, double angle, double vitesseRotation);
    public void conduireSelonGyro(double x, double y, double z, double gyro); 
}

abstract public class Roues implements Roulable, Dirigeable {

    public int ROUE_AVANT_DROITE = 4; // ID 4 
    public int ROUE_ARRIERE_DROITE = 2; // ID 2 
    public int ROUE_AVANT_GAUCHE = 3; // ID 3 
    public int ROUE_ARRIERE_GAUCHE = 1; // ID 1 
    protected CANSparkMax roueAvantDroite;
    protected CANSparkMax roueAvantGauche;
    protected CANSparkMax roueArriereDroite;
    protected CANSparkMax roueArriereGauche;

    public Roues()
    {
        this.roueAvantDroite = new CANSparkMax(ROUE_AVANT_DROITE, MotorType.kBrushless);
        this.roueAvantGauche = new CANSparkMax(ROUE_AVANT_GAUCHE, MotorType.kBrushless);
        this.roueArriereDroite = new CANSparkMax(ROUE_ARRIERE_DROITE, MotorType.kBrushless);
        this.roueArriereGauche = new CANSparkMax(ROUE_ARRIERE_GAUCHE, MotorType.kBrushless);
        this.roueAvantDroite.restoreFactoryDefaults();
        this.roueAvantGauche.restoreFactoryDefaults();
        this.roueArriereDroite.restoreFactoryDefaults();
        this.roueArriereGauche.restoreFactoryDefaults();;    
    }

    public void arreter()
    {
      this.roueAvantGauche.set(0);
      this.roueArriereGauche.set(0);
      this.roueAvantDroite.set(0);
      this.roueArriereDroite.set(0);
    }
 
	public double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}

}
