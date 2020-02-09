package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.IMU;
import org.firstinspires.ftc.teamcode.Robots.DriveRobot;
import org.opencv.core.Mat;


@TeleOp (name = "Testing New Drivetrain")
public class TestingDriving extends OpMode {

    DriveRobot joe;

    @Override
    public void init_loop() {
        telemetry.addData("Status", joe.getStatus());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void init() {
        joe = new DriveRobot(hardwareMap);
    }

    @Override
    public void loop() {
        //Values of left joystick
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        //Trigger values
        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        //Getting the angle (in radians)
        double angleRad = Math.abs(Math.atan(y / x));
        double degree;

        //Finding the reference angle
        if(x < 0 && y > 0)  //quad 2
            angleRad= Math.PI - angleRad;
        else if(x < 0 && y <= 0)  //quad 3
            angleRad = Math.PI + angleRad;
        else if(x >= 0 && y < 0)  //quad 4
            angleRad = 2*Math.PI - angleRad;

        //Determining power
        double vec = Math.sqrt(x*x+y*y);
        if(vec > 1)
            vec = 1;
        double frontLPow, frontRPow, backLPow, backRPow;

        //Displaying information
        degree = Math.toDegrees(angleRad);
        String motion = "";

        /**
         * DETERMINING THE ANGLES FOR MOVEMENT
         * Angles have a threshold of 5 degrees
         */
        if(degree >= 265 && degree <= 275) {        //Forward
            angleRad = Math.toRadians(270);
            motion = "Forward";
        }
        else if(degree >= 85 && degree <= 95) {     //Backward
            angleRad = Math.toRadians(90);
            motion = "Backwards";
        }
        else if(degree >= 355 || degree <= 5) {     //Right
            angleRad = Math.toRadians(0);
            motion = "Right";
        }
        else if(degree >= 175 && degree <= 185) {   //Left
            angleRad = Math.toRadians(180);
            motion = "Left";
        }
        else if(degree >= 310 && degree <= 320) {   //Northeast
            angleRad = Math.toRadians(315);
            motion = "Northeast";
        }
        else if(degree >= 220 && degree <= 230) {   //Northwest
            angleRad = Math.toRadians(225);
            motion = "Northwest";
        }
        else if(degree >= 40 && degree <= 50) {     //Southeast
            angleRad = Math.toRadians(45);
            motion = "Southeast";
        }
        else if(degree >= 130 && degree <= 140) {   //Southwest
            angleRad = Math.toRadians(135);
            motion = "Southwest";
        }

        degree = Math.toDegrees(angleRad);
        //imu translation
        angleRad = Math.toRadians(degree);

        frontRPow = vec*Math.sin(angleRad+Math.PI/4);
        frontLPow = -1*vec*Math.sin(angleRad-Math.PI/4);
        backRPow = vec*Math.sin(angleRad-Math.PI/4);
        backLPow = -1*Math.sin(angleRad+Math.PI/4);

        if(Math.abs(frontLPow) < 0.00005)
            frontLPow = 0;
        if(Math.abs(frontRPow) < 0.00005)
            frontRPow = 0;
        if(Math.abs(backRPow) < 0.00005)
            backRPow = 0;
        if(Math.abs(backLPow) < 0.00005)
            backLPow = 0;

        if(rTrig != 0)
        {
            joe.driveTrain.applyPower(rTrig, rTrig, rTrig, rTrig);
        }
        else if(lTrig != 0)
        {
            joe.driveTrain.applyPower(-lTrig, -lTrig, -lTrig, -lTrig);
        }
        else
        {
            joe.driveTrain.applyPower(frontLPow, frontRPow, backLPow, backRPow);
        }

        telemetry.addData("Direction", motion);
    }
}
