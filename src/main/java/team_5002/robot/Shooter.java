package team_5002.robot;

public class Shooter {
    public static double computeSpeed(double shooterAngle) {
        double distance = Vision.determineObjectDist();
        return Math.sqrt((16.1 * Math.pow(distance, 2)) / (distance * Math.tan(shooterAngle) - Vision.camAngle - Vision.goalHeight)) / Math.cos(shooterAngle);
      }
    public static double getShooterSpeed(){
        return 0;
    }
    public static void setShooterSpeed(double speed){
        
    }
}
