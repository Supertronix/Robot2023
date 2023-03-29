package frc.robot;
import java.util.Map;
import static java.util.Map.entry; 


public interface Materiel {

    public static int INTERRUPTEUR_DROIT = 11;
    public static int INTERRUPTEUR_GAUCHE = 12;

    public interface Affichage
    {
      public static int SIGNAL_ANIMATION_LED = 1; // et plusieurs autres pour les modes
    }

    public interface Roues
    {
        public int ROUE_AVANT_DROITE = 4; // ID 4 // roule avant
        public int ROUE_ARRIERE_DROITE = 3; // ID 3 // roule avant
        public int ROUE_AVANT_GAUCHE = 1; // ID 1  // roule arriere
        public int ROUE_ARRIERE_GAUCHE = 2; // ID 2 // roule arriere
    }
    public interface Manette
    {
      public static final int MANETTE = 0;

      public static final int BOUTON_A = 1;
      public static final int BOUTON_B = 2;
      public static final int BOUTON_X = 3;		
      public static final int BOUTON_Y = 4;
      public static final int BOUTON_MAIN_GAUCHE = 5; 
      public static final int BOUTON_MAIN_DROITE = 6;
      public static final int BOUTON_RETOUR = 7;
      public static final int BOUTON_DEMARRER = 8;
    
      public static final int BATON_GAUCHE_AXE_X = 0;
      public static final int BATON_GAUCHE_AXE_Y = 1;
      public static final int BATON_DROIT_AXE_X = 4;    
      public static final int BATON_DROIT_AXE_Y = 5;
      public static final int MAIN_GAUCHE_AXE = 2;
      public static final int MAIN_DROITE_AXE = 3;

      public enum ANGLE {HAUT, HAUT_DROIT, DROIT, BAS_DROIT, BAS, BAS_GAUCHE, GAUCHE, HAUT_GAUCHE};
        
      Map<ANGLE, Integer> ANGLE_POV = Map.ofEntries(
            entry(ANGLE.HAUT, 0),
            entry(ANGLE.HAUT_DROIT, 45),
            entry(ANGLE.DROIT, 90),
            entry(ANGLE.BAS_DROIT, 135),
            entry(ANGLE.BAS, 180),
            entry(ANGLE.BAS_GAUCHE, 225),
            entry(ANGLE.GAUCHE, 270),
            entry(ANGLE.HAUT_GAUCHE, 315)
            );
    
    }
    public interface Bras
    {
      public static final int MOTEUR_PRINCIPAL = 5; // tourne vers la batterie
      public static final int MOTEUR_SECONDAIRE = 6; // tourne vers la batterie

      public void abaisser();
    }
    public interface Machoire
    {
      public static final int MOTEUR = 7;
    }
    
}
