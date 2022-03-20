package team_5002.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import team_5002.robot.libraries.controls;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Drivetrain;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static devices Devices = new devices("devices.json");
  public static controls Controls = new controls("controls.json");
  private Drivetrain drive = new Drivetrain();

  @Override
  public void robotInit() { CameraServer.startAutomaticCapture(); }

  @Override
  public void teleopPeriodic() {
    drive.loop();
  }
}