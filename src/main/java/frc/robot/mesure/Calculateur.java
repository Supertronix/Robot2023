package frc.robot.mesure;

public class Calculateur {
	public static double clamp(double val, double min, double max) 
	{
	    return Math.max(min, Math.min(max, val));
	}

}