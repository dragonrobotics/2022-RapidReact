// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PneumaticHub;
import edu.wpi.first.wpilibj.PneumaticsControlModule;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private final WPI_TalonSRX motorTopRight = new WPI_TalonSRX(2);
  private final WPI_TalonSRX motorTopLeft = new WPI_TalonSRX(1);
  private final WPI_TalonSRX motorBottomRight = new WPI_TalonSRX(4);
  private final WPI_TalonSRX motorBottomLeft = new WPI_TalonSRX(3);
  private final WPI_TalonSRX motorShooter1 = new WPI_TalonSRX(7);
  private final WPI_TalonSRX motorShooter2 = new WPI_TalonSRX(8);
  private final Joystick controller = new Joystick(0);
  private final WPI_TalonSRX belt1 = new WPI_TalonSRX(9);
  private final WPI_TalonSRX belt2 = new WPI_TalonSRX(6);
  private final WPI_TalonSRX belt3 = new WPI_TalonSRX(5);
  private final Shooter Shooter = new Shooter(motorShooter1, belt1, belt2, belt3);
  private double accelerationRate = .1;
  private double DeadZone = 0;
  private Compressor compressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
  private DoubleSolenoid[] solenoids = new DoubleSolenoid[]{ new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0), new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3) };
  private Pneumatics armPneumatics = new Pneumatics(solenoids, compressor);
  private WPI_TalonSRX liftMotor = new WPI_TalonSRX(13);
  private Lift lift = new Lift(liftMotor, armPneumatics);


  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    DeadZone = SmartDashboard.getNumber("Dead Zone", .2);
    SmartDashboard.putNumber("Dead Zone", DeadZone);
    CameraServer.startAutomaticCapture();
    motorShooter2.follow(motorShooter1);
    belt3.setInverted(true);
    belt3.follow(belt1);
    belt2.follow(belt1);
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
  public void teleopInit() {
    SmartDashboard.putNumber("LEDS", 0);
    SmartDashboard.putNumber("Angle", 30);
    SmartDashboard.putNumber("Height", 110);
    SmartDashboard.putNumber("Speed Multiplier", 1);
    motorTopRight.setNeutralMode(NeutralMode.Coast);
    motorBottomLeft.setNeutralMode(NeutralMode.Coast);
    motorBottomRight.setNeutralMode(NeutralMode.Coast);
    motorTopLeft.setNeutralMode(NeutralMode.Coast);
    motorTopLeft.setInverted(true);
    motorBottomLeft.setInverted(true);
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {

    LEDs.








    SmartDashboard.putNumber("MotorSpeed", motorShooter1.getSelectedSensorVelocity()/1024);
    DeadZone = SmartDashboard.getNumber("Dead Zone", .2);
    double StraightAxis = controller.getRawAxis(1);
    double StrafeAxis = controller.getRawAxis(2);
    double TurnAxis = controller.getRawAxis(0);
    if (controller.getRawButton(2)) {
      StrafeAxis = controller.getRawAxis(0);
      TurnAxis = controller.getRawAxis(2);
    }
    StraightAxis = Math.abs(StraightAxis) > DeadZone ? StraightAxis: 0;
    StrafeAxis = Math.abs(StrafeAxis) > DeadZone ? StrafeAxis: 0;
    TurnAxis = Math.abs(TurnAxis) > DeadZone ? TurnAxis: 0;

    StraightAxis = Math.pow(StraightAxis, 3);
    StrafeAxis = Math.pow(StrafeAxis, 3);
    TurnAxis = Math.pow(TurnAxis, 3);

    
    if(StraightAxis != 0){
      TurnAxis = (TurnAxis/(Math.abs(StraightAxis)/2));
    }
    if(controller.getRawButton(3) || controller.getRawButton(4)){
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(3);
      boolean canSeeTarget = Vision.canSeeTarget();
    if(controller.getRawButton(3)){
      double diff = Vision.aim();
      double Offset = Math.abs(diff) > 2 ? diff < 0 ? -.15: .15 : 0;
      TurnAxis = canSeeTarget ? Offset: .5;
      StraightAxis = canSeeTarget ? StraightAxis: 0;
    }
    if(controller.getRawButton(4)){
      double TargetDist = 200;
      double dist = Vision.determineObjectDist();
      double Offset = -1*(Math.abs(dist - TargetDist) > 4 ? dist - TargetDist < 0 ? -.15: .15 : 0);

      TurnAxis = canSeeTarget ? TurnAxis: .5;
      StraightAxis = canSeeTarget ? Offset: 0;
    }}else{
      NetworkTableInstance.getDefault().getTable("limelight").getEntry("ledMode").setNumber(1);
    }
    
    double speedMult = ((((controller.getRawAxis(3) + 1) / 2)*-1)+1)*.75+.25;
    speedMult = SmartDashboard.getNumber("Speed Multiplier", 1);
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
    if(controller.getRawButton(5) || controller.getTrigger()){
      Shooter.setShooterSpeed(1 * speedMult);
    }else{
      Shooter.setShooterSpeed(0);
    }
    if(controller.getTrigger()){
      Shooter.setBeltSpeed(.75);
    }else if(controller.getRawButton(6)){
      Shooter.setShooterSpeed(0);
      Shooter.setBeltSpeed(.75);
    }else{
      Shooter.setBeltSpeed(0);
    }


    if(controller.getRawButton(9)){
      lift.lean();
    }else if(controller.getRawButton(10)){
      lift.straighten();
    }
    if(controller.getRawButton(11)){
      lift.armUp();
    }else if(controller.getRawButton(12)){
      lift.armDown();
    }else {
      lift.armStop();
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
