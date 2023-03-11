package frc.robot.soussysteme;

// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmax
import com.revrobotics.CANSparkMax;
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel
// https://codedocs.revrobotics.com/java/com/revrobotics/cansparkmaxlowlevel.motortype
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import frc.robot.Materiel;

// https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html
public class Bras implements Materiel.Bras
{
    protected CANSparkMax moteurPrincipal;
    protected CANSparkMax moteurSecondaire1;
    protected CANSparkMax moteurSecondaire2;

    public Bras()
    {
        System.out.println("new Bras()");
        this.moteurPrincipal = new CANSparkMax(MOTEUR_PRINCIPAL, MotorType.kBrushless);
        this.moteurSecondaire1 = new CANSparkMax(MOTEUR_SECONDAIRE_1, MotorType.kBrushless);
        this.moteurSecondaire2 = new CANSparkMax(MOTEUR_SECONDAIRE_2, MotorType.kBrushless);       
        
        // REVLibError	follow​(CANSparkMax leader)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID)	
        // REVLibError	follow​(CANSparkMax.ExternalFollower leader, int deviceID, boolean invert)	
        // REVLibError	follow​(CANSparkMax leader, boolean invert)	
        this.moteurSecondaire1.follow(moteurPrincipal);
        this.moteurSecondaire2.follow(moteurPrincipal);
        
    }

    
}
