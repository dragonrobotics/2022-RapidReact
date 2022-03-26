package team_5002.robot.systems;

import edu.wpi.first.wpilibj.DigitalOutput;
import team_5002.robot.libraries.digitalComs;

public class Bling {
    public int state = 0;
    digitalComs leds = new digitalComs(new DigitalOutput(0), new DigitalOutput(1), new DigitalOutput(2), new DigitalOutput(3));
    public enum blingState{
        disabled,
        driveBackwards,
        driveLeft,
        driveRight,
        turnRight,
        turnLeft,
        shooting,
        extendingArms,
        retractingArms,
        tiltArms,
        autonMode,
        teleOpMode,
        driveForward,
        beforeMatch,
        afterMatch,
        intake
    }
    public Bling(){}
    public void setLEDs(blingState blingState){
        state = blingState.ordinal();
        leds.setOutput(blingState.ordinal());
    }
    public void resetState(){
        state = 0;
    };
}
