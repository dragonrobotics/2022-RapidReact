package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Solenoid;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.controls;


public class Lift {
    static WPI_TalonSRX liftMotor;
    static Pneumatics pneumatics;
    static controls controls = Robot.Controls;
    static boolean button7 = (boolean) controls.getInput("button7");
    static boolean button8 = (boolean) controls.getInput("button8");
    static boolean button9 = (boolean) controls.getInput("button9");
    static boolean button10 = (boolean) controls.getInput("button10");
    public Lift(){
        liftMotor = (WPI_TalonSRX) Robot.Devices.getDevice("liftMotor");
        DoubleSolenoid[] solenoids = {(DoubleSolenoid) Robot.Devices.getDevice("solenoid1"),(DoubleSolenoid) Robot.Devices.getDevice("solenoid2")};
        this.pneumatics = new Pneumatics(solenoids, (Compressor) Robot.Devices.getDevice("compressor"));
    }

    public static void armUp(){
        if(button7){
            liftMotor.set(.5);
        }
    }

    public static void armDown(){
        if(button8){
            liftMotor.set(-.5); 
        }
    }

    public static void armStop(){
        liftMotor.set(0);
        liftMotor.setNeutralMode(NeutralMode.Brake);
    }

    public static void lean(){
        if(button9){
            pneumatics.open();
        }
    }

    public static void straighten(){
        if(button10){
            pneumatics.close();   
        }
    }

    public void loop(){
        
    }
}