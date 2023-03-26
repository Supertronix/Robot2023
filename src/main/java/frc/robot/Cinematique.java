package frc.robot;

public interface Cinematique {
    public interface Roues
    {
        public double FACTEUR_ROUES = 1;
    }
    public interface Manette
    {
      public static final double SEUIL_ZERO = 0.08;//0.02;
      public static final double SEUIL_AXES_OPPOSES = 0.3;
      //static double BIAIS_AXE_GAUCHE_X = 0.06299212574958801;
      //static double BIAIS_AXE_GAUCHE_Y = -0.06299212574958801;
      static double BIAIS_AXE_GAUCHE_X = 0;
      static double BIAIS_AXE_GAUCHE_Y = -0;
    } 
    public interface Bras
    {
      public static final float POSITION_AVANT = 12.75f;
      public static final float POSTIION_MILIEU = 6.5f;
      public static final float POSITION_ARRIERE = 0.875f;
    }
    public interface Machoire
    {
    }
    
}
