package frc.robot.mesure;

public class DetecteurDuree{

	protected double tempsDebut; 
	protected double tempsActuel; 
	protected boolean estTropLong;
	protected double limite;
	
	public interface Immobilisable
	{
		public double getDistancePourImmobilite();
	}
	public DetecteurDuree(double limite)
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

	public boolean estTropLongue()
	{
		this.estTropLong = (this.tempsActuel - this.tempsDebut) > this.limite;
		if(this.estTropLong) System.out.println("Delais trop long !");
		return this.estTropLong;
	}

	public double getDuree()
	{
		return (this.tempsActuel - this.tempsDebut);
	}
}
