package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

import java.util.Arrays;

@TeleOp (name = "SampleTele")
public class SampleTeleOp extends OpMode {
    DriveRobot robot;
    Servo right;
    Servo left;

    @Override
    public void init_loop() {
        if(!robot.getStatus().equals(""))
            telemetry.addData("Status", robot.getStatus());

        telemetry.update();
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void init() {
        robot = new DriveRobot(hardwareMap);
        right = hardwareMap.get(Servo.class, "ServoR");
        left = hardwareMap.get(Servo.class, "ServoL");
    }

    @Override
    public void loop() {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        double degree = Math.toDegrees(Math.atan2(y, x));
        if(degree < 0) {
            degree+=360;
        }

        String motion = "";
        if(degree >= 265 && degree <= 275) {        //Forward
            degree = 270;
            motion = "Forward";
        }
        else if(degree >= 85 && degree <= 95) {     //Backward
            degree = 90;
            motion = "Backwards";
        }
        else if(degree >= 355 || degree <= 5) {     //Right
            degree = 0;
            motion = "Right";
        }
        else if(degree >= 175 && degree <= 185) {   //Left
            degree = 180;
            motion = "Left";
        }
        else if(degree >= 310 && degree <= 320) {   //Northeast
            degree = 315;
            motion = "Northeast";
        }
        else if(degree >= 220 && degree <= 230) {   //Northwest
            degree = 225;
            motion = "Northwest";
        }
        else if(degree >= 40 && degree <= 50) {     //Southeast
            degree = 45;
            motion = "Southeast";
        }
        else if(degree >= 130 && degree <= 140) {   //Southwest
            degree = 135;
            motion = "Southwest";
        }

        double radian = Math.toRadians(degree);
        telemetry.addData("Degree", degree);


        double fLPower;
        double fRPower;
        double bLPower;
        double bRPower;

        double vec = Math.hypot(x, y);
        if(vec > 1)
            vec = 1;

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        fLPower = -1 * vec * Math.sin(radian - Math.PI/4) + rTrig - lTrig;
        fRPower = vec * Math.sin(radian + Math.PI/4) + rTrig - lTrig;
        bLPower = -1 * vec * Math.sin(radian + Math.PI/4) + rTrig - lTrig;
        bRPower = vec * Math.sin(radian - Math.PI/4) + rTrig - lTrig;

        robot.driveTrain.applyPower(fLPower, fRPower, bLPower, bRPower);

        if(!motion.equals("")) {
            telemetry.addData("Motion", motion);
        }
        telemetry.update();

        if(gamepad1.a) {
            robot.intake.goForward();
            robot.intake.powerOn();
        }
        else if(gamepad1.b) {
            robot.intake.goReverse();
            robot.intake.powerOn();
        }
        else {
            robot.intake.powerOff();
        }

        if(gamepad1.dpad_up)
            robot.verticalLift.liftUp();
        else if(gamepad1.dpad_down)
            robot.verticalLift.liftDown();
        else
            robot.verticalLift.stopLift();

        if(gamepad1.x) {
            robot.grabbers.grabbersDown();
        } else {
            robot.grabbers.grabbersUp();
        }

        if(gamepad1.dpad_left) {
            robot.horizontalSlide.extendSlide();
        } else if ( gamepad1.dpad_right) {
            robot.horizontalSlide.retractSlide();
        } else {
            robot.horizontalSlide.maintainSlide();
        }

        if(gamepad1.right_bumper) {
            robot.horizontalSlide.grabStone();
        } else if(gamepad1.left_bumper) {
            robot.horizontalSlide.releaseStone();
        }

        robot.verticalLift.applyPower(gamepad1.right_stick_y);

        if(gamepad1.y) {
            robot.capstone.releaseStone();
        }
    }
}
