package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Testing RotationBlue")
public class TestingFoundBlue extends AutonomousClass {

    AutoRobot robot;
    ElapsedTime timer;
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
        timer = new ElapsedTime();
    }

    @Override
    public void loop() {
        telemetry.addData("Step", steps);
        telemetry.update();

        if(steps == 0) {
            // GET REAL STRAFE VALUE
            robot.strafeLeft(6);
        }
        else if(steps == 1) {
            robot.forward(34);
            if(steps == 2)
                timer.reset();
        }
        else if(steps == 2) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 600)
                steps++;
        }
        else if(steps == 3) {
            robot.backward(22);
        }
        else if(steps == 4) {
            // MAKE SURE ROTATION CAN TURN REAL FOUNDATION
            robot.rotateAboutPoint(90, false);
        }
        else if(steps == 5) {
            robot.forward(9);
        }
        else if(steps == 6) {
            robot.grabbers.grabbersUp();
            steps++;
            timer.reset();
        }
        else if(steps == 7) {
            robot.backward(44);
        }
    }

    @Override
    public void stop() {

    }
}
