// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.LEDSubsystem;
// import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.LimelightSubsystem;


public class Pipeline0Command extends CommandBase {

  private final LimelightSubsystem limeLight;
  private final LEDSubsystem ledSubsystem; // PIPELINE 0 = BURGUNDY

  /** Creates a new Pipeline0. */
  public Pipeline0Command(LimelightSubsystem limeLight, LEDSubsystem ledSubsystem) {
    this.limeLight = limeLight;
    this.ledSubsystem = ledSubsystem;
    addRequirements(limeLight, ledSubsystem);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    ledSubsystem.setBurgundy();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    limeLight.setPipeline(Constants.VisionConstants.Cube_Pipeline);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
