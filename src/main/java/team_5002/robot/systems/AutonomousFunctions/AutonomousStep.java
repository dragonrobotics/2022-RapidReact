package team_5002.robot.systems.AutonomousFunctions;

import team_5002.robot.systems.Autonomous;

public interface AutonomousStep {
    public void setReady();
    public Autonomous.StepState getState();
    public void loop();
}
