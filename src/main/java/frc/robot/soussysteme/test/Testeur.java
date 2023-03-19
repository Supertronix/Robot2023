package frc.robot.soussysteme.test;

public class Testeur {

    protected Roue roue;

    public Testeur()
    {
        this.roue = new Roue(2);        
    }

    public void executer()
    {
        this.roue.tourner(0.1);
    }
    

}
