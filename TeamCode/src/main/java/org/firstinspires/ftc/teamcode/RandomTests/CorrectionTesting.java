package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Testing Correction")
public class CorrectionTesting extends AutonomousClass {
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
        robot.correctionDrive(0.2, .01);
        telemetry.update();
    }

    @Override
    public void stop() {

    }
}
