package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Vision;

public class Aim extends CommandBase{
    private final Vision limelight;
    private final Drivetrain wheelBase;
    public Aim(Vision camera, Drivetrain drivetrain){
        limelight = camera;
        wheelBase = drivetrain;
        addRequirements(camera, drivetrain);
    }
    @Override
    public void execute(){
        double xAngle = limelight.getXAngle();
        wheelBase.drive(0, 0, xAngle > 0 ? -.2 : .2);
    }

    @Override
    public void end(boolean Interrupted){
        wheelBase.stop();
    }

    @Override
    public boolean isFinished(){
        return Math.abs(limelight.getXAngle()) < Constants.aimDeadzone;
    }
}
