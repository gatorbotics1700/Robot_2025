package frc.robot;

import frc.robot.subsystems.DrivetrainSubsystem;
import frc.robot.subsystems.Mechanisms;
import frc.robot.subsystems.Mechanisms.MechanismStates;

public class Buttons {
    
  private DrivetrainSubsystem m_drivetrainSubsystem = Robot.m_drivetrainSubsystem;
  private Mechanisms m_mechanismSubsystem = Robot.m_mechanismSubsystem;
  
  public void buttonsPeriodic(){
      //driver
      /*if (OI.m_controller.getBButton()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }*/
      
      if (OI.driver.getLeftBumper()){ //emergency stop EVERYTHING
        m_drivetrainSubsystem.stopDrive(); 
      }
      
      if (OI.codriver.getAButton()){ 
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.AMP_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_AMP);
          System.out.println("=====A BUTTON=====SHOOTING IN AMP!!");
        }else if(m_mechanismSubsystem.getMechanismState() == MechanismStates.SPEAKER_HOLDING){
          m_mechanismSubsystem.setState(MechanismStates.SHOOTING_SPEAKER);
          System.out.println("=====A BUTTON=====SHOOTING IN SPEAKER!!");
        }else{
          System.out.println("=====A BUTTON=====ERROR NOT IN HOLDING CANNOT SHOOT !!!!!!!!!!!!!!!!!");
        }
      }
      
      if (OI.codriver.getYButton()){ 
        m_mechanismSubsystem.setState(MechanismStates.OFF);
        System.out.println("=======Y BUTTON====MECHANISMS STOP=======");
      }
      
      if (OI.codriver.getBButton()){
        if(m_mechanismSubsystem.getMechanismState() == MechanismStates.INTAKING){
          m_mechanismSubsystem.setState(MechanismStates.OFF);
          System.out.println("=======B BUTTON====INTAKING OFF=======");
        } else {
          m_mechanismSubsystem.setState(MechanismStates.INTAKING);
          System.out.println("=======B BUTTON====INTAKING ON=======");
        }
        
      }
      if(OI.codriver.getLeftBumper()){
        m_mechanismSubsystem.setState(MechanismStates.AMP_HOLDING);
        System.out.println("=======LEFT BUMPER====AMP HOLDING=======");
      }
      
      if(OI.codriver.getRightBumper()){
        m_mechanismSubsystem.setState(MechanismStates.SPEAKER_HOLDING);
        System.out.println("=======RIGHT BUMPER====SPEAKER HOLDING=======");
      }
  }
}