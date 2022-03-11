package team_5002.robot.libraries;

import edu.wpi.first.wpilibj.DigitalOutput;

public class digitalComs {
    DigitalOutput comPin1;
    DigitalOutput comPin2;
    DigitalOutput comPin3;
    DigitalOutput comPin4;

    public digitalComs(DigitalOutput comPin1, DigitalOutput comPin2, DigitalOutput comPin3, DigitalOutput comPin4){
        this.comPin1 = comPin1;
        this.comPin2 = comPin2;
        this.comPin3 = comPin3;
        this.comPin4 = comPin4;
    }

    public void setOutput(int hexValue) {
        comPin1.set((hexValue & 0b0001) == 0b0001);
        comPin2.set((hexValue & 0b0010) == 0b0010);
        comPin3.set((hexValue & 0b0100) == 0b0100);
        comPin4.set((hexValue & 0b1000) == 0b1000);
    }
}
