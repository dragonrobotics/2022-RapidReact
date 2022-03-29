package team_5002.robot.libraries;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

import org.ejml.equation.ManagerFunctions.InputN;
import org.json.*;
import edu.wpi.first.wpilibj.Filesystem;
import edu.wpi.first.wpilibj.Joystick;
public class controls {
    static HashMap<String, Object[]> controls = new HashMap<>();
    static Joystick joystick;
    public controls(String controlsConfigName){
        JSONObject controlsConfig;
        Path controlsConfigPath = Filesystem.getDeployDirectory().toPath().resolve(controlsConfigName);
        try{
            controlsConfig = new JSONObject(Files.readString(controlsConfigPath));

            JSONArray controllerList = controlsConfig.getJSONArray("controllers");
            HashMap<String, Joystick> controllers = new HashMap<>();
            for (int controllerNum = 0; controllerNum < controllerList.length(); controllerNum++) {
                JSONObject controller = controllerList.getJSONObject(controllerNum);
                controllers.put(controller.getString("name"), new Joystick(controller.getInt("controllerPort")));
                joystick = new Joystick(controller.getInt("controllerPort"));
            }
            
            JSONArray controlList = controlsConfig.getJSONArray("controls");
            for (int controlNum = 0; controlNum < controlList.length(); controlNum++) {
                JSONObject control = controlList.getJSONObject(controlNum);
                String controlName = control.getString("controlName");
                String controlType = control.getString("controlType");

                int inputNumber = control.getInt("inputID");
                String controllerName = control.getString("controllerName");
                Object[] inputInfo = {controllers.get(controllerName), inputNumber, controlType};
                controls.put(controlName, inputInfo);

            }
        }catch(IOException e){
            System.exit(1);
        }
    }
    public Object getInput(String inputName){
        if(inputName == "controller"){
            return joystick;
        }
        Object[] input = controls.get(inputName);
        if(input == null){
            return 0;
        }
        Joystick controller = (Joystick) input[0];
        if(input[2].equals("axis")){
            return controller.getRawAxis((int) input[1]);
        }else if(input[2].equals("button")){
            return controller.getRawButton((int) input[1]);
        }else if(input[2].equals("trigger")){
            return controller.getTrigger();
        }else{
            return null;
        }
    }
}
