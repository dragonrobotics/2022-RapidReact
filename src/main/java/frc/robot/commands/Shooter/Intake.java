package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem ;
public class Intake extends CommandBase{
    private final IntakeSubsystem m_intake;
    public Intake(IntakeSubsystem intake){
        m_intake = intake;
        addRequirements(intake);
    }

    @Override
    public void execute(){
        m_intake.startIntake();
    }

    @Override
    public void end(boolean interup){
        m_intake.stopIntake();
    }
    
}
