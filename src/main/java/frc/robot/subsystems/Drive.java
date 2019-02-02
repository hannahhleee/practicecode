/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Drive extends Subsystem {
// not the constructor
// outside the constuctor and where all the code goes

  public static TalonSRX m_motor;
  // defines the talon
  public Drive() {
  // construtor - variables
    m_motor = new TalonSRX(0);
    // sets the m_motor to port 0 on TalonSRX
    // defines the motor
  }

  public void setPower(double motor) {
  // sets power 
    m_motor.set(ControlMode.PercentOutput, motor);
    //PercentOutput = power
  }

  public double getPosition() {
  // gets position
    return m_motor.getSelectedSensorPosition();
    // return the distance
  }

  @Override
  public void initDefaultCommand() {
  // default command
  }
}
