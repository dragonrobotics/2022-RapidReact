package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class LowerIntake extends CommandBase{
    private final IntakeSubsystem m_intake;
    public LowerIntake(IntakeSubsystem intake){
        m_intake = intake;
        addRequirements(intake);
    }
    @Override
    public void execute(){
        m_intake.lowerIntake();
    }
    @Override
    public boolean isFinished(){
        return m_intake.intakeDown();
    }
    
}
