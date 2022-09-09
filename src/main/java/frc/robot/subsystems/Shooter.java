package frc.robot.subsystems;

import java.util.ResourceBundle.Control;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.SubsystemBase;


public class Shooter extends  SubsystemBase{

    public class Gains {
        public final double kP;
        public final double kI;
        public final double kD;
        public final double kF;
        public final int kIzone;
        public final double kPeakOutput;
        
        public Gains(double _kP, double _kI, double _kD, double _kF, int _kIzone, double _kPeakOutput){
            kP = _kP;
            kI = _kI;
            kD = _kD;
            kF = _kF;
            kIzone = _kIzone;
            kPeakOutput = _kPeakOutput;
        }
    }
    
    private double motorPower = 0;
    private WPI_TalonSRX shooterMotor1 = new WPI_TalonSRX(1);
    private WPI_TalonSRX shooterMotor2 = new WPI_TalonSRX(2);
    private static Gains kGains_Velocit;
    private static int kPIDLoopIdx = 0;
    private static int kTimeoutMs = 30;
    public Shooter(){
        kGains_Velocit = new Gains( 0.25, 0.001, 20, 1023.0/7200.0,  300,  1.00);
        shooterMotor1.setInverted(true);
        shooterMotor2.setInverted(true);
        shooterMotor2.follow(shooterMotor1);
        shooterMotor1.configFactoryDefault();

		/* Config sensor used for Primary PID [Velocity] */
        shooterMotor1.configSelectedFeedbackSensor(FeedbackDevice.QuadEncoder,
                                            kPIDLoopIdx, 
                                            kTimeoutMs);

        /**
		 * Phase sensor accordingly. 
         * Positive Sensor Reading should match Green (blinking) Leds on Talon
         */
		shooterMotor1.setSensorPhase(true);

		/* Config the peak and nominal outputs */
		shooterMotor1.configNominalOutputForward(0, kTimeoutMs);
		shooterMotor1.configNominalOutputReverse(0, kTimeoutMs);
		shooterMotor1.configPeakOutputForward(1, kTimeoutMs);
		shooterMotor1.configPeakOutputReverse(-1, kTimeoutMs);

		/* Config the Velocity closed loop gains in slot0 */
		shooterMotor1.config_kF(kPIDLoopIdx, kGains_Velocit.kF, kTimeoutMs);
		shooterMotor1.config_kP(kPIDLoopIdx, kGains_Velocit.kP, kTimeoutMs);
		shooterMotor1.config_kI(kPIDLoopIdx, kGains_Velocit.kI, kTimeoutMs);
		shooterMotor1.config_kD(kPIDLoopIdx, kGains_Velocit.kD, kTimeoutMs);
    }

    public void reverse(){
        shooterMotor1.set(-1);
        shooterMotor2.set(-1);
    }

    public void setSpeed(double rpm){
        
        rpm = rpm/60*4096;

        shooterMotor1.set(ControlMode.Velocity, rpm);
    }

    public double getSpeed(){
        return -shooterMotor1.getSelectedSensorVelocity()/4096*60;
    }

    public void stop() {
        motorPower = 0;
        shooterMotor1.stopMotor();
        shooterMotor2.stopMotor();
    }

    public void setPower(double power) {
        shooterMotor1.set(power);
        shooterMotor2.set(power);
    }
}
