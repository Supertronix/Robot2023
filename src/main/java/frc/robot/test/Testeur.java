package frc.robot.test;

import frc.robot.interaction.Manette;
import frc.robot.soussysteme.Bras;

public class Testeur {

    //protected Roue roue;
    //protected Moteur moteur;
    //protected Bras bras;
    protected Manette manette;

    public Testeur()
    {
        //this.roue = new Roue(2);        
        //this.moteur = new Moteur(5);
        //this.bras = new Bras();
        this.manette = new ManetteTestBras();
    }

    public void executer()
    {
        //this.roue.tourner(0.1);
        //this.moteur.tourner(0.1);
        //this.bras.tourner(0.1);
    }
    

}
