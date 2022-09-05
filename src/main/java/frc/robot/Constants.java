// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class Motors{
        public static int liftMotorId = 6;
    }
    public static double aimDeadzone = 0.2;
    public static double camAngle = 13;
    public static double cameraHeight = 116;
    public static double goalHeight = 264;

    public static class Solenoids{

        public static final int LiftForward = 1;
        public static final int LiftReverse = 3;}

    public static int liftSwitchLower = 5;
    public static int liftSwitchUpper = 4;
    public static double distanceSpeedMultiplier = .34;
    public static double speedAddition = 160;
    public static double shooterSpeedDeadzone = 1;
    public static double triggerDeadzone = .1;
    public static double shooter_kP = .05;
}
