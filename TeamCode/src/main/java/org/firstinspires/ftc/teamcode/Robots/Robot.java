package org.firstinspires.ftc.teamcode.Robots;

import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;

public class Robot {
    public final DcMotorEx.Direction REVERSE = DcMotorEx.Direction.REVERSE;
    public final DcMotorEx.Direction FORWARD = DcMotorEx.Direction.FORWARD;

    public final double DIAMETER_OF_WHEELS = 3.93701;
    public final double CIRCUMFERNCE_OF_WHEELS = DIAMETER_OF_WHEELS*Math.PI;

    public DcMotorEx frontLeft;
    public DcMotorEx frontRight;
    public DcMotorEx backRight;
    public DcMotorEx backLeft;

    public BNO055IMU imu;

    protected String status = "";

    public Robot(HardwareMap map, DcMotorEx.RunMode mode){
        DriveTrain driveTrain = new DriveTrain(map, mode);

    }

    public String getStatus(){
        return status;
    }
}
