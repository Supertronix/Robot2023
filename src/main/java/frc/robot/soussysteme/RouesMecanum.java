package frc.robot.soussysteme;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/math/geometry/Rotation2d.html
import edu.wpi.first.math.geometry.Rotation2d;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/drive/MecanumDrive.html    
import edu.wpi.first.wpilibj.drive.MecanumDrive;
import frc.robot.interaction.Manette;

public class RouesMecanum extends Roues {

    protected MecanumDrive mecanum;
    protected double facteur = 1;

    // import edu.wpi.first.math.geometry.Rotation2d;
    // this.mecanum = new MecanumDrive(this.roueArriereDroite, this.roueArriereGauche, this.roueAvantDroite, this.roueAvantGauche);
    public RouesMecanum()
    {
        this.facteur = FACTEUR_ROUES;
        this.roueAvantGauche.setInverted(true);
        this.roueArriereGauche.setInverted(true); 
    }
    public void activerModeHolonomique()
    {
        this.roueAvantGauche.setInverted(true);
        this.roueArriereGauche.setInverted(true); 
    }
    public void setFacteur(double facteur)
    {
        this.facteur = facteur;
    }
    public void avancer(double vitesse)
    {
        this.roueAvantDroite.set(limiter(-vitesse));
        this.roueAvantGauche.set(limiter(vitesse));
        this.roueArriereGauche.set(limiter(vitesse));
        this.roueArriereDroite.set(limiter(-vitesse));    
    }

    public void reculer(double vitesse)
    {
        this.roueAvantDroite.set(limiter(vitesse));
        this.roueAvantGauche.set(limiter(-vitesse));
        this.roueArriereGauche.set(limiter(-vitesse));
        this.roueArriereDroite.set(limiter(vitesse));    
    }

    public void tasserDroite(double vitesse)
    {
        this.roueAvantDroite.set(limiter(vitesse));
        this.roueAvantGauche.set(limiter(vitesse));
        this.roueArriereGauche.set(limiter(-vitesse));
        this.roueArriereDroite.set(limiter(-vitesse));
   }
    public void tasserGauche(double vitesse)
    {
        this.roueAvantDroite.set(limiter(-vitesse));
        this.roueAvantGauche.set(limiter(-vitesse));
        this.roueArriereGauche.set(limiter(vitesse));
        this.roueArriereDroite.set(limiter(vitesse));    
    }
    public void tournerDroite(double vitesse)
    {
        this.roueAvantDroite.set(limiter(-vitesse));
        this.roueAvantGauche.set(limiter(-vitesse));
        this.roueArriereGauche.set(limiter(-vitesse));
        this.roueArriereDroite.set(limiter(-vitesse));    
    }
    public void tournerGauche(double vitesse)
    {
        this.roueAvantDroite.set(limiter(vitesse));
        this.roueAvantGauche.set(limiter(vitesse));
        this.roueArriereGauche.set(limiter(vitesse));
        this.roueArriereDroite.set(limiter(vitesse));
    }

    public void avancerEtTournerDroite(double vitesse)
    {
        this.roueAvantDroite.set(limiter(-vitesse));
        this.roueAvantGauche.set(0.00); 
        this.roueArriereGauche.set(0.00);
        this.roueArriereDroite.set(limiter(-vitesse));
    }   
    public void avancerEtTournerGauche(double vitesse)
    {
        this.roueAvantDroite.set(0.00);
        this.roueAvantGauche.set(limiter(vitesse));
        this.roueArriereGauche.set(limiter(vitesse));
        this.roueArriereDroite.set(0.00);
    }
    public void reculerEtTournerDroite(double vitesse)
    {
        this.roueAvantDroite.set(limiter(vitesse));
        this.roueAvantGauche.set(0.00); 
        this.roueArriereGauche.set(0.00);
        this.roueArriereDroite.set(limiter(vitesse));
    }   
    public void reculerEtTournerGauche(double vitesse)
    {
        this.roueAvantDroite.set(0.00);
        this.roueAvantGauche.set(limiter(-vitesse));
        this.roueArriereGauche.set(limiter(-vitesse));
        this.roueArriereDroite.set(0.00);
    }
    public void tourner(double vitesseGauche, double vitesseDroite)
    {
      System.out.println("tourner("+vitesseGauche+","+vitesseDroite+")");
      this.roueAvantGauche.set(limiter(vitesseGauche));
      this.roueArriereGauche.set(limiter(-vitesseGauche));
      this.roueAvantDroite.set(limiter(vitesseDroite));
      this.roueArriereDroite.set(limiter(-vitesseDroite));
    }   
    double vitesseAvantGauche; 
    double vitesseAvantDroite;
    double vitesseArriereGauche;
    double vitesseArriereDroite;
    public void conduireAvecManette(Manette manette)
    {
        vitesseAvantGauche = facteur*(manette.getAxeMainGauche().y + manette.getAxeMainGauche().x + manette.getAxeMainDroite().x);
        vitesseAvantDroite = facteur*(manette.getAxeMainGauche().y - manette.getAxeMainGauche().x - manette.getAxeMainDroite().x);
        vitesseArriereGauche = facteur*(manette.getAxeMainGauche().y - manette.getAxeMainGauche().x + manette.getAxeMainDroite().x);
        vitesseArriereDroite = facteur*(manette.getAxeMainGauche().y + manette.getAxeMainGauche().x - manette.getAxeMainDroite().x);
        this.roueAvantGauche.set(limiter(vitesseAvantGauche));
        this.roueAvantDroite.set(limiter(vitesseAvantDroite));
        this.roueArriereGauche.set(limiter(vitesseArriereGauche));
        this.roueArriereDroite.set(limiter(vitesseArriereDroite));
	    //Formule 2017 (x + yGauche, yDroite - x, yGauche - x, x + yDroite);
    }
    public void conduire(double vitesseX, double vitesseY)
    {
      System.out.println("conduire("+vitesseY+","+vitesseX+")");
      this.mecanum.driveCartesian(vitesseX, vitesseY, 0);
    }
    // les angles sont en radians
    public void conduireAvecAngle(double vitesse, double angle, double vitesseRotation)
    {
      System.out.println("conduire("+vitesse+","+angle+")");
      //this.mecanum.drivePolar(vitesse, new Rotation2d(angle), vitesseRotation);
    }
    public void conduireSelonGyro(double x, double y, double z, double gyro)
    {
      //this.mecanum.driveCartesian(x,y,z, new Rotation2d(gyro));
    }

    public void conduireToutesDirections(double vitesseAvantGauche, double vitesseAvantDroite, double vitesseArriereGauche, double vitesseArriereDroite) 
    {
        //System.out.println("conduireToutesDirections("+vitesseAvantGauche+ " "+ vitesseAvantDroite + " " + vitesseArriereGauche + " " + vitesseArriereDroite + ")");
        this.roueAvantGauche.set(limiter(vitesseAvantGauche));
        this.roueAvantDroite.set(limiter(vitesseAvantDroite));
        this.roueArriereGauche.set(limiter(vitesseArriereGauche));
        this.roueArriereDroite.set(limiter(vitesseArriereDroite));
    }

    public double getPosition()
    {
        return this.roueAvantDroite.getEncoder().getPosition();
    }
}
