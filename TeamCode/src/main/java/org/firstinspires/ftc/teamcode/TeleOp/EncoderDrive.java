package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

@TeleOp (name = "Encoder Drive")
public class EncoderDrive extends OpMode {
    DriveRobot robot;

    @Override
    public void init() {
        robot = new DriveRobot(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.left_trigger > 0) {
            robot.driveTrain.applyPower(-gamepad1.left_trigger);
            telemetry.addLine("Rotate Left");
        }
        else if(gamepad1.right_trigger > 0) {
            robot.driveTrain.applyPower(gamepad1.right_trigger);
            telemetry.addLine("Rotate Right");
        }
        else if(gamepad1.dpad_up) {
            robot.driveTrain.applyPower(.2, -.2, .2, -.2);
            telemetry.addLine("Forward");
        }
        else if(gamepad1.dpad_down) {
            robot.driveTrain.applyPower(-.2, .2, -.2, .2);
            telemetry.addLine("Backward");
        }
        else if(gamepad1.dpad_left) {
            robot.driveTrain.applyPower(-.2, -.2, .2, .2);
            telemetry.addLine("Left");
        }
        else if(gamepad1.dpad_right) {
            robot.driveTrain.applyPower(.2, .2, -.2, -.2);
            telemetry.addLine("Right");
        }
        else {
            robot.driveTrain.applyPower(0);
            telemetry.addLine("Idle");
        }

        if(gamepad1.a) {
            robot.driveTrain.resetEncoders();
        }

        double encoderCount = robot.driveTrain.getAverageEncoderValue();

        telemetry.addData("Encoder Count:", encoderCount);

        telemetry.update();
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }
}
