// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Joystick;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX motorTopRight = new WPI_TalonSRX(3);
  private final WPI_TalonSRX motorTopLeft = new WPI_TalonSRX(1);
  private final WPI_TalonSRX motorBottomRight = new WPI_TalonSRX(4);
  private final WPI_TalonSRX motorBottomLeft = new WPI_TalonSRX(2);
  private final Joystick controller = new Joystick(0);
  private double topRightAccelleration = 0;
  private double topLeftAccelleration = 0;
  private double bottomRightAccelleration = 0;
  private double bottomLeftAccelleration = 0;

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
  }

  /**
   * This function is called every robot packet, no matter the mode. Use this for items like
   * diagnostics that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This autonomous (along with the chooser code above) shows how to select between different
   * autonomous modes using the dashboard. The sendable chooser code works with the Java
   * SmartDashboard. If you prefer the LabVIEW Dashboard, remove all of the chooser code and
   * uncomment the getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to the switch structure
   * below with additional strings. If using the SendableChooser make sure to add them to the
   * chooser code above as well.
   */
  @Override
  public void autonomousInit() {
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {
    
  }

  /** This function is called once when teleop is enabled. */
  @Override
  public void teleopInit() {}

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    double topLeft     = controller.getRawAxis(1) + controller.getRawAxis(2) - controller.getRawAxis(0);
    double topRight    = -1 * controller.getRawAxis(1) + controller.getRawAxis(2) - controller.getRawAxis(0);
    double bottomLeft  = controller.getRawAxis(1) + controller.getRawAxis(2) + controller.getRawAxis(0);
    double bottomRight = -1 *controller.getRawAxis(1) + controller.getRawAxis(2) + controller.getRawAxis(0);
    if(Math.abs(topRight) > Math.abs(motorTopRight.get())){
      if(topRight < 0){
        topRightAccelleration -= .2;
      }else {
        topRightAccelleration += .2;
      }
      motorTopRight.set(motorTopRight.get() + topRightAccelleration);
    }else {
      motorTopRight.set(topRight);
    }
    if(Math.abs(topLeft) > Math.abs(motorTopLeft.get())){
      if(topLeft < 0){
        topLeftAccelleration -= .2;
      }else {
        topLeftAccelleration += .2;
      }
      motorTopLeft.set(motorTopLeft.get() + topLeftAccelleration);
    }else {
      motorTopLeft.set(topLeft);
    }
    if(Math.abs(bottomLeft) > Math.abs(motorBottomLeft.get())){
      if(bottomLeft < 0){
        bottomLeftAccelleration -= .2;
      }else {
        bottomLeftAccelleration += .2;
      }
      motorBottomLeft.set(motorBottomLeft.get() + bottomLeftAccelleration);
    }else {
      motorBottomLeft.set(bottomLeft);
    }
    if(Math.abs(bottomRight) > Math.abs(motorBottomRight.get())){
      if(bottomRight < 0){
        bottomRightAccelleration -= .2;
      }else {
        bottomRightAccelleration += .2;
      }
      motorBottomRight.set(motorBottomRight.get() + bottomRightAccelleration);
    }else {
      motorBottomRight.set(bottomRight);
    }

  }

  /** This function is called once when the robot is disabled. */
  @Override
  public void disabledInit() {}

  /** This function is called periodically when disabled. */
  @Override
  public void disabledPeriodic() {}

  /** This function is called once when test mode is enabled. */
  @Override
  public void testInit() {}

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}
}