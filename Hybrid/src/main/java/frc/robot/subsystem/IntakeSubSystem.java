package frc.robot.subsystem;

import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class IntakeSubSystem extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	VictorSP mVictorSPCatchCargoUp;
	VictorSP mVictorSPCatchCargoDown;
	
	public IntakeSubSystem() {
		mVictorSPCatchCargoUp = new VictorSP(3);
		mVictorSPCatchCargoDown = new VictorSP(4);
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void catchBox(double speed) {
		mVictorSPCatchCargoUp.setSpeed(speed);
		mVictorSPCatchCargoDown.setSpeed(speed);
	}
    
    public void stopCatchBox() {
		mVictorSPCatchCargoUp.stopMotor();
		mVictorSPCatchCargoDown.stopMotor();
	}
}

