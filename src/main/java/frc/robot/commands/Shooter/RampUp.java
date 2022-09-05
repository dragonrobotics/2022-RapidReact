package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Vision;

public class RampUp extends CommandBase{
    private final Shooter shooter;
    private final Vision limelight;
    public RampUp(Shooter cannon, Vision camera){
        shooter = cannon;
        limelight = camera;
        addRequirements(cannon, camera);
    }

    @Override
    public void execute(){
        shooter.setSpeed(calculateSpeed());
    }

    private double calculateDistance(){
        double angle = limelight.getYAngle();

        return (Constants.goalHeight - Constants.cameraHeight)/ Math.tan(3.14125/180*(Constants.camAngle + angle));
    }

    private double calculateSpeed(){
        return (calculateDistance()*Constants.distanceSpeedMultiplier)+Constants.speedAddition;
    }

    @Override
    public boolean isFinished(){
        return Math.abs(shooter.getSpeed()-calculateSpeed()) < Constants.shooterSpeedDeadzone;
    }

    @Override
    public void end(boolean interupt){
        if(interupt){
            shooter.stop();
        }
    }
}
