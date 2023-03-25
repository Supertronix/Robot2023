package frc.robot.test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.soussysteme.Bras;
import frc.robot.soussysteme.RouesMecanumSynchro;

public class Testeur {

    double test = 5;
    //protected Roue roue;
    //protected Moteur moteur;
    //protected Bras bras;
    protected Manette manette;
    //protected ManetteTestBras manette;
    //protected CommandBase commandeAbaisser;
    //protected CommandBase commandeRelever;
    //protected RoueAvecControle roueSousControle;
    //protected RouesMecanumSynchro rouesMecanumSynchro;
    public Testeur()
    {
        SmartDashboard.putNumber("test", test); 
        // TESTS de roue
        //this.roue = new Roue(2);        
        //this.moteur = new Moteur(5);
        
        // tests de moteurs de bras
        //this.bras = new Bras();

        // tests de limite switch
        //double vitesse = 0.05;
        this.manette = Manette.getInstance();
        //commandeAbaisser = new CommandeAbaisserBrasAvecManette(Robot.getInstance().bras, manette, vitesse);
        //commandeRelever = new CommandeReleverBrasAvecManette(Robot.getInstance().bras, manette, vitesse);

        // TESTS de pid
        //this.roueSousControle = new RoueAvecControle();
        //this.rouesMecanumSynchro = new RouesMecanumSynchro();
    }

    public void executer()
    {
        // TESTS de roue
        //this.roue.tourner(0.1);
        //this.moteur.tourner(0.1);
        //this.bras.tourner(0.1);

        // tests de moteurs de bras
        //commandeAbaisser.execute();
        //commandeRelever.execute();
    }

    public void lancer()
    {
        // TESTS de pid
        // this.rouesMecanumSynchro.avancer();
    }
    

}
