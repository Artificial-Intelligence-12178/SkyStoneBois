package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.firstinspires.ftc.teamcode.Robots.Robot;

@Autonomous (name = "Testing Rotation")
public class TestingRotation extends AutonomousClass {
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
        telemetry.addLine(robot.imu.getHeading() + "");
        if(steps == 0) {
            //robot.rotateToHeading(90);
            robot.arcLeftToHeading(90, 12, 20, false);
        }
    }

    @Override
    public void stop() {

    }
}

