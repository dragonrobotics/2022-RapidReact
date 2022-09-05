package frc.robot.commands.Shooter;

import frc.robot.subsystems.IntakeSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Shooter;

public class Backrun extends CommandBase{
    private final IntakeSubsystem m_intake;
    private final Shooter m_shooter;
    public Backrun(IntakeSubsystem intake, Shooter shooter){
        m_intake = intake;
        m_shooter = shooter;
        addRequirements(intake, shooter);
    }
    @Override
    public void execute(){
        m_intake.reverseBelt();
        m_intake.reverseIntake();
        m_intake.reverseFeed();
        m_shooter.reverse();
    }

    @Override
    public void end(boolean interupt){
        m_intake.stopBelt();
        m_intake.stopFeed();
        m_intake.stopIntake();
        m_shooter.stop();
    }
}
