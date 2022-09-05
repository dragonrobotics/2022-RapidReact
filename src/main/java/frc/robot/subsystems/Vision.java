package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Vision extends SubsystemBase{
    private static NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight");
    public Vision(){}
    public double getYAngle(){return limelight.getEntry("ty").getDouble(100);}
    public double getXAngle(){return limelight.getEntry("tx").getDouble(100);}
}
