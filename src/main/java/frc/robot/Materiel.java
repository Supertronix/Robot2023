package frc.robot;

public interface Materiel {

    public interface Roues
    {
        public int ROUE_AVANT_DROITE = 4; // ID 4 
        public int ROUE_ARRIERE_DROITE = 3; // ID 2 
        public int ROUE_AVANT_GAUCHE = 1; // ID 3 
        public int ROUE_ARRIERE_GAUCHE = 2; // ID 1     
    }
    public interface Manette
    {
      public static final int MANETTE = 0;
      public static final int BOUTON_A = 1;
      public static final int BOUTON_B = 2;
      public static final int BOUTON_X = 3;		
      public static final int BOUTON_Y = 4;
      public static final int BOUTON_GAUCHE = 5; 
      public static final int BOUTON_DROIT = 6;
      public static final int BOUTON_RETOUR = 7;
      public static final int BOUTON_DEMARRER = 8;
  
      public static final double SEUIL_ZERO = 0.02;
      public static final double SEUIL_AXES_OPPOSES = 0.3;
  
      public static final int MAIN_GAUCHE_AXE_Y = 1;
      public static final int MAIN_GAUCHE_AXE_X = 0;
      public static final int MAIN_DROITE_AXE_Y = 5;
      public static final int MAIN_DROITE_AXE_X = 4;    
      public static final int MAIN_GAUCHE_AXE_DECLENCHEUR = 2;
      public static final int MAIN_DROITE_AXE_DECLENCHEUR = 3;
    } 
    public interface Bras
    {
      public static final int MOTEUR_PRINCIPAL = 5;
      public static final int MOTEUR_SECONDAIRE_1 = 6;
      public static final int MOTEUR_SECONDAIRE_2 = 7;
    }
    
}
