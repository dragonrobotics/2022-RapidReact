package team_5002.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

public class Shooter {
    WPI_TalonSRX motor1;
    WPI_TalonSRX belt1;
    WPI_TalonSRX belt2;
    WPI_TalonSRX belt3;
    public Shooter(WPI_TalonSRX motor1, WPI_TalonSRX belt1, WPI_TalonSRX belt2, WPI_TalonSRX belt3){
        this.motor1 = motor1;
        this.belt1 = belt1;
        this.belt2 = belt2;
        this.belt3 = belt3;
    }
    public double computeSpeed(double shooterAngle) {
        double distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    public double getShooterSpeed(){
        return 0;
    }
    public void setShooterSpeed(double speed){
        this.motor1.set(speed);
    }
    public void shooterPeriodic(){

    }

    public void setBeltSpeed(double speed){
        this.belt1.set(-speed);
    }
}
