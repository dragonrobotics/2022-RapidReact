package frc.robot.commands.Drivetrain;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class Drive extends CommandBase{
    private final Drivetrain m_drivetrain;
    private final XboxController m_joystick;
    private final int m_straightAxis;
    private final int m_strafeAxis;
    private final int m_turnAxis;
    public Drive(Drivetrain drivetrain, XboxController controller, int StraightAxis, int StrafeAxis, int TurnAxis){
        m_drivetrain = drivetrain;
        m_straightAxis = StraightAxis;
        m_strafeAxis = StrafeAxis;
        m_turnAxis = TurnAxis;
        m_joystick = controller;
        addRequirements(drivetrain);
    }

    @Override
    public void execute(){
        double DeadZone = .2;
        double StraightAxis = m_joystick.getRawAxis(m_straightAxis);
        double StrafeAxis = m_joystick.getRawAxis(m_strafeAxis);
        double TurnAxis = m_joystick.getRawAxis(m_turnAxis);
        StraightAxis = Math.abs(StraightAxis) > DeadZone ? StraightAxis: 0;
        StrafeAxis = Math.abs(StrafeAxis) > DeadZone ? StrafeAxis: 0;
        TurnAxis = Math.abs(TurnAxis) > DeadZone ? TurnAxis: 0;
        StraightAxis = Math.pow(StraightAxis, 3);
        StrafeAxis = Math.pow(StrafeAxis, 3);
        TurnAxis = Math.pow(TurnAxis, 3);
        if(SmartDashboard.getBoolean("SpeedLimiter", false))
        {
            double SpeedLimit = SmartDashboard.getNumber("SpeedLimit", .2);
            StraightAxis *= SpeedLimit;
            StrafeAxis *= SpeedLimit;
            TurnAxis *= SpeedLimit;
        }
        if(StraightAxis != 0){
            TurnAxis = (TurnAxis-(TurnAxis*(Math.abs(StraightAxis)/2)));
        }
        m_drivetrain.drive(StraightAxis, StrafeAxis, TurnAxis);
    }

    @Override
    public void end(boolean interup){
        m_drivetrain.stop();
    }
}
