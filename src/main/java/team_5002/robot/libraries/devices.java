package team_5002.robot.libraries;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import org.json.*;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
public class devices {
    private HashMap<String, Object> devices = new HashMap<>();
    public devices(String deviceConfigName){
        JSONObject deviceConfig;
        Path deviceConfigPath = Filesystem.getDeployDirectory().toPath().resolve(deviceConfigName);
        try{
            deviceConfig = new JSONObject(Files.readString(deviceConfigPath));

            JSONArray deviceList = deviceConfig.getJSONArray("devices");
            for (int deviceNum = 0; deviceNum < deviceList.length(); deviceNum++) {
                JSONObject device = deviceList.getJSONObject(deviceNum);
                String deviceName = device.getString("deviceName");
                String deviceType = device.getString("deviceType");
                    if(deviceType.equals("motor")){
                        int portNumber = device.getInt("canID");
                        boolean inverted = device.getBoolean("inverted");
                        WPI_TalonSRX motor = new WPI_TalonSRX(portNumber);
                        motor.setInverted(inverted);
                        devices.put(deviceName, motor);
                    }
                    else if(deviceType.equals("solenoid")){
                        int forwardPort = device.getInt("forwardPort");
                        int reversePort = device.getInt("reversePort");
                        DoubleSolenoid solenoid = new DoubleSolenoid(20, PneumaticsModuleType.CTREPCM, forwardPort, reversePort);
                        devices.put(deviceName, solenoid);
                    }
            }
        }catch(IOException e){
            System.exit(1);
        }
    }

    public Object getDevice(String deviceName){
        return devices.get(deviceName);
    }
}
