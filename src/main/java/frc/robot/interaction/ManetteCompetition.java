package frc.robot.interaction;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commande.CommandeCalibrerBras;
import frc.robot.commande.CommandeFermerMachoire;
import frc.robot.commande.CommandeOuvrirMachoire;
import frc.robot.commande.CommandeReleverBras;
import frc.robot.Cinematique;
import frc.robot.Cinematique.Bras.POSITION;

// https://docs.wpilib.org/en/2020/docs/software/old-commandbased/commands/running-commands-joystick-input.html
// https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html
public class ManetteCompetition extends Manette {

    // protected JoystickButton boutonControllerAttrapeur;
    protected JoystickButton boutonMaison;
    protected JoystickButton boutonDemarrer;
    protected JoystickButton boutonDevant;
    protected JoystickButton boutonArriere;
    protected JoystickButton boutonPencheDevant;
    protected JoystickButton boutonPencheArriere;
    protected JoystickButton boutonMainDroite;
    protected JoystickButton boutonMainGauche;

    @SuppressWarnings("deprecation") // la classe ouverte fonctionne aussi bien que la nouvelle classe proprietaire
    protected ManetteCompetition()
    {
        this.manette = new Joystick(MANETTE);

        this.boutonMaison = new JoystickButton(this.manette, BOUTON_Y);
        Command commandeCalibration = new CommandeCalibrerBras();
        this.boutonMaison.whenPressed(commandeCalibration);

        this.boutonDemarrer = new JoystickButton(this.manette, BOUTON_A);
        Command commandeMilieu = new CommandeReleverBras(POSITION.POSTIION_MILIEU);
        this.boutonDemarrer.whenPressed(commandeMilieu);

        this.boutonArriere = new JoystickButton(this.manette, BOUTON_X);
        Command commandeArriere = new CommandeReleverBras(POSITION.POSITION_AVANT);
        this.boutonArriere.whenPressed(commandeArriere);

        this.boutonPencheDevant = new JoystickButton(this.manette, BOUTON_B);
        Command commandePencheDevant = new CommandeReleverBras(POSITION.POSITION_ARRIERE);
        this.boutonPencheDevant.whenPressed(commandePencheDevant);

        this.boutonDevant = new JoystickButton(this.manette, BOUTON_DEMARRER);
        Command commandeDevant = new CommandeReleverBras(POSITION.POSITION_PENCHE_AVANT);
        this.boutonDevant.whenPressed(commandeDevant);

        this.boutonPencheArriere = new JoystickButton(this.manette, BOUTON_RETOUR);
        Command commandePencheArriere = new CommandeReleverBras(POSITION.POSITION_PENCHE_ARRIERE);
        this.boutonPencheArriere.whenPressed(commandePencheArriere);
        
        this.boutonMainDroite = new JoystickButton(this.manette, BOUTON_MAIN_DROITE);
        this.boutonMainGauche = new JoystickButton(this.manette, BOUTON_MAIN_GAUCHE);
        Command commandeOuvrirMachoire = new CommandeOuvrirMachoire();
        Command commandeFermerMachoire = new CommandeFermerMachoire();
        this.boutonMainDroite.whenPressed(commandeFermerMachoire);
        this.boutonMainGauche.whenPressed(commandeOuvrirMachoire);
    }
 
    public void executerActions()
    {
    	
    }
    
}
// this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
