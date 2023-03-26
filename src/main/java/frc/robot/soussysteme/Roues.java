package frc.robot.soussysteme;

import com.revrobotics.RelativeEncoder;

import frc.robot.Materiel;
import frc.robot.Cinematique;
import frc.robot.interaction.Manette;
import frc.robot.composant.Moteur;

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

abstract public class Roues extends SousSysteme implements Roulable, Dirigeable, Materiel.Roues, Cinematique.Roues {

    protected Moteur roueAvantDroite;
    protected Moteur roueAvantGauche;
    protected Moteur roueArriereDroite;
    protected Moteur roueArriereGauche;
    protected RelativeEncoder encodeurAvantDroit;
    protected RelativeEncoder encodeurAvantGauche;
    protected RelativeEncoder encodeurArriereDroit;
    protected RelativeEncoder encodeurArriereGauche;        

    public Roues()
    {
        this.roueAvantDroite = new Moteur(ROUE_AVANT_DROITE);
        this.roueAvantGauche = new Moteur(ROUE_AVANT_GAUCHE);
        this.roueArriereDroite = new Moteur(ROUE_ARRIERE_DROITE);
        this.roueArriereGauche = new Moteur(ROUE_ARRIERE_GAUCHE);

        // lire sur getAbsoluteEncoder et getAlternateEncoder()
        // this.encodeurAvantDroit = this.roueAvantDroite.getEncoder();
        // this.encodeurArriereDroit = this.roueArriereDroite.getEncoder();
        // this.encodeurAvantGauche = this.roueAvantGauche.getEncoder();
        // this.encodeurArriereGauche = this.roueArriereGauche.getEncoder();

        
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

    @Override
    public void liberer()
    {
        this.roueAvantDroite.liberer();
        this.roueAvantGauche.liberer();
        this.roueArriereDroite.liberer();
        this.roueArriereGauche.liberer();
    }
 
}
