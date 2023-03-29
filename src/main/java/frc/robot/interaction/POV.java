package frc.robot.interaction;

import java.util.Map;
import static java.util.Map.entry; 

public class POV {

    public enum ANGLE {HAUT, HAUT_DROIT, DROIT, BAS_DROIT, BAS, BAS_GAUCHE, GAUCHE, HAUT_GAUCHE};
    
    Map<ANGLE, Integer> POSITION_NUMERIQUE = Map.ofEntries(
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
