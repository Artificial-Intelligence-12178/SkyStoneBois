package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

import java.lang.reflect.Array;
import java.util.Arrays;

@TeleOp (name = "SimpleMovement")
public class SimpleTeleMovement extends OpMode {
    DriveRobot robot;

    @Override
    public void init_loop() {
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
        /*if(gamepad1.left_trigger > 0) {
            robot.driveTrain.applyPower(-gamepad1.left_trigger);
            telemetry.addLine("Rotate Left");
        }
        else if(gamepad1.right_trigger > 0) {
            robot.driveTrain.applyPower(gamepad1.right_trigger);
            telemetry.addLine("Rotate Right");
        }
        else if(gamepad1.dpad_up) {
            robot.driveTrain.applyPower(.4, -.4, .4, -.4);
            telemetry.addLine("Forward");
        }
        else if(gamepad1.dpad_down) {

            robot.driveTrain.applyPower(-.4, .4, -.4, .4);
            telemetry.addLine("Backward");
        }
        else if(gamepad1.dpad_left) {
            robot.driveTrain.applyPower(-.4, -.4, .4, .4);
            telemetry.addLine("Left");
        }
        else if(gamepad1.dpad_right) {
            robot.driveTrain.applyPower(.4, .4, -.4, -.4);
            telemetry.addLine("Right");
        }
        else {
            robot.driveTrain.applyPower(0);
            telemetry.addLine("Idle");
        }*/

        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rightX = gamepad1.right_stick_x;

        double degree = Math.toDegrees(Math.atan2(y, x));
        if(degree < 0) {
            degree+=360;
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

        fLPower = -1 * vec * Math.sin(radian - Math.PI/4) + rightX;
        fRPower = vec * Math.sin(radian + Math.PI/4) + rightX;
        bLPower = -1 * vec * Math.sin(radian + Math.PI/4) + rightX;
        bRPower = vec * Math.sin(radian - Math.PI/4) + rightX;

        double[] powers = {Math.abs(fLPower), Math.abs(fRPower), Math.abs(bLPower), Math.abs(bRPower)};
        Arrays.sort(powers);
        if(powers[3] > 1) {
            fLPower/=powers[3];
            fRPower/=powers[3];
            bLPower/=powers[3];
            bRPower/=powers[3];
        }

        robot.driveTrain.applyPower(fLPower, fRPower, bLPower, bRPower);
        telemetry.update();
    }
}
