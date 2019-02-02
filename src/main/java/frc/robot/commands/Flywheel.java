/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.constant.constants;
// all the imports

public class Flywheel extends Command {
  public static double m_desiredDistance;
  // the distance that the user want the robot to travel
  public static double m_error;
  // the distance betwen actual position and desired position
  public static double m_output;
  // power
  public static double m_currentTime;
  // time starts when execute runs
  public static double m_previousTime;
  // time starts when initialize runs
  public static double m_previousError;
  // previous error
  public static double m_derivative;
  // the rate of change = change of error / change of time

  public Flywheel(double distance) {
  // constuctor (double distance defined smartdashboard button)
    requires(Robot.m_Drive);
    // it uses the drive subsystem and can not function without it
    m_desiredDistance = distance;
    // desired distance 

    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
  // only runs one time
    m_previousTime = Timer.getFPGATimestamp();
    // gets time and is updated afer update
    m_previousError = m_desiredDistance;
    // states your error after execute has ran

    // initial position = 0, error = total distance
    // runs multiple times until conditions are met
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
  // runs until conditions are met
    m_currentTime = Timer.getFPGATimestamp();
    // starts your time in execute and is constantly changes as time cycles
    m_error = m_desiredDistance - Robot.m_Drive.getPosition();
    // getting the distance between the acual position and the desired position
    m_derivative = (m_error - m_previousError) - (m_currentTime - m_previousTime);
    // change in error over time (and calculates it)
    m_output = m_error * constants.kp * m_derivative * constants.kD; 
    // calculates your output using the proportional and derivative terms
        // refer to constants
    Robot.m_Drive.setPower(m_output);
    // sets the drive power to the calculated output
    m_previousTime = m_currentTime;
    // updates the previous time to the current time of the execute
    m_previousError = m_error;
    // updates the previous error to the error after execute
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
  // conditions in order to end the code
    return Math.abs(m_error) <= constants.kT;
    // when the absolute value of the error is less then or equal to the tolerance, the code with end
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  // what you want to mechanism to do when the code ends
    Robot.m_Drive.setPower(0);
    // sets the motor power to 0 when the code ends
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  // if the code is interrupted, then this will tell the robot to do
  }
}
