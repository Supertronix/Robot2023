package frc.robot.interaction;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commande.CommandeAjusterBras;
import frc.robot.commande.CommandeCalibrerBras;
import frc.robot.commande.CommandeFermerMachoire;
import frc.robot.commande.CommandeOuvrirMachoire;
import frc.robot.commande.CommandeDeplacerBras;
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

    protected BoutonDeclencheur boutonPressionMainDroite;
    protected BoutonDeclencheur boutonPressionMainGauche;

    @SuppressWarnings("deprecation") // la classe ouverte fonctionne aussi bien que la nouvelle classe proprietaire
    protected ManetteCompetition()
    {
        this.manette = new Joystick(MANETTE);

        this.boutonPressionMainGauche = new BoutonDeclencheur(manette, MAIN_GAUCHE_AXE);
        this.boutonPressionMainDroite = new BoutonDeclencheur(manette, MAIN_DROITE_AXE);
        //this.boutonPressionMainGauche.whenPressed(new CommandeAjusterBras(0.3));
        //this.boutonPressionMainDroite.whenPressed(new CommandeAjusterBras(-0.3));
        this.boutonPressionMainGauche.setCommande(new CommandeAjusterBras(-0.15));
        this.boutonPressionMainDroite.setCommande(new CommandeAjusterBras(0.15 ));


        this.boutonMaison = new JoystickButton(this.manette, BOUTON_Y);
        this.boutonDemarrer = new JoystickButton(this.manette, BOUTON_A);
        this.boutonArriere = new JoystickButton(this.manette, BOUTON_X);
        this.boutonPencheDevant = new JoystickButton(this.manette, BOUTON_B);
        this.boutonDevant = new JoystickButton(this.manette, BOUTON_DEMARRER);
        this.boutonPencheArriere = new JoystickButton(this.manette, BOUTON_RETOUR);
        this.boutonMainDroite = new JoystickButton(this.manette, BOUTON_MAIN_DROITE);
        this.boutonMainGauche = new JoystickButton(this.manette, BOUTON_MAIN_GAUCHE);

        Command commandeCalibration = new CommandeCalibrerBras();
        this.boutonMaison.whenPressed(commandeCalibration);

        Command commandeMilieu = new CommandeDeplacerBras(POSITION.POSTIION_MILIEU);
        this.boutonDemarrer.whenPressed(commandeMilieu);

        Command commandeArriere = new CommandeDeplacerBras(POSITION.POSITION_AVANT);
        this.boutonArriere.whenPressed(commandeArriere);

        Command commandePencheDevant = new CommandeDeplacerBras(POSITION.POSITION_ARRIERE);
        this.boutonPencheDevant.whenPressed(commandePencheDevant);

        Command commandeDevant = new CommandeDeplacerBras(POSITION.POSITION_PENCHE_AVANT);
        this.boutonDevant.whenPressed(commandeDevant);

        Command commandePencheArriere = new CommandeDeplacerBras(POSITION.POSITION_PENCHE_ARRIERE);
        this.boutonPencheArriere.whenPressed(commandePencheArriere);
        
        Command commandeOuvrirMachoire = new CommandeOuvrirMachoire();
        Command commandeFermerMachoire = new CommandeFermerMachoire();
        this.boutonMainDroite.whenPressed(commandeFermerMachoire);
        this.boutonMainGauche.whenPressed(commandeOuvrirMachoire);

    }
 
    public void executerActions()
    {
    	if(this.boutonPressionMainGauche.getAsBoolean())
        {
            this.boutonPressionMainGauche.declencher();;
        }
    	if(this.boutonPressionMainDroite.getAsBoolean())
        {
            this.boutonPressionMainDroite.declencher();
        }
    }
    
}
// this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
