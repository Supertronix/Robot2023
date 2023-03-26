package frc.robot.soussysteme;

// import com.revrobotics.SparkMaxPIDController;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel
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
        this.moteur = new Moteur(MOTEUR, true).avecLimites();
        this.moteur.setInverted(true);

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
        System.out.println("Machoire.ouvrirAvecVitesse("+vitesse+")");
        this.moteur.set(limiter(vitesse));
    }

    public void desactiver()
    {
        this.moteur.close();
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
