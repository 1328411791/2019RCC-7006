/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import frc.robot.commands.B_CastCargoCommand;
import frc.robot.commands.B_CatchCargoCommand;
import frc.robot.commands.B_DropIntakeCommand;
import frc.robot.commands.B_ElevatorDownCommand;
import frc.robot.commands.B_ElevatorUpCommand;
import frc.robot.commands.B_RaiseIntakeCommand;

/**
 * Add your docs here.
 */
public class OI {

    Joystick mJoystick1 =new Joystick(1);
	Joystick mJoystick0 =new Joystick(0);
	public Joystick getJoystick() {
        return mJoystick0;
    }

    public OI()
    {
        new JoystickButton(mJoystick1, 1).whenActive(new B_CastCargoCommand());
		new JoystickButton(mJoystick1, 3).whenActive(new B_CatchCargoCommand());
		
		new JoystickButton(mJoystick1, 2).whenActive(new B_ElevatorDownCommand());
		new JoystickButton(mJoystick1, 4).whenActive(new B_ElevatorUpCommand());

		new JoystickButton(mJoystick1, 7).whenActive(new B_RaiseIntakeCommand());
		new JoystickButton(mJoystick1, 8).whenActive(new B_DropIntakeCommand());
    }
}
