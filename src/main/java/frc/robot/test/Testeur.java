package frc.robot.test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.soussysteme.Bras;

public class Testeur {

    //protected Roue roue;
    //protected Moteur moteur;
    //protected Bras bras;
    protected ManetteTestBras manette;
    protected CommandBase commandeAbaisser;
    protected CommandBase commandeRelever;
    public Testeur()
    {
        //this.roue = new Roue(2);        
        //this.moteur = new Moteur(5);
        //this.bras = new Bras();
        this.manette = new ManetteTestBras();
        double vitesse = 0.1;
        commandeAbaisser = new CommandeAbaisserBras(Robot.getInstance().bras, manette, vitesse);
        commandeRelever = new CommandeReleverBras(Robot.getInstance().bras, manette, vitesse);
    }

    public void executer()
    {
        commandeAbaisser.execute();
        commandeRelever.execute();
        //this.roue.tourner(0.1);
        //this.moteur.tourner(0.1);
        //this.bras.tourner(0.1);
    }
    

}
