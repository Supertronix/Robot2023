package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;


public class Roues {
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

    public void avancer()
    {
        this.roueAvantDroite.set(-0.05);
        this.roueAvantGauche.set(0.05);
        this.roueArriereGauche.set(0.05);
        this.roueArriereDroite.set(-0.05);    
    }

    public void reculer()
    {
        this.roueAvantDroite.set(0.05);
        this.roueAvantGauche.set(-0.05);
        this.roueArriereGauche.set(-0.05);
        this.roueArriereDroite.set(0.05);    
    }

    public void tasserCoteDroit()
    {
        this.roueAvantDroite.set(0.05);
        this.roueAvantGauche.set(0.05);
        this.roueArriereGauche.set(-0.05);
        this.roueArriereDroite.set(-0.05);
   }
    public void tasserCoteGauche()
    {
        this.roueAvantDroite.set(-0.05);
        this.roueAvantGauche.set(-0.05);
        this.roueArriereGauche.set(0.05);
        this.roueArriereDroite.set(0.05);    
    }
    public void tournerDroite()
    {
        this.roueAvantDroite.set(-0.05);
        this.roueAvantGauche.set(-0.05);
        this.roueArriereGauche.set(-0.05);
        this.roueArriereDroite.set(-0.05);    
    }
    public void tournerGauche()
    {
        this.roueAvantDroite.set(0.05);
        this.roueAvantGauche.set(0.05);
        this.roueArriereGauche.set(0.05);
        this.roueArriereDroite.set(0.05);
    }

    public void avancerEtTournerDroite()
    {
        this.roueAvantDroite.set(-0.05);
        this.roueAvantGauche.set(0.00); // plus c'est negatif plus la courbe est longue
        this.roueArriereGauche.set(0.00);
        this.roueArriereDroite.set(-0.05);
    }   
    public void avancerEtTournerGauche()
    {
        this.roueAvantDroite.set(0.00);
        this.roueAvantGauche.set(0.05);
        this.roueArriereGauche.set(0.05);
        this.roueArriereDroite.set(0.00);
    }
    public void reculerEtTournerDroite()
    {
        this.roueAvantDroite.set(0.05);
        this.roueAvantGauche.set(0.00); 
        this.roueArriereGauche.set(0.00);
        this.roueArriereDroite.set(0.05);
    }   
    public void reculerEtTournerGauche()
    {
        this.roueAvantDroite.set(0.00);
        this.roueAvantGauche.set(-0.05);
        this.roueArriereGauche.set(-0.05);
        this.roueArriereDroite.set(0.00);
    }

}
