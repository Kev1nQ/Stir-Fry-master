// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

  package frc.robot.subsystems;

  import edu.wpi.first.networktables.NetworkTable;
  import edu.wpi.first.networktables.NetworkTableInstance;
  import edu.wpi.first.wpilibj2.command.SubsystemBase;
  import frc.robot.Constants;

  public class LimelightSubsystem extends SubsystemBase {
  
    private final NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight");
    
    private int currentPipeline = Constants.VisionConstants.Default_Pipeline; // April Tags

    public void setPipeline(int pipeline) {
      currentPipeline = pipeline;
      table.getEntry("pipeline").setNumber(pipeline);
    }

    public int getCurrentPipeline() {
      return currentPipeline;
    }

    private static boolean m_LimelightHasValidTargets = false;
    private static double m_LimelightDriveCommand = 0.0;
    private static double m_LimelightRotateCommand = 0.0;

    public static void Update_Limelight_Values() {
    
        double tx = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tx").getDouble(0.0); 
        double ty = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ty").getDouble(0.0);
        double targetArea = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("ta").getDouble(0.0);
        double validTargets = NetworkTableInstance.getDefault().getTable("Limelight").getEntry("tv").getDouble(0.0);
        

        if (validTargets < 1) {
          m_LimelightHasValidTargets = false;
          m_LimelightDriveCommand = 0.0;
          m_LimelightRotateCommand = 0.0;
          return;
        }

        m_LimelightHasValidTargets = true;

        double rotateError = tx; // horizontally off 
        double driveError = Constants.AutoAlignConstants.desiredtargetArea - targetArea;

        double rotateCommand = rotateError * Constants.AutoAlignConstants.rotateSpeed;
        double driveCommand = driveError * Constants.AutoAlignConstants.driveSpeed;
        
        if (driveCommand > Constants.AutoAlignConstants.maxDriveSpeed) {
          driveCommand = Constants.AutoAlignConstants.maxDriveSpeed;
        } 

        m_LimelightDriveCommand = driveCommand;
        m_LimelightRotateCommand = rotateCommand;

    }

      
    public boolean hasValidTargets() {
      return m_LimelightHasValidTargets;
    }

    public double getDriveCommand(double drivespeed, double desiredtargetarea) {
      return m_LimelightDriveCommand;
    }

    public double getRotateCommand(double rotatespeed) {
      return m_LimelightRotateCommand;
    }

    
    /** Creates a new LimelightSubsystem. */
    public LimelightSubsystem() {}

    @Override
    public void periodic() {
      // This method will be called once per scheduler run
    }

}
