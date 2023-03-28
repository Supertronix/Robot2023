package frc.robot;

public interface Materiel {

    public static int INTERRUPTEUR_DROIT = 11;
    public static int INTERRUPTEUR_GAUCHE = 12;

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
