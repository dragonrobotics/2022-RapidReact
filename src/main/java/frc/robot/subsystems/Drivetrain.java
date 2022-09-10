package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Drivetrain extends  SubsystemBase{
    private static WPI_TalonSRX motorTopLeft = new WPI_TalonSRX(9);
    private static WPI_TalonSRX motorTopRight = new WPI_TalonSRX(7);
    private static WPI_TalonSRX motorBottomLeft = new WPI_TalonSRX(4);
    private static WPI_TalonSRX motorBottomRight = new WPI_TalonSRX(10);

    public Drivetrain(){
        motorTopLeft.setInverted(true);
        motorBottomLeft.setInverted(true);
    }
    
    public void drive(double ySpeed, double xSpeed, double turnSpeed){
        double topLeft     = (ySpeed - turnSpeed - xSpeed);
        double topRight    = (ySpeed + turnSpeed + xSpeed);
        double bottomLeft  = (ySpeed - turnSpeed + xSpeed);
        double bottomRight = (ySpeed + turnSpeed - xSpeed);
        
        double BiggestMotor = Math.max(Math.max(Math.abs(topLeft),Math.abs(topRight)),Math.max(Math.abs(bottomLeft),Math.abs(bottomRight)));

        if(BiggestMotor > 1){
        topLeft = topLeft / BiggestMotor;
        topRight = topRight / BiggestMotor;
        bottomLeft = bottomLeft / BiggestMotor;
        bottomRight = bottomRight / BiggestMotor;
        }

        motorTopLeft.set(topLeft);
        motorTopRight.set(topRight);
        motorBottomLeft.set(bottomLeft);
        motorBottomRight.set(bottomRight);
    }

    public void stop(){
        motorTopLeft.stopMotor();
        motorTopRight.stopMotor();
        motorBottomLeft.stopMotor();
        motorBottomRight.stopMotor();
    }

}
