package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

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
        try {
            frontLeft = map.get(DcMotorEx.class, "DC1");
            frontLeft.setMode(mode);
            frontLeft.setDirection(FORWARD);
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = map.get(DcMotorEx.class, "DC2");
            frontRight.setMode(mode);
            frontRight.setDirection(FORWARD);
        } catch (Exception e) {
            status+="\nFront Right motor (DC2) not mapping";
        }

        try {
            backRight = map.get(DcMotorEx.class, "DC3");
            backRight.setMode(mode);
            backRight.setDirection(FORWARD);
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = map.get(DcMotorEx.class, "DC4");
            backLeft.setMode(mode);
            backRight.setDirection(FORWARD);
        } catch (Exception e) {
            status+="\nBack Left motor (DC4) not mapping";
        }

        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = map.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
    }

    public String getStatus(){
        return status;
    }
}
