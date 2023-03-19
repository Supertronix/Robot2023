package frc.robot;
import frc.robot.soussysteme.*;

public class Robot {
    
    // en attendant que java invente les properties !
    public Roues roues;
    public Bras bras;  

    protected Robot()
    {
        this.roues = new RouesMecanum();
        this.bras = new Bras();    
    }

    public static Robot instance = null;
    public static Robot getInstance()
    {
        if(Robot.instance == null) Robot.instance = new Robot();
        return Robot.instance;
    }
}
