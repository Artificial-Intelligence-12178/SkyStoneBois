package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "WackStoneFoundBlue")

public class TestingStoneFoundBlue extends AutonomousClass {

    AutoRobot robot;
    ElapsedTime timer;

    @Override
    public void init() {
        super.init();
        robot = new AutoRobot(hardwareMap, this);
        timer = new ElapsedTime();
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
            robot.strafeRight(39.5);
            if(steps == 1) {
                robot.intake.powerOn();
            }
        }
        else if(steps == 1) {
            robot.backward(6);
            if(steps == 2) {
                robot.intake.powerOff();
            }
        }
        else if(steps == 2) {
            robot.forward(6);
        }
        else if(steps == 3) {
            robot.strafeLeft(16.5);
        }
        else if(steps == 4) {
            robot.forward(78);
        }
        else if(steps == 5) {
            robot.rotateToHeading(-90);
        }
        else if(steps == 6) {
            robot.forward(9);
            if(steps == 2)
                timer.reset();
        }
        else if(steps == 7) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 600)
                steps++;
        }
        else if(steps == 8) {
            robot.backward(24);
        }
        else if(steps == 9) {
            // MAKE SURE ROTATION CAN TURN REAL FOUNDATION
            robot.rotateAboutPoint(0
                    , false);
        }
        else if(steps == 10) {
            robot.forward(9);
        }
        else if(steps == 11) {
            robot.grabbers.grabbersUp();
            steps++;
            timer.reset();
        }
        else if(steps == 12) {
            robot.backward(44);
        }
    }

    @Override
    public void stop() {

    }
}

