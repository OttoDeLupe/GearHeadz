
package org.usfirst.frc.team1289.robot.subsystems;

import org.usfirst.frc.team1289.robot.IOMap;
import org.usfirst.frc.team1289.robot.OperatorInterface;
import org.usfirst.frc.team1289.robot.commands.*;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;

/**
 *
 */
public class DriveTrain extends Subsystem 
{
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private static SpeedController _leftMotor = IOMap.driveTrainMotorLeft;
	private static SpeedController _rightMotor = IOMap.driveTrainMotorRight;
	private static RobotDrive _robotDrive = IOMap.driveTrainRobotDrive;

    public void initDefaultCommand() 
    {
        // Set the default command for a subsystem here.
        setDefaultCommand(new DriveViaJoystick());
    }
    
    // Sets the motor speed of all motors to the desired setting
    public void Move (double speed)
    {
     _leftMotor.set(speed);
     _rightMotor.set(speed);
     
    }

    // Sets the motor speed in opposite directions so robot turns
    // Caller has to invert speed to get opposite turning behavior
    public void Turn(double speed) 
    {
    	_leftMotor.set(speed);
    	_rightMotor.set(-speed);
    }

    public void ArcadeDrive()
    {
    	// this is inverted from what the WPI code says X & Y represent.
    	// but on-bot testing shows it works. And you have to negate the Y value...
    	double rotateValue =  -(OperatorInterface.joyStick.getY());
    	double moveValue = OperatorInterface.joyStick.getX();
    	
    	SmartDashboard.putNumber("stickMoveValue", moveValue);
    	SmartDashboard.putNumber("stickRotateValue", rotateValue);
    	
    	_robotDrive.arcadeDrive(moveValue, rotateValue, true);
    }
    
    public void Stop()
    {
    	_robotDrive.stopMotor();
    }
}
