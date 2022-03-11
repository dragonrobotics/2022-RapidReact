package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;

public class Lift {
    WPI_TalonSRX liftMotor;
    Pneumatics pneumatics;
    public Lift(){
        this.liftMotor = (WPI_TalonSRX) Robot.Devices.getDevice("liftMotor");
        DoubleSolenoid[] solenoids = {(DoubleSolenoid) Robot.Devices.getDevice("solenoid1"),(DoubleSolenoid) Robot.Devices.getDevice("solenoid2")};
        this.pneumatics = new Pneumatics(solenoids, (Compressor) Robot.Devices.getDevice("compressor"));
    }

    private void armUp(){
        liftMotor.set(.5);
    }

    private void armDown(){
        liftMotor.set(-.5);
    }

    private void armStop(){
        liftMotor.set(0);
        liftMotor.setNeutralMode(NeutralMode.Brake);
    }

    private void lean(){
        this.pneumatics.open();
    }

    private void straighten(){
        this.pneumatics.close();
    }

    public void loop(){
        
    }
}