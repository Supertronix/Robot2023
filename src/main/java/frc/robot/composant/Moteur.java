import com.revrobotics.CANSparkMax;

public class Moteur extends CANSparkMax{)
    public Moteur(int id)
    {
        // CANSparkMax​(int deviceId, CANSparkMaxLowLevel.MotorType type)        
        super(id, MotorType.kBrushless);
        this.restoreFactoryDefaults();
        this.setIdleMode(IdleMode.kBrake);
    }
}
