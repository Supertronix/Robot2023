package frc.robot.interaction;

import frc.robot.Materiel;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelecteurPositionAutonomeAvecInterupteur implements Materiel{

    protected DigitalInput interrupteurGauche = null;
    protected DigitalInput interrupteurDroit = null;

    protected int position = 0;
    
    private SelecteurPositionAutonome()
    {
        this.interrupteurGauche = new DigitalInput(INTERRUPTEUR_GAUCHE);
        this.interrupteurDroit = new DigitalInput(INTERRUPTEUR_DROIT);
    }
    
    static protected SelecteurPositionAutonome instance = null;
    static public SelecteurPositionAutonome getInstance()
    {
    	if(null == instance) instance = new SelecteurPositionAutonome();
    	return instance;
    }
    
    public int lireChoix()
    {
    	System.out.println("Interrupteur GAUCHE " + this.interrupteurGauche.get());
    	System.out.println("Interrupteur DROIT " + this.interrupteurDroit.get());
    	SmartDashboard.putBoolean("Switch GAUCHE", this.interrupteurGauche.get());
    	SmartDashboard.putBoolean("Switch DROITE", this.interrupteurDroit.get());
    	
    	if(this.interrupteurGauche.get() && this.interrupteurDroit.get()) position = 3;
    	else if(!this.interrupteurGauche.get() && !this.interrupteurDroit.get()) position = 0;
    	else if(this.interrupteurGauche.get()) position = 2;
    	else if(this.interrupteurDroit.get()) position = 1;
    	
    	System.out.println("Position trouvee " + position);
    	SmartDashboard.putNumber("Position depart autonome", position);
		return position;
    }	
}
