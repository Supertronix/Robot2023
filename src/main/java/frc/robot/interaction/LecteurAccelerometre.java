package frc.robot.interaction;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;

// https://github.com/kauailabs/navxmxp/blob/master/roborio/java/navXMXP_Java_AutoBalance/src/org/usfirst/frc/team2465/robot/Robot.java
public class LecteurAccelerometre {
    
    AHRS accelerometre;
    double pitch;
    // en degres
    public static final double SEUIL_PENTE = 10;
    public static final double SEUIL_EQUILIBRE  = 5;

    public LecteurAccelerometre()
    {
        accelerometre = new AHRS(SPI.Port.kMXP); 
    }

    public double getPitch()
    {
        this.pitch = this.accelerometre.getPitch();
        return this.pitch;
    }
}
