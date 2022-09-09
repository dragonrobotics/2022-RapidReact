package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Move extends CommandBase{
    Drivetrain m_drivetrain;
    double m_x;
    double m_y;
    double m_r;
    double m_time;
    public Move(Drivetrain drivetrain, double x, double y, double r, double time){
        m_drivetrain = drivetrain;
        m_x = x;
        m_y = y;
        m_r = r;
        m_time = time*20;
        addRequirements(drivetrain);
    }
    @Override
    public void execute(){
        m_drivetrain.drive(m_y, m_x, m_r);
    }
    @Override
    public boolean isFinished(){
        m_time -= 1;
        return m_time < 0;
    }
    @Override
    public void end(boolean interupt){
        m_drivetrain.stop();
    }

}
