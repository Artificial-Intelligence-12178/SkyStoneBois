package org.firstinspires.ftc.teamcode.RandomTests;
import com.acmerobotics.roadrunner.drive.Drive;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

public class FieldCentric extends OpMode {

    DriveRobot robot;
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
        double heading = robot.imu.getHeading();
        telemetry.addData("Degree", degree);


        double fLPower;
        double fRPower;
        double bLPower;
        double bRPower;

        double vec = Math.hypot(x, y);
        if(vec > 1)
            vec = 1;

        fLPower = -1 * vec * Math.sin(radian - Math.PI/4);
        fRPower = vec * Math.sin(radian + Math.PI/4);
        bLPower = -1 * vec * Math.sin(radian + Math.PI/4);
        bRPower = vec * Math.sin(radian - Math.PI/4);

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        if(rTrig > 0) {
            robot.driveTrain.applyPower(rTrig, rTrig, rTrig, rTrig);
            motion = "Spin Right";
        }
        else if(lTrig > 0) {
            robot.driveTrain.applyPower(-lTrig, -lTrig, -lTrig, -lTrig);
            motion = "Spin Left";
        }
        else {
            robot.driveTrain.applyPower(fLPower, fRPower, bLPower, bRPower);
        }
    }
}