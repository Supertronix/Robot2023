package frc.robot.test;

// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj/Joystick.html
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
// https://first.wpi.edu/wpilib/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/JoystickButton.html
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.Robot;
import frc.robot.interaction.Manette;

public class ManetteTestBras extends Manette {
    
    protected JoystickButton boutonTestBrasAbaisse;
    protected JoystickButton boutonTestBrasReleve;

    public ManetteTestBras()
    {
        System.out.println("new ManetteTestBras()");
        // https://www.chiefdelphi.com/t/lack-of-enough-joystickbutton-methods/428194/2
        // non deprecated version for this year is the CommandXboxController. 
        this.manette = new Joystick(MANETTE);

        //this.boutonTestBrasAbaisse = new JoystickButton(this.manette, BOUTON_A);
        //Command commandeTestBrasAbaisse = new CommandeAbaisserBras(Robot.getInstance().bras);
        //this.boutonTestBrasAbaisse.whenPressed(commandeTestBrasAbaisse);
        //this.boutonTestBrasAbaisse.whenReleased(new CommandeArreterCommande((Finissable)commandeTestBrasAbaisse)); 

        //this.boutonTestBrasReleve = new JoystickButton(this.manette, BOUTON_B);
        //Command commandeTestBrasReleve = new CommandeReleverBras(Robot.getInstance().bras);
        //this.boutonTestBrasReleve.whenPressed(commandeTestBrasReleve);
        //this.boutonTestBrasReleve.whenReleased(new CommandeArreterCommande((Finissable)commandeTestBrasReleve)); 
    }

    public boolean getDemandeAbaisse()
    {
        //System.out.println("Bouton A est " + this.manette.getRawButton(BOUTON_A));
        return this.manette.getRawButton(BOUTON_A);
    }
    public boolean getDemandeReleve()
    {
        return this.manette.getRawButton(BOUTON_B);
    }
}
