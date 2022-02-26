// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import edu.wpi.first.wpilibj.DigitalOutput;

/** Add your docs here. */
public class LEDs {
    public static void setOutput(int hexValue) {
        DigitalOutput[] leds = {
            new DigitalOutput(2),
            new DigitalOutput(4),
            new DigitalOutput(7),
            new DigitalOutput(8)
        };
        for (DigitalOutput led : leds) {
            int value = hexValue;
            value = value << 31;
            value = value >> 31;
            led.set(value == 1);
            hexValue = hexValue >> 1;
        }
    }
}
