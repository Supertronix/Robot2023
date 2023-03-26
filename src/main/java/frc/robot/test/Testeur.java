package frc.robot.test;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;
import frc.robot.interaction.Manette;
import frc.robot.soussysteme.Bras;
import frc.robot.soussysteme.RouesMecanumSynchro;
import frc.robot.commande.CommandeOuvrirMachoire;
import frc.robot.commande.CommandeFermerMachoire;

public class Testeur {

    double test = 5;
    //protected Roue roue;
    //protected Moteur moteur;
    //protected Bras bras;
    //protected Manette manette;
    //protected ManetteTestBras manette;
    //protected CommandBase commandeAbaisser;
    //protected CommandBase commandeRelever;
    //protected RoueAvecControle roueSousControle;
    //protected RouesMecanumSynchro rouesMecanumSynchro;
    protected CommandeOuvrirMachoire commandeOuvrirMachoire;
    protected CommandeFermerMachoire commandeFermerMachoire;
    public Testeur()
    {
        System.out.println("new Testeur()");
        //SmartDashboard.putNumber("test", test); 
        
        // TESTS de roue
        //this.roue = new Roue(2);        
        //this.moteur = new Moteur(5);
        
        // tests de moteurs de bras
        //this.bras = new Bras();

        // tests de limite switch
        //double vitesse = 0.05;
        //this.manette = Manette.getInstance();
        //commandeAbaisser = new CommandeAbaisserBrasAvecManette(Robot.getInstance().bras, manette, vitesse);
        //commandeRelever = new CommandeReleverBrasAvecManette(Robot.getInstance().bras, manette, vitesse);

        // TESTS de pid
        //this.roueSousControle = new RoueAvecControle();
        //this.rouesMecanumSynchro = new RouesMecanumSynchro();
        //this.commandeOuvrirMachoire = new CommandeOuvrirMachoire();
        //this.commandeOuvrirMachoire = new CommandeOuvrirMachoire();        
        this.commandeFermerMachoire = new CommandeFermerMachoire();        
    }

    public void initialiser(){
        //this.commandeOuvrirMachoire.initialize();
        this.commandeFermerMachoire.initialize();
    }

    public void lancer()
    {
        // TESTS de pid
        // this.rouesMecanumSynchro.avancer();
        //commandeOuvrirMachoire.schedule();
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

        // testt de machoire
        //if(!commandeOuvrirMachoire.isFinished()) commandeOuvrirMachoire.execute();
        if(!commandeFermerMachoire.isFinished()) commandeFermerMachoire.execute();

    }
    

}
