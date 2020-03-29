/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc4388.robot;

import com.ctre.phoenix.motorcontrol.InvertType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import com.ctre.phoenix.sensors.PigeonIMU;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc4388.robot.Constants.DriveConstants;
import frc4388.robot.Constants.LEDConstants;
import frc4388.utility.RobotGyro;

/**
 * Defines and holds all I/O objects on the Roborio. This is useful for unit
 * testing and modularization.
 */
public class RobotMap {

    public RobotMap() {
        configureLEDSubsystem();
        configureDriveSubsystem();
    }

    /* LED Subsystem */
    public final Spark LEDController = new Spark(LEDConstants.LED_SPARK_ID);

    void configureLEDSubsystem() {
        
    }

    /* Drive Subsystem */
    public final WPI_TalonSRX leftFrontMotor = new WPI_TalonSRX(DriveConstants.DRIVE_LEFT_FRONT_CAN_ID);
    public final WPI_TalonSRX rightFrontMotor = new WPI_TalonSRX(DriveConstants.DRIVE_RIGHT_FRONT_CAN_ID);
    public final WPI_TalonSRX leftBackMotor = new WPI_TalonSRX(DriveConstants.DRIVE_LEFT_BACK_CAN_ID);
    public final WPI_TalonSRX rightBackMotor = new WPI_TalonSRX(DriveConstants.DRIVE_RIGHT_BACK_CAN_ID);
    public final DifferentialDrive driveTrain = new DifferentialDrive(leftFrontMotor, rightFrontMotor);
    public final RobotGyro gyroDrive = new RobotGyro(new PigeonIMU(DriveConstants.DRIVE_PIGEON_ID));

    void configureDriveSubsystem() {

        /* factory default values */
        leftFrontMotor.configFactoryDefault();
        rightFrontMotor.configFactoryDefault();
        leftBackMotor.configFactoryDefault();
        rightBackMotor.configFactoryDefault();

        /* set back motors as followers */
        leftBackMotor.follow(leftFrontMotor);
        rightBackMotor.follow(rightFrontMotor);

        /* set neutral mode */
        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);
        leftFrontMotor.setNeutralMode(NeutralMode.Brake);
        rightFrontMotor.setNeutralMode(NeutralMode.Brake);

        /* flip input so forward becomes back, etc */
        leftFrontMotor.setInverted(false);
        rightFrontMotor.setInverted(false);
        leftBackMotor.setInverted(InvertType.FollowMaster);
        rightBackMotor.setInverted(InvertType.FollowMaster);
    }
}
