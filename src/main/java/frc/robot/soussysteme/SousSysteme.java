package frc.robot.soussysteme;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

// https://codedocs.revrobotics.com/java/com/revrobotics/package-summary.html
// https://docs.revrobotics.com/sparkmax/software-resources/migrating-ctre-to-rev
public class SousSysteme extends SubsystemBase{
	
    public double limiter(double vitesse) 
	{
		return Math.max(-1, Math.min(1, vitesse));
	}
	public void afficher()
	{
		//double test = 5;
        //SmartDashboard.putNumber("test",test );
	}
	public void liberer()
	{

	}
}
