package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.IntakeSubsystem;

public class RaiseIntake extends CommandBase{
    private final IntakeSubsystem m_intake;
    public RaiseIntake(IntakeSubsystem intake){
        m_intake = intake;
        addRequirements(intake);
    }
    @Override
    public void execute(){
        m_intake.raiseIntake();
    }
    @Override
    public boolean isFinished(){
        return !m_intake.intakeDown();
    }
    
}
