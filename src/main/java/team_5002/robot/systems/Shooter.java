package team_5002.robot.systems;

import java.lang.ModuleLayer.Controller;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.platform.DeviceType;

import team_5002.robot.Robot;
import team_5002.robot.libraries.Vision;
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
        double distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    private double getShooterSpeed(){
        return motor1.getSelectedSensorVelocity()/1024;
    }
    private void run(double speed){
        this.motor1.set(speed);
        this.motor2.set(speed);
    }
    private void setBeltSpeed(double speed){
        this.belt1.set(speed);
        this.belt2.set(speed);
        this.belt3.set(speed);
    }

    public void loop(){
        if((boolean) Robot.Controls.getInput("Shoot") || (boolean) Robot.Controls.getInput("Ait")){
            if((boolean) Robot.Controls.getInput("Shoot")){
                if(Vision.canSeeTarget()){
                    //if(Math.abs(Vision.aim()))
                }
            }
        }

    }
}
