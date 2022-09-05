package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class RaiseLift extends CommandBase{
    private final Climber m_climber;
    public RaiseLift(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }
    @Override
    public void execute(){
        m_climber.liftUp();
    }
    @Override
    public boolean isFinished(){
        return m_climber.getLiftState() == 1;
    }
    
    @Override
    public void end(boolean interup){
        m_climber.stopLift();
    }
}
