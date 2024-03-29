/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystem.BlockSubSystem;
import frc.robot.subsystem.DriveSubsystem;
import frc.robot.subsystem.ElevatorSubsystem;
import frc.robot.subsystem.IntakeSubSystem;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {
  private static final String kDefaultAuto = "Default";
  private static final String kCustomAuto = "My Auto";
  public static DriveSubsystem m_drivesubsystem;
  public static OI m_Oi;
  public static ElevatorSubsystem m_ElevatorSubsystem;
  public static BlockSubSystem m_BlockSubSystem;
  public static IntakeSubSystem m_IntakeSubSystem;


  Thread m_visionThread;



  private String m_autoSelected;

  SendableChooser<Command> m_chooser = new SendableChooser<>();

  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    m_drivesubsystem = new DriveSubsystem();
    m_Oi = new OI();
    m_ElevatorSubsystem = new ElevatorSubsystem();
    m_BlockSubSystem = new BlockSubSystem();
    m_IntakeSubSystem = new IntakeSubSystem();



    SmartDashboard.putData("Auto choices", m_chooser);

    m_visionThread = new Thread(() -> {
			// Get the UsbCamera from CameraServer
			UsbCamera camera = CameraServer.getInstance().startAutomaticCapture(1);
			// Set the resolution
			camera.setResolution(640, 480);
	  
			// Get a CvSink. This will capture Mats from the camera
			CvSink cvSink = CameraServer.getInstance().getVideo();
			// Setup a CvSource. This will send images back to the Dashboard
			CvSource outputStream
				= CameraServer.getInstance().putVideo("Rectangle", 640, 480);
	  
			// Mats are very memory expensive. Lets reuse this Mat.
			Mat mat = new Mat();
	  
			// This cannot be 'true'. The program will never exit if it is. This
			// lets the robot stop this thread when restarting robot code or
			// deploying.
			while (!Thread.interrupted()) {
			  // Tell the CvSink to grab a frame from the camera and put it
			  // in the source mat.  If there is an error notify the output.
			  if (cvSink.grabFrame(mat) == 0) {
				// Send the output the error.
				outputStream.notifyError(cvSink.getError());
				// skip the rest of the current iteration
				continue;
			  }
			  // Put a rectangle on the image
			  Imgproc.rectangle(mat, new Point(100, 100), new Point(400, 400),
				  new Scalar(255, 255, 255), 5);
			  // Give the output stream a new image to display
			  outputStream.putFrame(mat);
			}
		  });
		  m_visionThread.setDaemon(true);
		  m_visionThread.start();
		}
  

  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString line to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional comparisons to
   * the switch structure below with additional strings. If using the
   * SendableChooser make sure to add them to the chooser code above as well.
   */
  @Override
  public void autonomousInit() {
    // m_autoSelected = SmartDashboard.getString("Auto Selector", kDefaultAuto);
    System.out.println("Auto selected: " + m_autoSelected);
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
    switch (m_autoSelected) {
      case kCustomAuto:
        // Put custom auto code here
        break;
      case kDefaultAuto:
      default:
        // Put default auto code here
        break;
    }
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
