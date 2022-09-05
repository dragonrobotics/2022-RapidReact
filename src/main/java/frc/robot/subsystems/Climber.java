package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Climber extends  SubsystemBase{
    private static WPI_TalonSRX liftMotor = new WPI_TalonSRX(Constants.Motors.liftMotorId);
    private static DoubleSolenoid liftSolenoid = new DoubleSolenoid(20, PneumaticsModuleType.CTREPCM, Constants.Solenoids.LiftForward, Constants.Solenoids.LiftReverse);
    private static DigitalInput liftLimitTop = new DigitalInput(Constants.liftSwitchUpper);
    private static DigitalInput liftLimitBottom = new DigitalInput(Constants.liftSwitchLower);  
    public Climber(){}
    public void armsBack(){liftSolenoid.set(Value.kReverse);}

    public void armsForward(){liftSolenoid.set(Value.kForward);}

    public void liftDown(){if (liftLimitTop.get()) liftMotor.set(1);}

    public void liftUp(){if (liftLimitBottom.get()) liftMotor.set(-1);}

    public Value getArms(){return liftSolenoid.get();}

    public int getLiftState(){
        if (!liftLimitTop.get()) return 1;
        if (!liftLimitBottom.get()) return -1;
        return 0;
    }

    @Override
    public void periodic(){
        if(liftMotor.get() > 0 && !liftLimitTop.get()) liftMotor.set(0);
        if(liftMotor.get() < 0 && !liftLimitBottom.get()) liftMotor.set(0);
    }
    public void stopLift() {
        liftMotor.stopMotor();
    }
}
