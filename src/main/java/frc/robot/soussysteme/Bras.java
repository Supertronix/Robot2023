package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.Materiel;

public class Bras implements Materiel.Bras
{
    protected CANSparkMax moteurPrincipal;

    public Bras()
    {
        System.out.println("new Bras()");
        moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
    }
    
}
