package team_5002.robot.systems;

import java.lang.ModuleLayer.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.platform.DeviceType;

import team_5002.robot.Robot;
import team_5002.robot.libraries.Vision;
<<<<<<< HEAD
import team_5002.robot.libraries.controls;

public class Shooter {
    double shooterAngle = 0;
    WPI_TalonSRX motor1 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter1");
    WPI_TalonSRX motor2 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter2");
    WPI_TalonSRX belt1 = (WPI_TalonSRX) Robot.Devices.getDevice("belt1");
    WPI_TalonSRX belt2 = (WPI_TalonSRX) Robot.Devices.getDevice("belt2");
    WPI_TalonSRX belt3 = (WPI_TalonSRX) Robot.Devices.getDevice("belt3");
    public Shooter(WPI_TalonSRX motor1, WPI_TalonSRX belt1, WPI_TalonSRX belt2, WPI_TalonSRX belt3){}
    public double computeSpeed() {
=======
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
>>>>>>> 97b8d0fc72e26b7de6925fab28d8a638414d6b53
        double distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    private double getShooterSpeed(){
        return motor1.getSelectedSensorVelocity()/1024;
    }
<<<<<<< HEAD
    private void run(double speed){
        this.motor1.set(speed);
        this.motor2.set(speed);
=======
    public static void setShooterSpeed(double speed){
        if(trigger){
            motor1.set(speed);
        }
>>>>>>> 97b8d0fc72e26b7de6925fab28d8a638414d6b53
    }
    private void setBeltSpeed(double speed){
        this.belt1.set(speed);
        this.belt2.set(speed);
        this.belt3.set(speed);
    }

<<<<<<< HEAD
    public void loop(){
        if((boolean) Robot.Controls.getInput("Shoot") || (boolean) Robot.Controls.getInput("Ait")){
            if((boolean) Robot.Controls.getInput("Shoot")){
                if(Vision.canSeeTarget()){
                    //if(Math.abs(Vision.aim()))
                }
            }
        }

=======
    public static void setBeltSpeed(double speed){
        if(button5){
            belt1.set(-speed);
        }
>>>>>>> 97b8d0fc72e26b7de6925fab28d8a638414d6b53
    }
}
