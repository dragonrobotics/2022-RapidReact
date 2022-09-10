package frc.robot.commands.Shooter;

import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;

public class ShortShot extends CommandBase{
    private final IntakeSubsystem m_intake;
    private final Shooter m_shooter;
    public ShortShot(IntakeSubsystem intake, Shooter shooter){
        m_intake = intake;
        m_shooter = shooter;
        addRequirements(intake, shooter);
    }

    @Override
    public void execute(){
        double speed = SmartDashboard.getNumber("ShortSpeed", 200);
        m_shooter.setSpeed(speed);
        if(Math.abs(m_shooter.getSpeed()-speed) < 10){
            m_intake.startBelt();
            m_intake.startFeed();
            m_intake.startIntake();
        }else{
            m_intake.stopBelt();
            m_intake.stopFeed();
            m_intake.stopIntake();
        }
    }

    @Override
    public void end(boolean interup){
        m_intake.stopBelt();
        m_intake.stopFeed();
        m_shooter.stop();
    }
}
