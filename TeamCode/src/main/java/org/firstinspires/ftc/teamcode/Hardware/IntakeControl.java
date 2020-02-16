package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

@TeleOp (name = "Intake Control")
public class IntakeControl extends OpMode {

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
        double rTrig = gamepad1.right_trigger;

        robot.intake.applyPower(-rTrig, rTrig);

        if(gamepad1.a)
            robot.intake.goReverse();
        else if(gamepad1.b)
            robot.intake.goForward();

        telemetry.addData("Power", rTrig);
        telemetry.update();
    }
}
