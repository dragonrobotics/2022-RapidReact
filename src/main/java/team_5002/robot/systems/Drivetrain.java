package team_5002.robot.systems;
import team_5002.robot.libraries.controls;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Bling.blingState;
import team_5002.robot.Robot;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;


public class Drivetrain {
    public Drivetrain(){
    }

    public void loop(){
        devices Devices = Robot.Devices;
        WPI_TalonSRX motorTopRight = (WPI_TalonSRX) Devices.getDevice("driveTopRight");
        WPI_TalonSRX motorTopLeft = (WPI_TalonSRX) Devices.getDevice("driveTopLeft");
        WPI_TalonSRX motorBottomRight = (WPI_TalonSRX) Devices.getDevice("driveBottomRight");
        WPI_TalonSRX motorBottomLeft = (WPI_TalonSRX) Devices.getDevice("driveBottomLeft");
        double DeadZone = .2;
        Bling bling = Robot.bling;
        double accelerationRate = .1;
        controls Controls = Robot.Controls;
        double StraightAxis = (Double) Controls.getInput("StraightAxis");
        double StrafeAxis = (Double) Controls.getInput("StrafeAxis");
        double TurnAxis = (Double) Controls.getInput("TurnAxis");
        StraightAxis = Math.abs(StraightAxis) > DeadZone ? StraightAxis: 0;
        StrafeAxis = Math.abs(StrafeAxis) > DeadZone ? StrafeAxis: 0;
        TurnAxis = Math.abs(TurnAxis) > DeadZone ? TurnAxis: 0;

        StraightAxis = Math.pow(StraightAxis, 3);
        StrafeAxis = Math.pow(StrafeAxis, 3);
        TurnAxis = Math.pow(TurnAxis, 3);


        {
            if(Math.abs(StrafeAxis)>Math.abs(StraightAxis)){
                bling.setLEDs(StrafeAxis > 0 ? blingState.driveRight:blingState.driveLeft);
            }else if(Math.abs(TurnAxis)>Math.abs(StraightAxis)){
                bling.setLEDs(TurnAxis > 0 ? blingState.turnRight:blingState.turnLeft);
            }else {
                bling.setLEDs(StraightAxis > 0 ? blingState.driveForward:blingState.driveBackwards);
            }
        }
        
        if(StraightAxis != 0){
        TurnAxis = (TurnAxis-(TurnAxis*(Math.abs(StraightAxis)/2)));
        }
        
        double speedMult = ((((0 + 1) / 2)*-1)+1)*.75+.25;
        double topLeft     = speedMult*(StraightAxis - TurnAxis - StrafeAxis);
        double topRight    = speedMult*(StraightAxis + TurnAxis + StrafeAxis);
        double bottomLeft  = speedMult*(StraightAxis - TurnAxis + StrafeAxis);
        double bottomRight = speedMult*(StraightAxis + TurnAxis - StrafeAxis);

        double BiggestMotor = Math.max(Math.max(Math.abs(topLeft),Math.abs(topRight)),Math.max(Math.abs(bottomLeft),Math.abs(bottomRight)));

        if(BiggestMotor > 1){
        topLeft = topLeft / BiggestMotor;
        topRight = topRight / BiggestMotor;
        bottomLeft = bottomLeft / BiggestMotor;
        bottomRight = bottomRight / BiggestMotor;
        }

        motorTopLeft.set(((topLeft - motorTopLeft.get())*accelerationRate) + motorTopLeft.get());
        motorTopRight.set(((topRight - motorTopRight.get())*accelerationRate) + motorTopRight.get());
        motorBottomLeft.set(((bottomLeft - motorBottomLeft.get())*accelerationRate) + motorBottomLeft.get());
        motorBottomRight.set(((bottomRight - motorBottomRight.get())*accelerationRate) + motorBottomRight.get());
    }
}
