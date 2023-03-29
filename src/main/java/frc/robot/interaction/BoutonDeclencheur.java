package frc.robot.interaction;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.Button;

@SuppressWarnings("deprecation")
public class BoutonDeclencheur extends Button{
    
    protected boolean estDeclenche;
    protected int axe;
    protected Joystick manette;
    private double minimum = .1; // seuil minimal de desactivation
    private double maximum = .75; // seuil maximal de desactivation
    protected Command commande;

    public BoutonDeclencheur(Joystick manette, int axe)
    {
        this.axe = axe;
        this.manette = manette;
    }

    @Override
    public Button whenPressed(Command command)
    {
        this.commande = commande;
        super.whenPressed(this.commande);
        return this;
    }

    public void setCommande(Command commande)
    {
        this.commande = commande;
    }

    public Command getCommande()
    {
        return this.commande;
    }

    @Override
    public boolean getAsBoolean()
    {
        if (Math.abs(manette.getRawAxis(axe)) > maximum)
        {
            this.estDeclenche = true;
        }
        else if (Math.abs(manette.getRawAxis(axe)) < minimum)
        {
            this.estDeclenche = false;
            this.commandeEnCours = false;
        }
        return this.estDeclenche;
    }
    protected boolean commandeEnCours = false;
    public void declencher()
    {
        if(!this.commandeEnCours) this.commande.initialize();
        this.commandeEnCours = true;
    }

}




