package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Bling.blingState;

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
    DoubleSolenoid[] intake = {(DoubleSolenoid) Robot.Devices.getDevice("intakeSolenoid")};
    Bling bling = Robot.bling;
    Pneumatics intakePneumatics = new Pneumatics(intake);
    public Shooter(){
        SmartDashboard.putNumber("Speed", 17000);
    }
    public double computeSpeed(double distance) {
        int speedPer = 30/950;
        return speedPer * distance;
      }
    private double getShooterSpeed(){
        return -motor1.getSelectedSensorVelocity()/1024;
    }
    private void run(double speed){
        motor1.set(speed);
        motor2.set(speed);
    }
    private void setBeltSpeed(double speed){
        this.belt2.set(speed);
        this.belt3.set(speed);
    }

    public void loop(){
        SmartDashboard.putNumber("CurrentMotorSpeed", getShooterSpeed());
        if((boolean) Robot.Controls.getInput("Shoot")){
            bling.setLEDs(blingState.shooting);
            if(Vision.canSeeTarget()){
                if((Math.abs(Vision.aim())<5)){
                    double distance = Vision.determineObjectDist();
                    double targetSpeed = computeSpeed(distance);
                    if(Math.abs(getShooterSpeed() - targetSpeed)<2){
                        run(1);
                        setBeltSpeed(.5);
                    }else{
                        if(getShooterSpeed() < targetSpeed){
                            run(motor1.get() + .1);
                        }else{
                            run(motor1.get() - .1);
                        }
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
            belt1.set(0);
        }
        if((boolean) Robot.Controls.getInput("intake")){
            bling.setLEDs(blingState.intake);
            intakePneumatics.open();
            belt1.set(1);
        }else{
            intakePneumatics.close();
            setBeltSpeed(0);
            belt1.set(0);
        }

    }
}
