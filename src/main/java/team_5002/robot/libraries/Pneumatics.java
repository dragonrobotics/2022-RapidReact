// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot.libraries;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;

/** Add your docs here. */

public class Pneumatics
{
	private static Compressor pcmCompressor;
	private DoubleSolenoid[] solenoids;

	public Pneumatics(DoubleSolenoid[] solenoids, Compressor compressor) {
		this.pcmCompressor = compressor;
		this.solenoids = solenoids;
		this.pcmCompressor.enableDigital();
		}


	public void open() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kReverse);
		}
	}

	public  void close() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kForward);
		}
	}


	public void turnOff() {
		for (DoubleSolenoid solenoid : this.solenoids) {
			solenoid.set(kOff);
		}
	}
}