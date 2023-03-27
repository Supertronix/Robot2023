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
      public static final float POSTIION_MILIEU = 6.5f;
      public static final float POSITION_PENCHE_ARRIERE = 5f;
      public static final float POSITION_ARRIERE = 1.875f;
    }
    public interface Machoire
    {
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
