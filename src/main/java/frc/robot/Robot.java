package frc.robot;
import frc.robot.soussysteme.*;

public class Robot {
    
    // en attendant que java invente les properties !
    public Roues roues;
    public Bras bras;  

    public Robot()
    {
        this.roues = new RouesMecanum();
        //this.bras = new Bras();    
    }
}
