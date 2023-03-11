package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Materiel;

public class Bras implements Materiel.Bras
{
    protected CANSparkMax moteurPrincipal;
    protected CANSparkMax moteurSecondaire1;
    protected CANSparkMax moteurSecondaire2;

    public Bras()
    {
        System.out.println("new Bras()");
        moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
        moteurSecondaire1 = new CANSparkMax(MOTEUR_SECONDAIRE_1, MotorType.kBrushless);
        moteurSecondaire2 = new CANSparkMax(MOTEUR_SECONDAIRE_2, MotorType.kBrushless);        
    }
    
    
}
