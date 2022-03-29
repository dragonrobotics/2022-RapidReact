package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.controls;
import team_5002.robot.systems.Bling.blingState;


public class Lift {
    WPI_TalonSRX liftMotor;
    Pneumatics pneumatics;
    controls controls = Robot.Controls;
    DigitalInput limitSwitch1 = new DigitalInput(4);
    DigitalInput limitSwitch2 = new DigitalInput(5);  
    Bling bling = Robot.bling;  
    public Lift(){
        liftMotor = (WPI_TalonSRX) Robot.Devices.getDevice("liftMotor");
        DoubleSolenoid[] solenoids = {(DoubleSolenoid) Robot.Devices.getDevice("liftSolenoid")};
        this.pneumatics = new Pneumatics(solenoids);
    }

    public void armUp(){
        if(limitSwitch1.get() == true){
            this.liftMotor.set(-1);
            bling.setLEDs(blingState.extendingArms);
        } else {
            armStop();
        }

    }

    public void armDown(){   
        if(limitSwitch2.get() == true){
            this.liftMotor.set(1);
            bling.setLEDs(blingState.retractingArms);
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
            bling.setLEDs(blingState.tiltArms);
    }

    public void straighten(){
            this.pneumatics.close();  
            bling.setLEDs(blingState.tiltArms); 
    }

    public void loop(){
        boolean up = (boolean) controls.getInput("up");
        boolean down = (boolean) controls.getInput("down");
        boolean lean = (boolean) controls.getInput("lean");
        boolean straighten = (boolean) controls.getInput("straighten");
        if(up){
            armUp();
        }else if(down){
            armDown();
        }else{
            armStop();
        }
        if(lean){
            lean();
        }else if(straighten){
            straighten();
        }
        SmartDashboard.putNumber("Motor", liftMotor.get());
        SmartDashboard.putBoolean("4", limitSwitch1.get());
        SmartDashboard.putBoolean("5", limitSwitch2.get());
        
    }
}