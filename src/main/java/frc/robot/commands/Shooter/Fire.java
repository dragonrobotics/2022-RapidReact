package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Shooter;

public class Fire extends CommandBase{
    private final Shooter shooter;
    private final IntakeSubsystem feeder;
    public Fire(Shooter m_shooter, IntakeSubsystem m_feeder){
        shooter = m_shooter;
        feeder = m_feeder;
        addRequirements(m_shooter);
    }

    @Override
    public void execute(){
        feeder.startBelt();
        feeder.startFeed();
        feeder.startIntake();
    }

    @Override
    public void end(boolean interupted){
        shooter.stop();
        feeder.stopFeed();
        feeder.stopBelt();
        feeder.startIntake();
    }
}
