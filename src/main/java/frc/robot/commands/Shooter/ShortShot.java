package frc.robot.commands.Shooter;

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
        m_intake.startBelt();
        m_intake.startFeed();
        m_shooter.setSpeed(200);
    }

    @Override
    public void end(boolean interup){
        m_intake.stopBelt();
        m_intake.stopFeed();
        m_shooter.stop();
    }
}
