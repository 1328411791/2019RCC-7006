package frc.robot.subsystem;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BlockSubSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	TalonSRX mTalonSRXBlock;

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public BlockSubSystem() {
    	mTalonSRXBlock = new TalonSRX(0);
		mTalonSRXBlock.configSelectedFeedbackSensor(FeedbackDevice.CTRE_MagEncoder_Relative, 0, 0);
		
	}
    
	public void climbBarSpeed(double speed) {
		mTalonSRXBlock.set(ControlMode.PercentOutput, speed);
		
		SmartDashboard.putNumber("climbBar Speed Power", speed);
	}
	
	public void mStop() {
		mTalonSRXBlock.set(ControlMode.PercentOutput, 0);
	}
}

