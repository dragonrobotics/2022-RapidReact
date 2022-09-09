package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class LowerLift extends CommandBase{
    private final Climber m_climber;
    public LowerLift(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }
    @Override
    public void execute(){
        m_climber.liftDown();
    }
    
    @Override
    public void end(boolean interup){
        m_climber.stopLift();
    }
}
