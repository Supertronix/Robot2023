package frc.robot;

import java.util.Map;
import static java.util.Map.entry; 

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
      public static final float POSITION_AVANT = 11.5f;
      public static final float POSITION_PENCHE_AVANT = 9f;
      public static final float POSTIION_MILIEU = 6.6f;
      public static final float POSITION_PENCHE_ARRIERE = 4.5f;
      public static final float POSITION_ARRIERE = 1.5f;
      public enum POSITION {POSITION_AVANT, POSITION_PENCHE_AVANT, POSTIION_MILIEU, POSITION_PENCHE_ARRIERE, POSITION_ARRIERE, AJUSTEE, INCONNUE};
      public static final double POSITION_INCONNUE = -1;
      Map<POSITION, Float> POSITION_NUMERIQUE = Map.ofEntries(
        entry(POSITION.POSITION_AVANT, POSITION_AVANT),
        entry(POSITION.POSITION_PENCHE_AVANT, POSITION_PENCHE_AVANT),
        entry(POSITION.POSTIION_MILIEU, POSTIION_MILIEU),
        entry(POSITION.POSITION_PENCHE_ARRIERE, POSITION_PENCHE_ARRIERE),
        entry(POSITION.POSITION_ARRIERE, POSITION_ARRIERE));

      //public static final double P = 0.05;
      //public static final double I = 0.000001;//0.0001;  // gros = bang bang
      public static final double P = 0.1;
      public static final double I = 0.000002;//0.0001;  // gros = bang bang
      public static final double D = 8;
      public static final double MAX = 0.3;

      public static int INTERVALLE_IMMOBILITE = 250;
      public static double TEMPS_MAXIMUM_CALIBRER = 800;
      public static double TEMPS_MAXIMUM_CALIBRER_AVANT = 1600;
      public static double DISTANCE_NULLE = 2; 
      // TODO peut-etre un ratio sur la distance


    
    }
    public interface Machoire
    {
      public double VITESSE = 1;
      public double VITESSE_OUVRIR = 0.5;
      public double VITESSE_FERMER = 0.5;
      public double TEMPS_MAXIMUM_OUVRIR = 1000; // ms
      public double TEMPS_MAXIMUM_FERMER = 1000; // ms
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
