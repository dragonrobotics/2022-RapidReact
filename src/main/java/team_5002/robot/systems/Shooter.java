package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.devices;

public class Shooter {
    devices Devices = Robot.Devices;
    WPI_TalonSRX motorTopRight = (WPI_TalonSRX) Devices.getDevice("driveTopRight");
    WPI_TalonSRX motorTopLeft = (WPI_TalonSRX) Devices.getDevice("driveTopLeft");
    WPI_TalonSRX motorBottomRight = (WPI_TalonSRX) Devices.getDevice("driveBottomRight");
    WPI_TalonSRX motorBottomLeft = (WPI_TalonSRX) Devices.getDevice("driveBottomLeft");
    double shooterAngle = 0;
    WPI_TalonSRX motor1 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter1");
    WPI_TalonSRX motor2 = (WPI_TalonSRX) Robot.Devices.getDevice("shooter2");
    WPI_TalonSRX belt1 = (WPI_TalonSRX) Robot.Devices.getDevice("belt1");
    WPI_TalonSRX belt2 = (WPI_TalonSRX) Robot.Devices.getDevice("belt2");
    WPI_TalonSRX belt3 = (WPI_TalonSRX) Robot.Devices.getDevice("belt3");
    DoubleSolenoid intake1 = (DoubleSolenoid) Robot.Devices.getDevice("intakeSolenoid1");
    DoubleSolenoid intake2 = (DoubleSolenoid) Robot.Devices.getDevice("intakeSolenoid2");
    DoubleSolenoid[] intakeSolenoids = {intake1, intake2};
    Pneumatics intakePneumatics = new Pneumatics(intakeSolenoids);
    public Shooter(){}
    public double computeSpeed(double distance) {
        distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    private double getShooterSpeed(){
        return motor1.getSelectedSensorVelocity()/1024;
    }
    private void run(double speed){
        motor1.set(speed);
        motor2.set(speed);
    }
    private void setBeltSpeed(double speed){
        this.belt1.set(speed);
        this.belt2.set(speed);
        this.belt3.set(speed);
    }

    public void loop(){
        if((boolean) Robot.Controls.getInput("Shoot")){
            if(Vision.canSeeTarget()){
                if(Math.abs(Vision.aim())<5){
                    double distance = Vision.determineObjectDist();
                    double wheelSpeed = computeSpeed(distance);
                    if(getShooterSpeed() < wheelSpeed){
                        run(.1 + motorTopLeft.get());
                    }else{
                        run(1);
                        setBeltSpeed(.5);
                    }
                }else{
                    run(0);
                    setBeltSpeed(0);
                    motorBottomLeft.set(Vision.aim() > 0 ? -.2 : .2 );
                    motorBottomRight.set(Vision.aim() > 0 ? .2 : -.2 );
                    motorTopLeft.set(Vision.aim() > 0 ? -.2 : .2 );
                    motorTopRight.set(Vision.aim() > 0 ? .2 : -.2 );
                }
            }else {
                setBeltSpeed(0);
                run(0);
                motorBottomLeft.set(1);
                motorBottomRight.set(-1);
                motorTopLeft.set(1);
                motorTopRight.set(-1);
            }
        }else{
            run(0);
            setBeltSpeed(0);
        }
        if((boolean) Robot.Controls.getInput("intake")){
            intake1.set(DoubleSolenoid.Value.kForward);
            intake2.set(DoubleSolenoid.Value.kForward);
            setBeltSpeed(.5);
        }else{
            intake1.set(DoubleSolenoid.Value.kReverse);
            intake2.set(DoubleSolenoid.Value.kReverse);
            setBeltSpeed(0);
        }

    }
}
