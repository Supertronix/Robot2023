package frc.robot.test;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.interaction.Manette;

public class ManetteTestBras extends Manette {
    
    protected JoystickButton boutonTestBrasAbaisse;

    public ManetteTestBras()
    {
        // https://www.chiefdelphi.com/t/lack-of-enough-joystickbutton-methods/428194/2
        // non deprecated version for this year is the CommandXboxController. 
        this.manette = new Joystick(MANETTE);
        this.boutonTestBrasAbaisse = new JoystickButton(this.manette, BOUTON_A);
        this.boutonTestBrasAbaisse.whenPressed(new CommandeAbaisserBras(Robot.bras));
        //this.boutonTestBrasAvant.whenReleased(new CommandeArmerAttrapeur());        
    }
}
