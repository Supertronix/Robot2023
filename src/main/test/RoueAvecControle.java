package frc.robot.test;

/**
 * Unhandled exception: java.lang.IllegalStateException: 
 * The encoder type must be kHallSensor 
 * when the SPARK MAX is configured in brushless mode.
 * The countPerRev must be 42 when using the hall sensor
 * roue.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
**/


/* 
 To use an external quadrature encoder with a brushless motor, 
you must wire it as an Alternate Encoder, 
and then call getAlternateEncoder() on the CANSparkMax object.
SparkMaxRelativeEncoder.Type.kQuadrature
 */

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.RelativeEncoder;
import com.revrobotics.SparkMaxPIDController;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.soussysteme.SousSysteme;

// https://github.com/REVrobotics/SPARK-MAX-Examples/blob/master/Java/Position%20Closed%20Loop%20Control/src/main/java/frc/robot/Robot.java
public class RoueAvecControle extends SousSysteme{

    protected CANSparkMax roue;
    protected RelativeEncoder encodeur;
    protected SparkMaxPIDController pid;

    public RoueAvecControle()
    {
        this.roue = new CANSparkMax(1, MotorType.kBrushless);
        this.roue.restoreFactoryDefaults();

        // lire sur getAbsoluteEncoder et getAlternateEncoder()
        // this.encodeur= this.roue.getEncoder();

        this.roue.setIdleMode(IdleMode.kBrake);
        
        //this.roue.stopMotor();

        this.encodeur = this.roue.getEncoder(SparkMaxRelativeEncoder.Type.kHallSensor, 42);
        // the encoder could not be inverted separately from motor in brushless mode        
        // encodeur.setInverted(false);
        pid = this.roue.getPIDController();
        pid.setFeedbackDevice(encodeur);
        //this.roue.setFeedbackDevice(encoder);
        pid.setP(0.3);
        //pid.setI(1e-4);
        //pid.setD(1);
        pid.setOutputRange(-1, 1); 
    }

    public void tourner(double vitesse)
    {
        System.out.println("tourner()");
        //this.roue.set(limiter(vitesse));
        SmartDashboard.putNumber("Position", encodeur.getPosition());
    }

    public void avancer()
    {
        System.out.println("avancer()");
        pid.setReference(50, CANSparkMax.ControlType.kPosition);
        //pid.setReference(50, CANSparkMax.ControlType.kPosition);
    }

}
