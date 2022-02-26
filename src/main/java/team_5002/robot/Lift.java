package team_5002.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Lift {
    WPI_TalonSRX liftMotor;
    Pneumatics pneumatics;
    public Lift(WPI_TalonSRX liftMotor, Pneumatics pneumatics){
        this.liftMotor = liftMotor;
        this.pneumatics = pneumatics;
    }

    public void armUp(){
        liftMotor.set(.5);
    }

    public void armDown(){
        liftMotor.set(-.5);
    }

    public void armStop(){
        liftMotor.set(0);
        liftMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void lean(){
        this.pneumatics.open();
    }

    public void straighten(){
        this.pneumatics.close();
    }
}