// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot.libraries;

import com.kauailabs.navx.frc.AHRS;
import edu.wpi.first.wpilibj.SPI;
/** Add your docs here. */
public class Gyro {
    static AHRS gyro = new AHRS(SPI.Port.kMXP);

    public static double getAngle() {
        double angle = gyro.getAngle();
        return angle;
    }

    public static void calibrate() {
        gyro.calibrate();
    }

    public static double[] getVelocity() {
        return new double[] {gyro.getVelocityX(), gyro.getVelocityY()};
    }
}
