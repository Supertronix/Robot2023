package frc.robot.soussysteme;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
// import com.revrobotics.SparkMaxPIDController;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.SparkMaxPIDController;
import com.revrobotics.RelativeEncoder;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import frc.robot.Materiel;
import frc.robot.composant.Moteur;

@SuppressWarnings("resource") // framework roborio appelle exit
public class Machoire extends SousSysteme implements Materiel.Machoire
{
    protected Moteur moteur;

    public Machoire()
    {
        System.out.println("new Machoire()");
        
        // creer
        this.moteur = new Moteur(MOTEUR).avecLimites();

        // configuration
        this.moteur.setOpenLoopRampRate(0);
    }
    public void aller(double position)
    {
        System.out.println("Bras.aller()" + position);

    }

    public void fermerAvecVitesse(double vitesse) {
        this.moteur.set(limiter(-vitesse));
    }
    public void ouvrirAvecVitesse(double vitesse) {
        this.moteur.set(limiter(vitesse));
    }

    public void desactiver()
    {
        this.moteur.close();
    }
    public void initialiser()
    {
    }
    public boolean estFermee()
    {
        return this.moteur.getLimiteArriere().isPressed();
    }
    public boolean estOuverte()
    {
        return this.moteur.getLimiteAvant().isPressed();
    }

    @Override
    public void liberer()
    {
        this.moteur.liberer();
    }
}
