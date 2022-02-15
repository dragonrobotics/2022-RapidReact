// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package team_5002.robot;

import static edu.wpi.first.wpilibj.DoubleSolenoid.Value.*;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;

/** Add your docs here. */

public class Pneumatics
{
	public Pneumatics pneumatics() {
		return this;
	}

	Compressor pcmCompressor = new Compressor(0, PneumaticsModuleType.CTREPCM);
	static DoubleSolenoid Double1 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 2, 3);
    static DoubleSolenoid Double2 = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, 1, 0);
	boolean enabled = pcmCompressor.enabled();
	boolean pressureSwitch = pcmCompressor.getPressureSwitchValue();
	//double current = pcmCompressor.getCompressorCurrent();
	double currentPressure = pcmCompressor.getPressure();
	
	public void setCompressor() {
		pcmCompressor.enableDigital();
		pcmCompressor.disable();
	}

	public static void open1() {
		Double1.set(kReverse);
	}

	public static void open2() {
		Double2.set(kReverse);
	}

	public static void close1() {
		Double1.set(kForward);
	}

	public static void close2() {
		Double2.set(kForward);
	}

	public void turnOff() {
		Double1.set(kOff);
		Double2.set(kOff);
	}
}