package frc.robot.commands;

import frc.robot.Robot;


import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class B_DropIntakeCommand extends Command {

    public B_DropIntakeCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.m_BlockSubSystem);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_BlockSubSystem.climbBarSpeed(0.8);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_BlockSubSystem.mStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
