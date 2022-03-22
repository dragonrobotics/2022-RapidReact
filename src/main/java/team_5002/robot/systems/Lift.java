package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.controls;


public class Lift {
    WPI_TalonSRX liftMotor;
    Pneumatics pneumatics;
    controls controls = Robot.Controls;
    boolean up = (boolean) controls.getInput("up");
    boolean down = (boolean) controls.getInput("down");
    boolean lean = (boolean) controls.getInput("lean");
    boolean straighten = (boolean) controls.getInput("straighten");
    DigitalInput limitSwitch1 = new DigitalInput(5);
    DigitalInput limitSwitch2 = new DigitalInput(6);
    public Lift(){
        liftMotor = (WPI_TalonSRX) Robot.Devices.getDevice("liftMotor");
        DoubleSolenoid[] solenoids = {(DoubleSolenoid) Robot.Devices.getDevice("solenoid1"),(DoubleSolenoid) Robot.Devices.getDevice("solenoid2")};
        this.pneumatics = new Pneumatics(solenoids, (Compressor) Robot.Devices.getDevice("compressor"));
    }

    public void armUp(){
        if(limitSwitch1.get() == false){
            this.liftMotor.set(.5);
        } else {
            armStop();
        }
    }

    public void armDown(){   
        if(limitSwitch2.get() == false){
            this.liftMotor.set(-.5);
        } else {
            armStop();
        }
    }

    public void armStop(){
        this.liftMotor.set(0);
        this.liftMotor.setNeutralMode(NeutralMode.Brake);
    }

    public void lean(){
            this.pneumatics.open();
    }

    public void straighten(){
            this.pneumatics.close();   
    }

    public void loop(){
        if(up){
            armUp();
        }
        if(down){
            armDown();
        }
        if(lean){
            lean();
        }
        if(straighten){
            straighten();
        }
        
    }
}