package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

@TeleOp (name = "No Robot Drive")
public class TestingDriveNoRobot extends OpMode {

    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    String status = "";

    @Override
    public void init() {
        //Initializing components
        try {
            frontLeft = hardwareMap.get(DcMotor.class, "DC1");
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = hardwareMap.get(DcMotor.class, "DC2");
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status+="\nFront Right motor (DC2) not mapping";
        }

        try {
            backRight = hardwareMap.get(DcMotor.class, "DC3");
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = hardwareMap.get(DcMotor.class, "DC4");
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status+="\nBack Left motor (DC4) not mapping";
        }
    }

    @Override
    public void init_loop() {
        if (!status.equals("")) {
            telemetry.addData("Status", status);
        }

        telemetry.update();
    }

    @Override
    public void start() {

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
            frontLeft.setPower(rTrig);
            frontRight.setPower(rTrig);
            backLeft.setPower(rTrig);
            backRight.setPower(rTrig);
            telemetry.addData("I am", "SPINNING");
        }
        else if(lTrig != 0)
        {
            frontLeft.setPower(-lTrig);
            frontRight.setPower(-lTrig);
            backLeft.setPower(-lTrig);
            backRight.setPower(-lTrig);
            telemetry.addData("I am", "ALSO SPINNING");
        }
        else
        {
            frontLeft.setPower(frontLPow);
            frontRight.setPower(frontRPow);
            backLeft.setPower(backLPow);
            backRight.setPower(backRPow);
        }

        telemetry.addData("Direction", motion);
    }

    @Override
    public void stop() {

    }
}
