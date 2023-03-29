package frc.robot;
import frc.robot.soussysteme.*;

public class Robot {
    
    // en attendant que java invente les properties !
    public Roues roues = null;
    public Bras bras = null;  
    public Machoire machoire = null;
    public boolean equilibre = false;

    protected Robot()
    {
        this.roues = new RouesMecanumSynchro();
        //this.roues = new RouesMecanum();
        this.bras = new Bras();    
        this.machoire = new Machoire();
    }

    public static Robot instance = null;
    public static Robot getInstance()
    {
        if(Robot.instance == null) Robot.instance = new Robot();
        return Robot.instance;
    }

    public void fermer()
    {
        this.bras.liberer();
        this.roues.liberer();
        this.machoire.liberer();
    }
}
