package org.usfirst.frc.team4028.robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.*;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4028.robot.Enums.SIDE_GEAR;

import com.ctre.CANTalon;
import com.ctre.CANTalon.*;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.SPI.Port;
import edu.wpi.first.wpilibj.Solenoid;

public class Robot extends IterativeRobot {
	CANTalon _talonLMaster = new CANTalon(11);
	CANTalon _talonLSlave = new CANTalon(12);
	//CANTalon _talonLSlave2 = new CANTalon(12);
	CANTalon _talonRMaster = new CANTalon(9);
	CANTalon _talonRSlave = new CANTalon(10);
	//CANTalon _talonRSlave2 = new CANTalon(9);
	Joystick _joy = new Joystick(0);
	NavXGyro _navX;
	boolean _wasPressed = false;
	//private static final double _motorCommand = 20.00;
	private static final double _degreesPerRotation = 48; 
	Solenoid _solenoid;
	boolean _firstPathFinished;
	boolean _secondPathFinished;
	boolean _thirdPathFinished; 
	private SIDE_GEAR _sideGear;
	public void robotInit()
	{
		_sideGear=SIDE_GEAR.STRAIGHT;
		
		_solenoid = new Solenoid(0,3);
		//_navX = new NavXGyro(Port.kMXP);
		_talonLSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		_talonLSlave.set(11);
		//_talonLSlave2.changeControlMode(CANTalon.TalonControlMode.Follower);
		//_talonLSlave2.set(10);
		_talonRSlave.changeControlMode(CANTalon.TalonControlMode.Follower);
		_talonRSlave.set(9);
		//_talonRSlave2.changeControlMode(CANTalon.TalonControlMode.Follower);
		//_talonRSlave2.set(7);
		
		
		/* first choose the sensor */
	/*	_talonLMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_talonRMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_talonLMaster.reverseSensor(false);
		_talonRMaster.reverseSensor(true);
		_talonLMaster.configEncoderCodesPerRev(1097);
		_talonRMaster.configEncoderCodesPerRev(1097);
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		 set the peak and nominal outputs, 12V means full 
		_talonLMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		_talonLMaster.configPeakOutputVoltage(+12.0f, -12.0f);
		set closed loop gains in slot0 - see documentation 
		_talonLMaster.setProfile(0);
		_talonLMaster.setF(0.35);
		_talonLMaster.setP(0.35);
		_talonLMaster.setI(0.0);
		_talonLMaster.setD(0.0);
		/ set acceleration and vcruise velocity - see documentation/
		_talonLMaster.setMotionMagicCruiseVelocity(250);
		_talonLMaster.setMotionMagicAcceleration(250);
		/* set the peak and nominal outputs, 12V means full *
		_talonRMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		_talonRMaster.configPeakOutputVoltage(+12.0f, -12.0f);
		/ set closed loop gains in slot0 - see documentation *
		_talonRMaster.setProfile(0);
		_talonRMaster.setF(0.35);
		_talonRMaster.setP(0.35);
		_talonRMaster.setI(0.0);
		_talonRMaster.setD(0.0);
		/* set acceleration and vcruise velocity - see documentation *
		_talonRMaster.setMotionMagicCruiseVelocity(250);
		_talonRMaster.setMotionMagicAcceleration(250);*/
		_talonLMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_talonRMaster.setFeedbackDevice(FeedbackDevice.CtreMagEncoder_Relative);
		_talonLMaster.reverseSensor(false);
		_talonRMaster.reverseSensor(true);
		//_talonLMaster.configEncoderCodesPerRev(1097);
		//_talonRMaster.configEncoderCodesPerRev(1097);
		// FeedbackDevice.QuadEncoder
		// _talon.configPotentiometerTurns(XXX), // if using
		// FeedbackDevice.AnalogEncoder or AnalogPot

		/* set the peak and nominal outputs, 12V means full */
		_talonLMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		_talonLMaster.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 - see documentation */
		_talonLMaster.setProfile(0);
		_talonLMaster.setF(0.25); // 0.35
		_talonLMaster.setP(1.0); //2.5
		_talonLMaster.setI(0.0);
		_talonLMaster.setD(50.0); // 50
		/* set acceleration and vcruise velocity - see documentation */
		_talonLMaster.setMotionMagicCruiseVelocity(700);
		_talonLMaster.setMotionMagicAcceleration(1000);
		/* set the peak and nominal outputs, 12
		 * V means full */
		_talonRMaster.configNominalOutputVoltage(+0.0f, -0.0f);
		_talonRMaster.configPeakOutputVoltage(+12.0f, -12.0f);
		/* set closed loop gains in slot0 - see documentation */
		_talonRMaster.setProfile(0);
		_talonRMaster.setF(.25);//0.35   0.25
		_talonRMaster.setP(1.0); //2.5
		_talonRMaster.setI(0.0);
		_talonRMaster.setD(50); // 50
		/* set acceleration and vcruise velocity - see documentation */
		_talonRMaster.setMotionMagicCruiseVelocity(700);
		_talonRMaster.setMotionMagicAcceleration(1000);
	}
	@Override
	public void teleopInit()
	{
		_firstPathFinished = false;
		_secondPathFinished = false;
		_thirdPathFinished = false;
	}
	
	
	public void teleopPeriodic() 
	{
		//_solenoid.set(true);
		if((_joy.getRawButton(2)))
		{
			_talonLMaster.setEncPosition(0);
			_talonRMaster.setEncPosition(0);
			//_navX.zeroYaw();
			_wasPressed = true;
		}
		else if(_joy.getRawButton(1))
		{
				double targetPos = 8.05;
				if(!_firstPathFinished && !_secondPathFinished && Math.abs(Math.abs(_talonRMaster.getEncPosition())-9.1*4096) > 400)
				{
					
				
				_talonLMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
				_talonRMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
				_talonRMaster.reverseOutput(true);
				_talonRMaster.set(9.1);
				_talonLMaster.set(9.1);
				}
				
				else if(Math.abs(Math.abs(_talonLMaster.getEncPosition())-9.1*4096) <=400)
				{
					_firstPathFinished = true;
					_talonLMaster.setEncPosition(0);
					_talonRMaster.setEncPosition(0);
					_talonLMaster.setPosition(00);
					_talonRMaster.setPosition(00);
					//_sideGear =SIDE_GEAR.TURN;
					
				}
				if(_firstPathFinished && !_secondPathFinished && Math.abs(Math.abs(_talonLMaster.getEncPosition())-1.2*4096) > 600)
				{
						
					_talonLMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
					_talonRMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
					_talonRMaster.reverseOutput(true);
					//System.out.println(currentYaw);
					_talonLMaster.set(-1.2);
					_talonRMaster.set(1.2);
					//System.out.println();
				}
				else if (_firstPathFinished && !_secondPathFinished && Math.abs(Math.abs(_talonLMaster.getEncPosition())-1.2*4096) <= 600)
				{
					_secondPathFinished = true;
					_talonLMaster.setEncPosition(0);
					_talonLMaster.setPosition(0);
					_talonRMaster.setEncPosition(0);
					_talonRMaster.setPosition(0);;
					System.out.println("Turn Complete");
					//_sideGear = SIDE_GEAR.TO_PEG;
				}
			if(_secondPathFinished && _firstPathFinished && !_thirdPathFinished)
			{
				
				//double newTargetPos = 1.2;
				_talonLMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
				_talonRMaster.changeControlMode(CANTalon.TalonControlMode.MotionMagic);
				_talonRMaster.reverseOutput(true);
				_talonRMaster.set(0.8);
				_talonLMaster.set(.8);
				System.out.println("Moving Forward");
			}

					
			
		}
		else
		{
			_talonLMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			_talonRMaster.changeControlMode(CANTalon.TalonControlMode.PercentVbus);
			_talonLMaster.set(_joy.getRawAxis(1)-0.6*_joy.getRawAxis(4));
			_talonRMaster.set(-(_joy.getRawAxis(1))-0.6*_joy.getRawAxis(4));
			//System.out.println(_talonLMaster.getPosition());
			
		}
		SmartDashboard.putNumber("Position", _talonRMaster.getPosition());
	}
}