package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj2.command.SubsystemBase;



public class IntakeSubsystem extends  SubsystemBase{
    private static WPI_TalonSRX intakeMotor = new WPI_TalonSRX(8);
    private static WPI_TalonSRX beltMotor = new WPI_TalonSRX(3);
    private static WPI_TalonSRX feedMotor = new WPI_TalonSRX(5);
    private static DoubleSolenoid intakePneumatics = new DoubleSolenoid(20, PneumaticsModuleType.CTREPCM, 0, 2);
    public IntakeSubsystem(){}
    public void startIntake() {intakeMotor.set(.75);}
    public void stopIntake() {intakeMotor.set(0);}
    public void reverseIntake() {intakeMotor.set(-1);}
    public void startBelt() {beltMotor.set(1);}
    public void stopBelt() {beltMotor.set(0);}
    public void reverseBelt() {beltMotor.set(-1);}
    public void startFeed() {feedMotor.set(1);}
    public void stopFeed() {feedMotor.set(0);}
    public void reverseFeed() {feedMotor.set(-1);}
    public void lowerIntake() {intakePneumatics.set(Value.kForward);}
    public void raiseIntake() {intakePneumatics.set(Value.kReverse);}
    public boolean intakeDown() {if (intakePneumatics.get() == Value.kForward) {return true;} return false;}

}