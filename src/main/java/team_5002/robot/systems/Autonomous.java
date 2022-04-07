package team_5002.robot.systems;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team_5002.robot.systems.AutonomousFunctions.AutonomousStep;

public class Autonomous {
    private ArrayList<AutonomousStep> steps = new ArrayList<>();
    public enum StepState{
        WORKING,
        WAITING,
        FINISHED
    }

    private int stepNum = 0;

    public Autonomous(){}
    public Autonomous addStep(AutonomousStep step){
        steps.add(step);
        return this;
    }

    public void init(){
        steps.get(0).setReady();
    }

    public void loop(){
        SmartDashboard.putNumber("AutonStep", stepNum);
        if(stepNum > (steps.size()-1)){return;}
        AutonomousStep step = steps.get(stepNum);
        step.loop();
        SmartDashboard.putString("StepName", step.getClass().getName());
        if(step.getState() == StepState.FINISHED){
            stepNum = stepNum + 1;
            if(stepNum > (steps.size()-1)){return;}
            AutonomousStep nextStep = steps.get(stepNum);
            nextStep.setReady();
        }
        
    }
}
    