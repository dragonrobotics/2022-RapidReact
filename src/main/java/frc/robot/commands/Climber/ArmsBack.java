package frc.robot.commands.Climber;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Climber;

public class ArmsBack extends CommandBase{
    private final Climber m_climber;
    public ArmsBack(Climber climber){
        m_climber = climber;
        addRequirements(climber);
    }
    @Override
    public void execute(){
        m_climber.armsBack();
    }
    @Override
    public boolean isFinished(){
        return m_climber.getArms() == DoubleSolenoid.Value.kReverse;
    }
    
}
