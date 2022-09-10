package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Climber.*;
import frc.robot.commands.Drivetrain.*;
import frc.robot.commands.Shooter.*;
import frc.robot.subsystems.*;
import edu.wpi.first.wpilibj.XboxController.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import static edu.wpi.first.wpilibj.util.ErrorMessages.requireNonNullParam;

public class RobotContainer {
  //private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();
  //private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);
  private final XboxController controller = new XboxController(0);

  private final Shooter shooter = new Shooter();
  private final Vision limelight = new Vision();
  private final Drivetrain drivetrain = new Drivetrain();
  private final Climber climber = new Climber();
  private final IntakeSubsystem intake = new IntakeSubsystem();
  // shooter commands
  private final Shoot ShootCommand = new Shoot(shooter, limelight, drivetrain, intake, 10);
  private final Intake IntakeCommand = new Intake(intake);
  private final ShortShot ShortShotCommand = new ShortShot(intake, shooter);
  private final Backrun BackrunCommand = new Backrun(intake, shooter);

  // climber commands
  private final RaiseLift RaiseLiftCommand = new RaiseLift(climber);
  private final LowerLift LowerLiftCommand = new LowerLift(climber);
  private final ArmsForward ArmsForwardCommand = new ArmsForward(climber);
  private final ArmsBack ArmsBackCommand = new ArmsBack(climber);

  // intake commands
  private final RaiseIntake RaiseIntakeCommand = new RaiseIntake(intake);
  private final LowerIntake LowerIntakeCommand = new LowerIntake(intake);

  // drivetrain commands
  private final Drive DriveCommand = new Drive(drivetrain, controller, 1, 0, 4);


  public RobotContainer() {
    drivetrain.setDefaultCommand(DriveCommand);
    configureButtonBindings();
  }

  public void teleopInit(){
  }

  class JoystickTrigger extends Trigger{
    private final GenericHID m_joystick;
    private final int m_buttonNumber;

    public JoystickTrigger(GenericHID joystick, int buttonNumber) {
      requireNonNullParam(joystick, "joystick", "JoystickButton");
  
      m_joystick = joystick;
      m_buttonNumber = buttonNumber;
    }

    @Override
    public boolean get(){
      return m_joystick.getRawAxis(m_buttonNumber) > Constants.triggerDeadzone;
    }
  }

  private void configureButtonBindings() {
    SmartDashboard.putBoolean("SetSpeed", false);
    SmartDashboard.putBoolean("DisableLimelight", false);
    SmartDashboard.putBoolean("SpeedLimiter", false);
    SmartDashboard.putNumber("SpeedLimit", .2);
    JoystickButton buttonA = new JoystickButton(controller, Button.kA.value);
    JoystickButton buttonB = new JoystickButton(controller, Button.kB.value);
    JoystickButton buttonX = new JoystickButton(controller, Button.kX.value);
    JoystickButton buttonY = new JoystickButton(controller, Button.kY.value);
    JoystickButton rightJoystickButton = new JoystickButton(controller, Button.kRightStick.value);
    JoystickButton leftBumper = new JoystickButton(controller, Button.kLeftBumper.value);
    JoystickButton rightBumper = new JoystickButton(controller, Button.kRightBumper.value);
    JoystickTrigger leftTrigger = new JoystickTrigger(controller, Axis.kLeftTrigger.value);
    JoystickTrigger rightTrigger = new JoystickTrigger(controller, Axis.kRightTrigger.value);

    leftTrigger.whileActiveOnce(IntakeCommand);
    rightTrigger.whileActiveOnce(ShootCommand);

    leftBumper.whenPressed(ArmsBackCommand);
    rightBumper.whenPressed(ArmsForwardCommand);
    buttonA.whileActiveOnce(RaiseLiftCommand);
    buttonY.whileActiveOnce(LowerLiftCommand);

    rightJoystickButton.whenPressed(new ConditionalCommand(RaiseIntakeCommand, LowerIntakeCommand, intake::intakeDown));

    buttonX.whileActiveOnce(ShortShotCommand);
    buttonB.whileActiveOnce(BackrunCommand);
    SmartDashboard.putNumber("Speed", 0);

    

  }


  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return new SequentialCommandGroup(
      new Move(drivetrain, 0, .5, 0, 4),
      new Shoot(shooter, limelight, drivetrain, intake, 50)
    );
  }
}
