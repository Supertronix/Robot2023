package frc.robot.interaction;

import frc.robot.Materiel;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SelecteurPositionAutonome implements Materiel{

    protected int position = 0;
    
    private SelecteurPositionAutonome()
    {
    }
    
    static protected SelecteurPositionAutonome instance = null;
    static public SelecteurPositionAutonome getInstance()
    {
    	if(null == instance) instance = new SelecteurPositionAutonome();
    	return instance;
    }
    
    public int lireChoix()
    {
        //SmartDashboard.putBoolean("Mode autonome avec plateforme ?", false);
        boolean modePlateforme=SmartDashboard.getBoolean("Mode autonome avec plateforme ?", false);

        if(modePlateforme) position = 3;
        else position = 0;

        return position;
    }	
}
