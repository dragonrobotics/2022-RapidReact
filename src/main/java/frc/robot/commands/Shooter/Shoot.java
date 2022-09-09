package frc.robot.commands.Shooter;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.Vision;
import frc.robot.subsystems.Shooter;

public class Shoot extends SequentialCommandGroup{
    public Shoot(Shooter shooter, Vision limelight, Drivetrain drivetrain, IntakeSubsystem intake, int limit){
        addCommands(
            new Aim(limelight, drivetrain),
            new RampUp(shooter, limelight),
            new Fire(shooter, intake, limit)
        );
    }
}
