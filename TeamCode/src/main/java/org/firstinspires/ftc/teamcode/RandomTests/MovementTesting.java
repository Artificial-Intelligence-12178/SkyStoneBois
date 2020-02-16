package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Testing Movement")
public class MovementTesting extends AutonomousClass {

    AutoRobot robot;

    ElapsedTime timer;

    public void init() {
        robot = new AutoRobot(hardwareMap, this);
    }

    public void init_loop() {
        if(!robot.getStatus().equals("")) {
            telemetry.addData("Status", robot.getStatus());
        }

        telemetry.update();
    }

    public void start() {
        timer = new ElapsedTime();
        steps = 0;
    }

    public void loop() {
        robot.testingCorrection(.1);
        telemetry.addData("IMU", robot.imu.getHeading());
        telemetry.update();
    }

    public void stop() {

    }
}
