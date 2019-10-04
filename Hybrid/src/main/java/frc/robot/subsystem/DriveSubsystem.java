/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystem;


import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.M_TeleopDriveCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  Spark mSparkleft = new Spark(1);
  Spark mSparkright = new Spark(0);

  DifferentialDrive mDrive = new DifferentialDrive(mSparkleft, mSparkright);

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new M_TeleopDriveCommand());
  }

  public void mTeleopDrive(Joystick mJoystick){
      mDrive.tankDrive(-mJoystick.getY()*1, -mJoystick.getThrottle()*1);
  }

  public void mStopDrive(){
    mDrive.stopMotor();
  }
}
