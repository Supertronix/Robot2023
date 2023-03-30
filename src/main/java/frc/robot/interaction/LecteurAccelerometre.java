package frc.robot.interaction;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Timer;

// https://github.com/kauailabs/navxmxp/blob/master/roborio/java/navXMXP_Java_AutoBalance/src/org/usfirst/frc/team2465/robot/Robot.java
public class LecteurAccelerometre {
    
    AHRS accelerometre;
    double pitch;
    double roll;
    double yaw;
    double pitchDebut;
    double rollDebut;
    double yawDebut;

    public enum UNITE {RADIAN, DEGRES};
    public static final double SEUIL_PENTE = 12;
    public static final double SEUIL_EQUILIBRE  = 5;

    protected LecteurAccelerometre()
    {
        accelerometre = new AHRS(SPI.Port.kMXP); 
        accelerometre.reset();
        accelerometre.calibrate();
        while(accelerometre.isCalibrating())
        {
            Timer.delay(1);
        }
        //accelerometre.resetDisplacement();
    }

    // singleton pour calibrer une seule fois
    protected static LecteurAccelerometre instance = null;
    public static LecteurAccelerometre getInstance()
    {
        if(instance == null) instance = new LecteurAccelerometre();
        return instance;
    }

    void calibrerManuellement()
    {
        this.pitchDebut = accelerometre.getPitch();
        this.rollDebut = accelerometre.getRoll();
        this.yawDebut = accelerometre.getYaw();
    }

    public double getPitch()
    {
        this.pitch = (this.accelerometre.getPitch() - this.pitchDebut);
        return this.pitch;
    }
    public double getPitch(UNITE unites)
    {
        if(unites == UNITE.DEGRES) return this.getPitch();
        if(unites == UNITE.RADIAN) return this.getPitch() * Math.PI / 180.0;
        return this.getPitch();
    }
    public double getRoll()
    {
        this.roll = (this.accelerometre.getRoll() - this.pitchDebut);
        return this.roll;
    }
    public double getRoll(UNITE unites)
    {
        if(unites == UNITE.DEGRES) return this.getRoll();
        if(unites == UNITE.RADIAN) return this.getRoll() * Math.PI / 180.0;
        return this.getRoll();
    }
    public double getYaw()
    {
        this.yaw = (this.accelerometre.getYaw() - this.yawDebut);
        return this.yaw;
    }
    public double getYaw(UNITE unites)
    {
        if(unites == UNITE.DEGRES) return this.getYaw();
        if(unites == UNITE.RADIAN) return this.getYaw() * Math.PI / 180.0;
        return this.getYaw();
    }

    public boolean depasseSeuilPente()
    {
        //System.out.println("PITCH = " + this.getPitch());
        //System.out.println("ROLL = " + this.getRoll());
        return (Math.abs(this.getRoll()) >= SEUIL_PENTE);
    }
    public boolean depasseSeuilEquilibre()
    {
        //System.out.println("PITCH = " + this.getPitch());
        //System.out.println("ROLL = " + this.getRoll());
        return (Math.abs(this.getRoll()) <= SEUIL_EQUILIBRE);
    }}
/*
avX-Sensor Java library for FRC
navX-Sensor Yaw Reset request ignored - startup calibration is currently in progress.
navX-Sensor Connected.
navX-Sensor Board Type 50 (navX-MXP (Classic))
navX-Sensor firmware version 3.1
navX-Sensor onboard startup calibration complete.
navX-Sensor Yaw angle auto-reset to 0.0 due to startup calibration.

 */