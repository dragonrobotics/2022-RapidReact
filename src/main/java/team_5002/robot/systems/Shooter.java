package team_5002.robot.systems;

import java.lang.ModuleLayer.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import team_5002.robot.Robot;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.devices;
import team_5002.robot.libraries.controls;

public class Shooter {
    static devices Devices = Robot.Devices;
    controls Controls = Robot.Controls;
    static WPI_TalonSRX motor1 = (WPI_TalonSRX) Devices.getDevice("motor1");
    static WPI_TalonSRX belt1 = (WPI_TalonSRX) Devices.getDevice("belt1");
    static boolean trigger = (boolean) controls.getInput("trigger");
    static boolean button5 = (boolean) controls.getInput("button5");
    WPI_TalonSRX belt2 = (WPI_TalonSRX) Devices.getDevice("belt2");
    WPI_TalonSRX belt3 = (WPI_TalonSRX) Devices.getDevice("belt3");
    public Shooter(WPI_TalonSRX motor1, WPI_TalonSRX belt1, WPI_TalonSRX belt2, WPI_TalonSRX belt3){
        this.motor1 = motor1;
        this.belt1 = belt1;
        this.belt2 = belt2;
        this.belt3 = belt3;
    }
    public static double computeSpeed(double shooterAngle) {
        double distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    public double getShooterSpeed(){
        return 0;
    }
    public static void setShooterSpeed(double speed){
        if(trigger){
            motor1.set(speed);
        }
    }
    public void shooterPeriodic(){

    }

    public static void setBeltSpeed(double speed){
        if(button5){
            belt1.set(-speed);
        }
    }
}
