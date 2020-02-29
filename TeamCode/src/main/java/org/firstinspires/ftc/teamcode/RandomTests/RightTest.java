package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Testing Right")
public class RightTest extends AutonomousClass {
    AutoRobot robot;

    @Override
    public void init() {
        super.init();
        robot = new AutoRobot(hardwareMap, this);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
        telemetry.update();
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if(steps == 0) {
            double pow = robot.strafeRight(24, false);
            telemetry.addLine(pow + "");
            telemetry.update();
        }
    }

    @Override
    public void stop() {

    }
}

