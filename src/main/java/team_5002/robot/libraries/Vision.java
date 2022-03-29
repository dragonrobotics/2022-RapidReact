package team_5002.robot.libraries;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Vision {
    public static double camAngle = 25;
    public static double cameraHeight = 126;
    public static double goalHeight = 264;
    

    public static double aim() {
        double tx = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tx").getDouble(0.0);
        tx = Double.isNaN(tx) ? 0 : tx;
        return tx;
    }

    // Determine the distance an object is from the limelight given the camera's height off of the ground and the object's height off of the ground.
    public static double determineObjectDist() {
        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0.0);
        double distance1 = (goalHeight - cameraHeight) / Math.tan(3.14125/180*(camAngle + ty));
        SmartDashboard.putNumber("distance", distance1);
        return distance1;
    }

    public static boolean canSeeTarget(){
        
        return NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getNumber(0).doubleValue() == 1;
    }
}