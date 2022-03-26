// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot.libraries;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.DoubleSolenoid;

public class Pneumatics
{
	private DoubleSolenoid[] solenoids;
	

	public Pneumatics(DoubleSolenoid[] solenoids) {
		this.solenoids = solenoids;
		}


	public void close() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kReverse);
		}
	}

	public  void open() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kForward);
		}
	}


	public void turnOff() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kOff);
		}
	}


	public static void check() {
		
	}
}