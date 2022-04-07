package team_5002.robot.systems.AutonomousFunctions;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.Robot;
import team_5002.robot.systems.Autonomous;
import team_5002.robot.systems.Autonomous.StepState;

public class drive implements AutonomousStep {
    public Autonomous.StepState state = Autonomous.StepState.WAITING;
    long time; double speed;
    long startTime; long endTime;
    WPI_TalonSRX motorTopRight = (WPI_TalonSRX) Robot.Devices.getDevice("driveTopRight");
    WPI_TalonSRX motorTopLeft = (WPI_TalonSRX) Robot.Devices.getDevice("driveTopLeft");
    WPI_TalonSRX motorBottomRight = (WPI_TalonSRX) Robot.Devices.getDevice("driveBottomRight");
    WPI_TalonSRX motorBottomLeft = (WPI_TalonSRX) Robot.Devices.getDevice("driveBottomLeft");
    public drive(double time,double speed){
        this.time = (long) time;
        this.speed = -speed;
        
    }

    @Override
    public void setReady() {
        state = Autonomous.StepState.WORKING;
        startTime = System.currentTimeMillis();
        endTime = startTime + time * 1000;
        SmartDashboard.putNumber("starttime", startTime);
        SmartDashboard.putNumber("endtime", endTime);
        
    }

    @Override
    public StepState getState() {
        return state;
    }

    @Override
    public void loop() {
        long tim = System.currentTimeMillis();
        if(tim > endTime){
            this.state = Autonomous.StepState.FINISHED;
            motorTopLeft.set(0);
            motorTopRight.set(0);
            motorBottomLeft.set(0);
            motorBottomRight.set(0);
            return;
        }
        
        motorTopLeft.set(speed);
        motorTopRight.set(speed);
        motorBottomLeft.set(speed);
        motorBottomRight.set(speed);

    }
}
