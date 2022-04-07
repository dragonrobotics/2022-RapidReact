package team_5002.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.libraries.controls;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Autonomous;
import team_5002.robot.systems.Drivetrain;
import team_5002.robot.systems.Lift;
import team_5002.robot.systems.Shooter;
import team_5002.robot.systems.AutonomousFunctions.Shoot;
import team_5002.robot.systems.AutonomousFunctions.drive;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static devices Devices = new devices("devices.json");
  public static controls Controls = new controls("controls.json");
  private Drivetrain drive = new Drivetrain();
  private Shooter shooter = new Shooter();
  private Lift lift = new Lift();
  private Autonomous auton = new Autonomous();
  WPI_TalonSRX motorTopRight = (WPI_TalonSRX) Devices.getDevice("driveTopRight");
  WPI_TalonSRX motorTopLeft = (WPI_TalonSRX) Devices.getDevice("driveTopLeft");
  WPI_TalonSRX motorBottomRight = (WPI_TalonSRX) Devices.getDevice("driveBottomRight");
  WPI_TalonSRX motorBottomLeft = (WPI_TalonSRX) Devices.getDevice("driveBottomLeft");
  DriverStation station;
  @Override
  public void robotInit() { CameraServer.startAutomaticCapture(); 
      lift.straighten();
      shooter.intakePneumatics.close();
  }

  @Override
  public void robotPeriodic(){
    SmartDashboard.putNumber("Match Time", station.getMatchTime());
  }

  @Override
  public void autonomousInit(){
    auton
    .addStep(new drive(2.5, -.4))
    .addStep(new Shoot(4))
    .init();
  }

  @Override
  public void autonomousPeriodic(){
    auton.loop();
  }

  @Override
  public void autonomousExit(){

  }

  @Override
  public void teleopPeriodic() {
    drive.loop();
    shooter.loop();
    lift.loop();
  }
}