// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj.XboxController;
import frc.robot.Constants.IDConstants;
import frc.robot.Constants.OperatorConstants;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;

// import edu.wpi.first.wpilibj.SpeedControllerGroup;
// import edu.wpi.first.wpilibj.GenericHID.Hand;

/**
 * The VM is configured to automatically run this class, and to call the functions corresponding to
 * each mode, as described in the TimedRobot documentation. If you change the name of this class or
 * the package after creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot {

  public Robot() {
    m_myRobot = new DifferentialDrive(m_leftFrontDrive, m_rightFrontDrive);
  }

  private Command m_autonomousCommand;
  private DifferentialDrive m_myRobot;

  private RobotContainer m_robotContainer;
  private final WPI_TalonFX m_leftFrontDrive = new WPI_TalonFX(IDConstants.FrontLeftDriveID);
  private final TalonSRX m_leftFrontSteer = new TalonSRX(IDConstants.FrontLeftSteerID);
  private final WPI_TalonFX m_rightFrontDrive = new WPI_TalonFX(IDConstants.FrontRightDriveID);
  private final TalonSRX m_rightFrontSteer = new TalonSRX(IDConstants.FrontRightSteerID);
  private final WPI_TalonFX m_leftBackDrive = new WPI_TalonFX(IDConstants.BackLeftDriveID);
  private final TalonSRX m_leftBackSteer = new TalonSRX(IDConstants.BackLeftSteerID);
  private final WPI_TalonFX m_rightBackDrive = new WPI_TalonFX(IDConstants.BackRightDriveID);
  private final TalonSRX m_rightBackSteer = new TalonSRX(IDConstants.BackRightSteerID); 

  //Look at Kyle to relate control bindings to motor movemment:
  //https://github.com/gwhs/KYLE_ROBOTICS_2021/tree/main

  MotorControllerGroup leftMotors = new MotorControllerGroup(m_leftFrontDrive, m_leftBackDrive);

  //MotorControllerGroup rightMotors = new MotorControllerGroup(m_rightFrontDrive, m_rightBackDrive, m_rightFrontSteer, m_rightBackSteer);
  //DifferentialDrive differentialDrive = new DifferentialDrive(leftMotors, rightMotors);

  private final XboxController m_XboxController = new XboxController(OperatorConstants.kDriverControllerPort);

  /**
   * This function is run when the robot is first started up and should be used for any
   * initialization code.
   */
  @Override
  public void robotInit() {
    // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
    // autonomous chooser on the dashboard.
    m_robotContainer = new RobotContainer();
  }

  private DifferentialDrive newDifferentialDrive(SpeedControllerGroup leftMotors2, SpeedControllerGroup rightMotors2) {
    return null;
  }

  /**
   * This function is called every 20 ms, no matter the mode. Use this for items like diagnostics
   * that you want ran during disabled, autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before LiveWindow and
   * SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {
    // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
    // commands, running already-scheduled commands, removing finished or interrupted commands,
    // and running subsystem periodic() methods.  This must be called from the robot's periodic
    // block in order for anything in the Command-based framework to work.
    CommandScheduler.getInstance().run();
  }

  /** This function is called once each time the robot enters Disabled mode. */
  @Override
  public void disabledInit() {

  }

  @Override
  public void disabledPeriodic() {}

  /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
  @Override
  public void autonomousInit() {
    m_autonomousCommand = m_robotContainer.getAutonomousCommand();

    // schedule the autonomous command (example)
    if (m_autonomousCommand != null) {
      m_autonomousCommand.schedule();
    }
  }

  /** This function is called periodically during autonomous. */
  @Override
  public void autonomousPeriodic() {}

  @Override
  public void teleopInit() {
    // This makes sure that the autonomous stops running when
    // teleop starts running. If you want the autonomous to
    // continue until interrupted by another command, remove
    // this line or comment it out.
    if (m_autonomousCommand != null) {
      m_autonomousCommand.cancel();
    }
  }

  /** This function is called periodically during operator control. */
  @Override
  public void teleopPeriodic() {
    m_myRobot.arcadeDrive(m_XboxController.getLeftY()*1, m_XboxController.getLeftX()*.9);
  }

  @Override
  public void testInit() {
    // Cancels all running commands at the start of test mode.
    CommandScheduler.getInstance().cancelAll();
  }

  /** This function is called periodically during test mode. */
  @Override
  public void testPeriodic() {}

  /** This function is called once when the robot is first started up. */
  @Override
  public void simulationInit() {}

  /** This function is called periodically whilst in simulation. */
  @Override
  public void simulationPeriodic() {}
}
