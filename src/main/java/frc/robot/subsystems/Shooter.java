package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends  SubsystemBase{
    private double motorPower = 0;
    private WPI_TalonSRX shooterMotor1 = new WPI_TalonSRX(1);
    private WPI_TalonSRX shooterMotor2 = new WPI_TalonSRX(2);
    public Shooter(){
        shooterMotor1.setInverted(true);
        shooterMotor2.setInverted(true);
    }

    public void reverse(){
        shooterMotor1.set(-1);
        shooterMotor2.set(-1);
    }

    public void setSpeed(double rpm){
        double currentError = rpm - getSpeed();

        double proportional = currentError * Constants.shooter_kP;

        motorPower = motorPower + proportional;

        shooterMotor1.set(motorPower);
        shooterMotor2.set(motorPower);
    }

    public double getSpeed(){
        return -shooterMotor1.getSelectedSensorVelocity()/4096*60;
    }

    public void stop() {
        motorPower = 0;
        shooterMotor1.stopMotor();
        shooterMotor2.stopMotor();
    }

    public void setPower(double power) {
        shooterMotor1.set(power);
        shooterMotor2.set(power);
    }
}
