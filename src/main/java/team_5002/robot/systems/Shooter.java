package team_5002.robot.systems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.Robot;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.devices;

public class Shooter {

    double kP = .1;
    double kI = .1;
    double kD = .1;
    double motorPower = 0;
    double prevError = 0;
    double totalError = 0;

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
    DoubleSolenoid[] intake = {(DoubleSolenoid) Robot.Devices.getDevice("intakeSolenoid")};
    public Pneumatics intakePneumatics = new Pneumatics(intake);
    boolean intakeUp = true;
    boolean pressed = false;
    
    public Shooter(){
        SmartDashboard.putNumber("TargetSpeed", 0);
    }
    public double computeSpeed(double distance) {
        SmartDashboard.putNumber("TargetSpeed", (distance*.38)+68);
        return (distance*.34)+160;
      }
    private double getShooterSpeed(){
        SmartDashboard.putNumber("SensorVel", motor1.getSelectedSensorVelocity());
        return -motor1.getSelectedSensorVelocity()/4096*60;
    }
    private void run(double speed){
        motor1.set(speed);
        motor2.set(speed);
    }
    private void setFeedSpeed(double speed){
        this.belt2.set(speed);
        this.belt3.set(speed);
    }

    public void loop(){
        SmartDashboard.putNumber("CurrentMotorSpeed", motor1.get());
        if((boolean) Robot.Controls.getInput("Shoot")){
            Joystick controller = (Joystick) Robot.Controls.getInput("controller");
            controller.setRumble(RumbleType.kLeftRumble, 1);
            controller.setRumble(RumbleType.kRightRumble, 1);
            if(Vision.canSeeTarget()){
                double distance = Vision.determineObjectDist();
                if(distance > 350 && distance < 600){
                    double targetSpeed = computeSpeed(distance);
                    boolean canShoot = Math.abs(getShooterSpeed() - targetSpeed) < .5;
                    
                    double currentSpeed = getShooterSpeed();
                    double currPrevError = prevError;
                    double currentError = targetSpeed - currentSpeed;
                    prevError = currentError;

                    double proportional = currentError * kP;

                    motorPower = motorPower + proportional;

                    run(motorPower);

                    if((Math.abs(Vision.aim())<5)){
                        if(canShoot){
                            setFeedSpeed(.5);
                        }
                    }else{
                        setFeedSpeed(0);
                        motorBottomLeft.set(Vision.aim() > 0 ? -.2 : .2 );
                        motorBottomRight.set(Vision.aim() > 0 ? .2 : -.2 );
                        motorTopLeft.set(Vision.aim() > 0 ? -.2 : .2 );
                        motorTopRight.set(Vision.aim() > 0 ? .2 : -.2 );
                    }
                }
                
            }else {
                setFeedSpeed(0);
                run(0);
                motorBottomLeft.set(.3);
                motorBottomRight.set(-.3);
                motorTopLeft.set(.3);
                motorTopRight.set(-.3);
            }
        }else{
            Joystick controller = (Joystick) Robot.Controls.getInput("controller");
            controller.setRumble(RumbleType.kLeftRumble, 0);
            controller.setRumble(RumbleType.kRightRumble, 0);
            run(0);
            setFeedSpeed(0);
            belt1.set(0);
        }
        if((boolean) Robot.Controls.getInput("intake")){
            belt1.set(1);
        }else if((boolean) Robot.Controls.getInput("spitter")){
            run(-.5);
            belt1.set(-.5);
            belt2.set(-.5);
            belt3.set(-.5);
        }else if((boolean) Robot.Controls.getInput("shortShot")){
            run(.25);
            belt1.set(.25);
            belt2.set(.25);
            belt3.set(.25);
        }else{
            belt1.set(0);
        }
       {
            if((boolean) Robot.Controls.getInput("intakeToggle") && pressed == false){
                pressed = true;
                if(intakeUp){
                    intakePneumatics.open();
                    intakeUp = false;
                }else{
                    intakePneumatics.close();
                    intakeUp = true;
                }
                SmartDashboard.putBoolean("intakeUp", intakeUp);
           }else{
               if(!(boolean) Robot.Controls.getInput("intakeToggle")){
               pressed = false;
               }
           }
    }

    }
}
