package team_5002.robot.systems.AutonomousFunctions;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import team_5002.robot.Robot;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Autonomous;
import team_5002.robot.systems.Autonomous.StepState;

public class Shoot implements AutonomousStep {
    public Autonomous.StepState state = Autonomous.StepState.WAITING;
    devices Devices = Robot.Devices;
    WPI_TalonSRX motorTopRight = (WPI_TalonSRX) Devices.getDevice("driveTopRight");
    WPI_TalonSRX motorTopLeft = (WPI_TalonSRX) Devices.getDevice("driveTopLeft");
    WPI_TalonSRX motorBottomRight = (WPI_TalonSRX) Devices.getDevice("driveBottomRight");
    WPI_TalonSRX motorBottomLeft = (WPI_TalonSRX) Devices.getDevice("driveBottomLeft");
    WPI_TalonSRX motor1 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter1");
    WPI_TalonSRX motor2 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter2");
    WPI_TalonSRX belt1 = (WPI_TalonSRX) Robot.Devices.getDevice("belt1");
    WPI_TalonSRX belt2 = (WPI_TalonSRX) Robot.Devices.getDevice("belt2");
    WPI_TalonSRX belt3 = (WPI_TalonSRX) Robot.Devices.getDevice("belt3");
    double shootLength;
    public Shoot(double length){this.shootLength = length;}

    @Override
    public void setReady() {
        state = Autonomous.StepState.WORKING;
        
    }

    @Override
    public StepState getState() {
        return state;
    }

    public double computeSpeed(double distance) {
        return (distance*.34)+160;  
      }
    private double getShooterSpeed(){
        return -motor1.getSelectedSensorVelocity()/4096*60;
    }
    private void run(double speed){
        motor1.set(speed);
        motor2.set(speed);
    }
    private void setBeltSpeed(double speed){
        this.belt2.set(speed);
        this.belt3.set(speed);
    }
    boolean hasShot = false;
    long endTime;
    long shotTime;
    @Override
    public void loop() {
        if(hasShot){
            if(System.currentTimeMillis() > endTime){
                state = Autonomous.StepState.FINISHED;
                setBeltSpeed(0);
                run(0);
                return;
            }
            return;
        }
            if(Vision.canSeeTarget()){
                double distance = Vision.determineObjectDist();
                double targetSpeed = computeSpeed(distance);
                if(Math.abs(getShooterSpeed() - targetSpeed)<30){
                    setBeltSpeed(1);
                    hasShot = true;
                    shotTime = System.currentTimeMillis();
                    endTime = shotTime + ((long) shootLength)*1000;
                }else{
                    if(targetSpeed < getShooterSpeed()){
                        run(motor1.get() - .005);
                    }else{
                        run(motor1.get() + .005);
                    }
                }
            }
        
    }
    
}
