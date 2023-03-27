package frc.robot.mesure;

import frc.robot.Cinematique;


public class DetecteurImmobilite implements Cinematique.Bras{

	protected Immobilisable objet;
	protected double tempsDernier; 
	protected double tempsActuel; 
	protected double distanceActuelle;
	protected double distanceDernier;
	protected boolean estImmobile;
					
	public interface Immobilisable
	{
		public double getDistancePourImmobilite();
	}
	public DetecteurImmobilite(Immobilisable objet)
	{
		System.out.println("new DetecteurImmobilite()");
		this.objet = objet;
		this.initialiser();
	}
	
	public void initialiser()
	{
		this.tempsDernier = this.tempsActuel = System.currentTimeMillis();
		this.distanceDernier = this.distanceActuelle = objet.getDistancePourImmobilite();
		this.estImmobile = false;		
	}

	public void mesurer()
	{
		System.out.println("mesurer()");
		this.tempsActuel = System.currentTimeMillis();		
		
		if((this.tempsActuel - this.tempsDernier) > INTERVALLE_IMMOBILITE)
		{
			System.out.println("mesurer() if");
			// verifier
			this.distanceActuelle = objet.getDistancePourImmobilite();
			this.estImmobile = (Math.abs(this.distanceActuelle - this.distanceDernier) < DISTANCE_NULLE);
			
			// reinitialiser
			this.tempsDernier = this.tempsActuel; 
			this.distanceDernier = this.distanceActuelle; 
		}		
	}
		
	public boolean estImmobile()
	{
		return this.estImmobile;
	}
}
