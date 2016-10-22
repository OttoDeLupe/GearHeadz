// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc1289.stronghold.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc1289.stronghold.Robot;
import org.usfirst.frc1289.stronghold.RobotMap;

/**
 *
 */
public class DriveViaQuad extends Command {
	
	double QuadDistance,MotorSpeed;

    
    public DriveViaQuad(double distance,double speed) {

    	QuadDistance = distance;
    	MotorSpeed = speed;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	RobotMap.driveTrainQuadLeft.reset();
    	RobotMap.driveTrainQuadRight.reset();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.moverobot(-MotorSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	double quadl = Robot.driveTrain.getquad(1);
    	double quadr = Robot.driveTrain.getquad(2);
    	SmartDashboard.putNumber("DrivewithQuad Left",quadl);
    	SmartDashboard.putNumber("DrivewithQuad Right",quadr);
    	SmartDashboard.putNumber("calc distance", (quadl+quadr)/2);
    	if ( Math.abs(((quadl + quadr)/2)) > QuadDistance) return true; else return false;
        
    }

    // Called once after isFinished returns true
    protected void end() {
    	
    	Robot.driveTrain.moverobot(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}