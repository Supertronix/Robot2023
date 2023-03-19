package frc.robot.soussysteme;

public class SousSysteme {
	
    public double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}
	public void afficher()
	{
		//double test = 5;
        //SmartDashboard.putNumber("test",test );
	}

}
