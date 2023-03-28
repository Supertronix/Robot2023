package frc.robot.mesure;

public class DetecteurDelais{

	protected double tempsDebut; 
	protected double tempsActuel; 
	protected boolean estTropLong;
	protected double limite;
	protected boolean estImmobile;
					
	public interface Immobilisable
	{
		public double getDistancePourImmobilite();
	}
	public DetecteurDelais(double limite)
	{
		System.out.println("new DetecteurDelais()");
		this.limite = limite;
		this.initialiser();
	}
	
	public void initialiser()
	{
		this.tempsDebut = System.currentTimeMillis();
		this.estTropLong = false;
	}

	public void mesurer()
	{
		System.out.println("mesurer()");
		this.tempsActuel = System.currentTimeMillis();				
	}

	public boolean estTropLong()
	{
		this.estTropLong = (this.tempsActuel- this.tempsDebut) > this.limite;
		return this.estTropLong;
	}
		
	public boolean estImmobile()
	{
		return this.estImmobile;
	}
}
