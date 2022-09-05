package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ArmsForward extends CommandBase{
    private final Climber m_climber;
    public ArmsForward(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }
    @Override
    public void execute(){
        m_climber.armsForward();
    }
    @Override
    public boolean isFinished(){
        return m_climber.getArms() == DoubleSolenoid.Value.kForward;
    }
    
}
