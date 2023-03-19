package frc.robot.interaction;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

// https://docs.wpilib.org/en/2020/docs/software/old-commandbased/commands/running-commands-joystick-input.html
// https://docs.wpilib.org/en/stable/docs/software/basic-programming/joystick.html
public class ManetteCompetition extends Manette {

    // protected JoystickButton boutonControllerAttrapeur;

    protected ManetteCompetition()
    {
        this.manette = new Joystick(MANETTE);
        // this.boutonControllerAttrapeur = new JoystickButton(this.manette, BOUTON_DROIT);
        // this.boutonControllerAttrapeur.whenPressed(new CommandeRelacherEcoutille());
        // this.boutonControllerAttrapeur.whenReleased(new CommandeArmerAttrapeur());
    }
 
    public void executerActions()
    {
    	
    }

}