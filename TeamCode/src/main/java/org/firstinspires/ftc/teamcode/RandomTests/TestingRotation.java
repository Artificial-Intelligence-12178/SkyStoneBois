package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Testing Rotation")
public class TestingRotation extends AutonomousClass {

    AutoRobot robot;
    ElapsedTime timer;
    public void init() {
        robot = new AutoRobot(hardwareMap, this);
        robot.grabbers.grabbersDown();
    }

    public void init_loop() {
        if (!robot.getStatus().equals(""))
            telemetry.addData("Status", robot.getStatus());

        telemetry.addData("Heading", robot.imu.getHeading());
        telemetry.update();
    }

    public void start() {
        timer = new ElapsedTime();
        timer.reset();
        steps = 0;
    }

    public void loop() {
        telemetry.update();

        if(steps == 0) {
            robot.rotateToHeading(180);
            if(steps == 1) {
                timer.reset();
            }
        }
        else if(steps == 1) {
            if(timer.seconds() > 5)
                steps++;
        }
        else if(steps == 2) {
            robot.rotateToHeading(0);
            if(steps == 3) {
                timer.reset();
            }
        }
        else if(steps == 3) {
            if(timer.seconds() > 5) {
                steps = 0;
            }
        }
        else if(steps == 4) {
            robot.rotateToHeading(-90);
            if(steps == 5) {
                timer.reset();
            }
        }
        else if(steps == 5) {
            if(timer.seconds() > 2) {
                steps++;
            }
        }
        else if(steps == 6) {
            robot.rotateToHeading(0);
            if(steps == 7) {
                timer.reset();
            }
        }
        else if(steps == 7) {
            if(timer.seconds() > 2) {
                steps = 0;
            }
        }

        telemetry.addData("Heading", robot.imu.getHeading());
    }

    public void stop() {

    }
}
