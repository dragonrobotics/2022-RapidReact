package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
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
        double speed = calculateSpeed();
        SmartDashboard.putNumber("TargetSpeed", speed);
        SmartDashboard.putNumber("CurrSpeed", shooter.getSpeed());
        shooter.setSpeed(speed);
    }

    private double calculateDistance(){
        double angle = limelight.getYAngle();
        SmartDashboard.putNumber("Distance", (Constants.goalHeight - Constants.cameraHeight)/ Math.tan(3.14125/180*(Constants.camAngle + angle)));
        return (Constants.goalHeight - Constants.cameraHeight)/ Math.tan(3.14125/180*(Constants.camAngle + angle))-40;
    }

    private double calculateSpeed(){
        if(SmartDashboard.getBoolean("SetSpeed", false)) return SmartDashboard.getNumber("Speed", 0);
        if(SmartDashboard.getBoolean("DisableLimelight", false)) return 3; //TODO: Test to find right speed.
        return (calculateDistance()*Constants.distanceSpeedMultiplier)+Constants.speedAddition;
    }

    @Override
    public boolean isFinished(){
        return Math.abs(Math.abs(shooter.getSpeed())-Math.abs(calculateSpeed())) < Constants.shooterSpeedDeadzone;
    }

    @Override
    public void end(boolean interupt){
        if(interupt){
            shooter.stop();
        }
    }
}
