// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.simulation.SimHooks;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.libraries.Pneumatics;
import team_5002.robot.libraries.Vision;
import team_5002.robot.libraries.controls;
import team_5002.robot.libraries.devices;
import team_5002.robot.libraries.digitalComs;
import team_5002.robot.systems.Drivetrain;
import team_5002.robot.systems.Lift;
import team_5002.robot.systems.Shooter;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  public static devices Devices = new devices("devices.json");
  public static controls Controls = new controls("controls.json");
  private Drivetrain drive = new Drivetrain();

  @Override
  public void robotInit() { CameraServer.startAutomaticCapture(); }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    drive.loop();

    Shooter.setShooterSpeed(Shooter.computeSpeed(45));
    Shooter.setBeltSpeed(100);

    Lift.armUp();
    Lift.armDown();
    Lift.lean();
    Lift.straighten();
    
  }
}