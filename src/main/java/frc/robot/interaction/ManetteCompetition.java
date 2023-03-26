package frc.robot.interaction;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commande.CommandeCalibrerBras;
import frc.robot.commande.CommandeReleverBras;
import frc.robot.Cinematique;
import frc.robot.Robot;


// https://docs.wpilib.org/en/2020/docs/software/old-commandbased/commands/running-commands-joystick-input.html
// https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html
public class ManetteCompetition extends Manette {

    // protected JoystickButton boutonControllerAttrapeur;
    protected JoystickButton boutonCalibration;
    protected JoystickButton boutonDevant;
    protected JoystickButton boutonMilieu;
    protected JoystickButton boutonArriere;

    protected ManetteCompetition()
    {
        this.manette = new Joystick(MANETTE);
        // this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        // this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        // this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());

        this.boutonCalibration = new JoystickButton(this.manette, BOUTON_Y);
        Command commandeCalibration = new CommandeCalibrerBras(Robot.getInstance().bras);
        this.boutonCalibration.whenPressed(commandeCalibration);

        
        // B = 0 - A = 6.5 - X = 13
        // arriere pour scorer- centrer pour deplacement - devant pour ramasser
        this.boutonDevant = new JoystickButton(this.manette, BOUTON_X);
        Command commandeDevant = new CommandeReleverBras(Robot.getInstance().bras, Cinematique.Bras.POSITION_AVANT);
        this.boutonDevant.whenPressed(commandeDevant);

        this.boutonMilieu = new JoystickButton(this.manette, BOUTON_A);
        Command commandeMilieu = new CommandeReleverBras(Robot.getInstance().bras, Cinematique.Bras.POSTIION_MILIEU);
        this.boutonMilieu.whenPressed(commandeMilieu);

        this.boutonArriere = new JoystickButton(this.manette, BOUTON_B);
        Command commandeArriere = new CommandeReleverBras(Robot.getInstance().bras, Cinematique.Bras.POSITION_ARRIERE);
        this.boutonArriere.whenPressed(commandeArriere);
    }
 
    public void executerActions()
    {
    	
    }


}