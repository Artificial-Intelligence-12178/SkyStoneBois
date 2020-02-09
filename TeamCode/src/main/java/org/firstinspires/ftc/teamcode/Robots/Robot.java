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
import org.firstinspires.ftc.teamcode.Hardware.VerticalLift;

public class Robot {
    public static final double DIAMETER_OF_WHEELS = 3.93701;
    public static final double CIRCUMFERNCE_OF_WHEELS = DIAMETER_OF_WHEELS*Math.PI;
    public static final double TICKS_PER_REV = 753.2;
    public static final double TICKS_PER_INCH = TICKS_PER_REV/CIRCUMFERNCE_OF_WHEELS;
    public static final double INCHES_PER_TICK = 1/TICKS_PER_INCH;

    public DriveTrain driveTrain;

    public IMU imu;

    public FoundationGrabbers grabbers;

    public Intake intake;

    public VerticalLift verticalLift;

    protected String status = "";

    public Robot(HardwareMap map, DcMotorEx.RunMode mode){
        driveTrain = new DriveTrain(map, mode);

        imu = new IMU(map);

        grabbers = new FoundationGrabbers(map);

        intake = new Intake(map);

        verticalLift = new VerticalLift(map);

        status += driveTrain.getStatus() + intake.getStatus() + verticalLift.getStatus() + grabbers.getStatus();
    }

    public String getStatus(){
        return status;
    }

    public static int inchesToTicks(double inches){
        return (int)(TICKS_PER_INCH*inches);
    }

    public static double ticksToInches(int ticks) { return INCHES_PER_TICK*ticks; }

}
