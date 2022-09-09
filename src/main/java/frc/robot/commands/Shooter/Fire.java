package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;

public class Fire extends CommandBase{
    private final Shooter shooter;
    private final IntakeSubsystem feeder;
    private int m_limit;
    public Fire(Shooter m_shooter, IntakeSubsystem m_feeder, int limit){
        shooter = m_shooter;
        feeder = m_feeder;
        m_limit = limit;
        addRequirements(m_shooter);
    }

    @Override
    public void execute(){
        feeder.startBelt();
        feeder.startFeed();
        feeder.startIntake();
        m_limit -= .01;
    }

    @Override
    public boolean isFinished(){
        return m_limit < 0;
    }

    @Override
    public void end(boolean interupted){
        shooter.stop();
        feeder.stopFeed();
        feeder.stopBelt();
        feeder.stopIntake();
    }
}
