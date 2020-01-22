package org.firstinspires.ftc.teamcode.Robots;

import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Hardware.DriveTrain;
import org.firstinspires.ftc.teamcode.Hardware.FoundationGrabbers;
import org.firstinspires.ftc.teamcode.Hardware.IMU;
import org.firstinspires.ftc.teamcode.Hardware.Intake;

public class Robot {
    public final double DIAMETER_OF_WHEELS = 3.93701;
    public final double CIRCUMFERNCE_OF_WHEELS = DIAMETER_OF_WHEELS*Math.PI;
    public final int TICKS_PER_INCH = 1;

    public DriveTrain driveTrain;

    public IMU imu;

    public FoundationGrabbers grabbers;

    public Intake intake;

    protected String status = "";

    public Robot(HardwareMap map, DcMotorEx.RunMode mode){
        driveTrain = new DriveTrain(map, mode);

        imu = new IMU(map);

        grabbers = new FoundationGrabbers(map);

        intake = new Intake(map);

        status += driveTrain.getStatus() + grabbers.getStatus() + intake.getStatus();
    }

    public String getStatus(){
        return status;
    }

    public int inchesToTicks(double inches){
        return (int)(TICKS_PER_INCH*inches);
    }
}
