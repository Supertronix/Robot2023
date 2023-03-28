package frc.robot;

public interface Cinematique {

    public interface Roues
    {
      public double FACTEUR_ROUES = 0.7;
    }
    public interface Manette
    {
      enum Direction {DEVANT, DERRIERE, LATERAL_DROIT, LATERAL_GAUCHE, ROTATION_DROITE, ROTATION_GAUCHE};

      public static final double SEUIL_ZERO = 0.08;//0.02;
      public static final double SEUIL_AXES_OPPOSES = 0.3;
      //static double BIAIS_AXE_GAUCHE_X = 0.06299212574958801;
      //static double BIAIS_AXE_GAUCHE_Y = -0.06299212574958801;
      static double BIAIS_AXE_GAUCHE_X = 0;
      static double BIAIS_AXE_GAUCHE_Y = -0;
    } 
    public interface Bras
    {
      // B = 0 - A = 6.5 - X = 13
      // arriere pour scorer- centrer pour deplacement - devant pour ramasser
      public static final float POSITION_AVANT = 11.75f;
      public static final float POSITION_PENCHE_AVANT = 9f;
      public static final float POSTIION_MILIEU = 6.85f;
      public static final float POSITION_PENCHE_ARRIERE = 5f;
      public static final float POSITION_ARRIERE = 2f;

      //public static final double P = 0.05;
      //public static final double I = 0.000001;//0.0001;  // gros = bang bang
      public static final double P = 0.1;
      public static final double I = 0.000002;//0.0001;  // gros = bang bang
      public static final double D = 8;
      public static final double MAX = 0.3;

      public static int INTERVALLE_IMMOBILITE = 500;
      public static double TEMPS_MAXIMUM_CALIBRER = 2000;
      public static double DISTANCE_NULLE = 2; 
      // TODO peut-etre un ratio sur la distance
    
    }
    public interface Machoire
    {
      public double VITESSE = 2;
      public double VITESSE_OUVRIR = 5;
      public double VITESSE_FERMER = 3;
      public double TEMPS_MAXIMUM_OUVRIR = 500;
      public double TEMPS_MAXIMUM_FERMER = 500;
    }
    
}
/**
 * 
       public static final float POSITION_AVANT = 12.75f;
      public static final float POSTIION_MILIEU = 6.5f;
      public static final float POSITION_ARRIERE = 0.875f;
      public static final float POSITION_PENCHE_AVANT = 8.5f;
      public static final float POSITION_PENCHE_ARRIERE = 4.5f;

 */
