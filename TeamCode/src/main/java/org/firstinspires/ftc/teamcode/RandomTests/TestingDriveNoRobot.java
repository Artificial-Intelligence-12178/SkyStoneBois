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

    DcMotor intakeLeft;
    DcMotor intakeRight;

    String status = "";

    @Override
    public void init() {
        //Initializing components
        try {
            frontLeft = hardwareMap.get(DcMotor.class, "DC1");
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = hardwareMap.get(DcMotor.class, "DC4");
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nFront Right motor (DC2) not mapping";
        }

        try {
            backRight = hardwareMap.get(DcMotor.class, "DC3");
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = hardwareMap.get(DcMotor.class, "DC2");
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nBack Left motor (DC4) not mapping";
        }

        try {
            intakeLeft = hardwareMap.get(DcMotor.class, "IL");
            intakeLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nIntake Left (IL) not mapping";
        }

        try {
            intakeRight = hardwareMap.get(DcMotor.class, "IR");
            intakeRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nIntake Right (IR) not mapping";
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
        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        String motion;

        if(rTrig != 0)
        {
            frontLeft.setPower(rTrig);
            frontRight.setPower(rTrig);
            backLeft.setPower(rTrig);
            backRight.setPower(rTrig);
            motion = "SPIN RIGHT";
        }
        else if(lTrig != 0)
        {
            frontLeft.setPower(-lTrig);
            frontRight.setPower(-lTrig);
            backLeft.setPower(-lTrig);
            backRight.setPower(-lTrig);
            motion = "SPIN LEFT";
        }
        else if(gamepad1.dpad_up)
        {
            frontLeft.setPower(.5);
            frontRight.setPower(-.5);
            backLeft.setPower(.5);
            backRight.setPower(-.5);
            motion = "FORWARD";
        }
        else if(gamepad1.dpad_down)
        {
            frontLeft.setPower(-.5);
            frontRight.setPower(.5);
            backLeft.setPower(-.5);
            backRight.setPower(.5);
            motion = "BACKWARD";
        }
        else if(gamepad1.dpad_left)
        {
            frontLeft.setPower(-.5);
            frontRight.setPower(-.5);
            backLeft.setPower(.5);
            backRight.setPower(.5);
            motion = "LEFT";
        }
        else if(gamepad1.dpad_right)
        {
            frontLeft.setPower(.5);
            frontRight.setPower(.5);
            backLeft.setPower(-.5);
            backRight.setPower(-.5);
            motion = "RIGHT";
        }
        else
        {
            frontLeft.setPower(0);
            frontRight.setPower(0);
            backLeft.setPower(0);
            backRight.setPower(0);
            motion = "IDLE";
        }

        if(gamepad1.a){
            intakeRight.setPower(-.7);
            intakeLeft.setPower(.7);
        }
        else if(gamepad1.b){
            intakeLeft.setPower(-.7);
            intakeRight.setPower(.7);
        }
        else
        {
            intakeRight.setPower(0);
            intakeLeft.setPower(0);

        }
        telemetry.addData("Direction", motion);
    }

    @Override
    public void stop() {

    }
}
