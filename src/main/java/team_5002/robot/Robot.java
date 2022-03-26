package team_5002.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import team_5002.robot.libraries.controls;
import team_5002.robot.libraries.devices;
import team_5002.robot.systems.Bling;
import team_5002.robot.systems.Drivetrain;
import team_5002.robot.systems.Lift;
import team_5002.robot.systems.Shooter;
import team_5002.robot.systems.Bling.blingState;
import edu.wpi.first.cameraserver.CameraServer;

public class Robot extends TimedRobot {
  public static devices Devices = new devices("devices.json");
  public static controls Controls = new controls("controls.json");
  private Drivetrain drive = new Drivetrain();
  private Shooter shooter = new Shooter();
  private Lift lift = new Lift();
  public static Bling bling = new Bling();
  private boolean matchStarted = false;
  private boolean matchEnded = false;

  @Override
  public void robotInit() { CameraServer.startAutomaticCapture(); }

  @Override
  public void robotPeriodic(){
    if(!matchStarted){bling.setLEDs(blingState.beforeMatch);}
    if(!matchEnded){bling.setLEDs(blingState.afterMatch);}
  }

  @Override
  public void autonomousInit(){matchStarted = true;}

  @Override
  public void autonomousPeriodic(){
    bling.setLEDs(blingState.autonMode);
  }

  @Override
  public void disabledPeriodic(){
    bling.setLEDs(blingState.disabled);
  }

  @Override
  public void teleopPeriodic() {
    bling.resetState();
    drive.loop();
    shooter.loop();
    lift.loop();
    if(bling.state == 0){
      bling.setLEDs(blingState.teleOpMode);
    }
  }

  @Override
  public void teleopExit(){matchEnded = true;}
}