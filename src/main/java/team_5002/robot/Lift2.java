// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

/** Add your docs here. */
public class Lift2 {
    Pneumatics pneumatics1;
    Pneumatics pneumatics2;
    static WPI_TalonSRX liftMotor;
    public Lift2(WPI_TalonSRX liftMotor, Pneumatics pneumatics1, Pneumatics pneumatics2){
        this.pneumatics1 = pneumatics1;
        this.pneumatics2 = pneumatics2;
    }

    public void armUp() {
        this.pneumatics1.close();
    }
    
    public void armDown() {
        this.pneumatics1.open();
    }
    
    public void armOut() {
        this.pneumatics2.close();
    }

    public void armIn() {
        this.pneumatics2.open();
    }

    public static void pullUp() {
        liftMotor.set(0.5);
    }

    public static void drop() {
        liftMotor.set(-0.5);
    }

    public static void armStop() {
        liftMotor.set(0);
        liftMotor.setNeutralMode(NeutralMode.Brake);
    }
}