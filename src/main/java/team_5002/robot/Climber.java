// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;

/** Add your docs here. */
public class Climber {
    private final static WPI_TalonSRX verticalMotor = new WPI_TalonSRX(13);
    private final static WPI_TalonSRX rotateMotor = new WPI_TalonSRX(14);
    AnalogPotentiometer pot = new AnalogPotentiometer(0, 180, 30);
    DigitalInput limitSwitch = new DigitalInput(0);

    public static void armUp() {
        verticalMotor.set(0.25);
    }
    public static void armDown() {
        verticalMotor.set(-0.25);
    }
    public static void armForward() {
        rotateMotor.set(0.25);
    }
    public static void armBack() {
        rotateMotor.set(-0.25);
    }


}
