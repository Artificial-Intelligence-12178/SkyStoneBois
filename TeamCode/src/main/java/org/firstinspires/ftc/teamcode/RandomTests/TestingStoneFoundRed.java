package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "WackStoneFoundRed")

public class TestingStoneFoundRed extends AutonomousClass {

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
        telemetry.addData("Step", steps);
        if(steps == 0) {
            robot.strafeLeft(40);
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
            robot.strafeRight(14.5);
        }
        else if(steps == 4) {
            robot.forward(78);
        }
        else if(steps == 5) {
            robot.rotateToHeading(90);
        }
        else if(steps == 6) {
            robot.forward(9);
            if(steps == 7)
                timer.reset();
        }
        else if(steps == 7) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 600)
                steps++;
        }
        else if(steps == 8) {
            robot.backward(22);
        }
        else if(steps == 9) {
            // MAKE SURE ROTATION CAN TURN REAL FOUNDATION
            robot.rotateAboutPoint(0, false);
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
        telemetry.update();

        /*
        if(steps == 0) {
            if(skyStone.equals(Position.MIDDLE)) {
                robot.forward(8);
            }
            else if(skyStone.equals(Position.RIGHT)) {
                robot.forward(16);
            }
            else {
                steps++;
            }
        }
        else if(steps == 1) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.strafeLeft(27.5, true);
            } else {
                robot.strafeLeft(39.5, true);
                if(steps == 2)
                    robot.intake.powerOn();
            }
        }
        else if(steps == 2) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.rotateToHeading(-33.7);
                if(steps == 3)
                    robot.intake.powerOn();
            } else {
                robot.backward(6);
                if(steps == 3)
                    robot.intake.powerOff();
            }
        }
        else if(steps == 3) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.backward(8);
                if(steps == 4)
                    robot.intake.powerOff();
            } else {
                robot.forward(6);
            }
        }
        else if(steps == 4) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.forward(8);
            } else {
                robot.strafeRight(14.5, true);
            }
        }
        else if(steps == 5) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.rotateToHeading(0);
            } else {
                if(skyStone.equals(Position.MIDDLE))
                    robot.forward(44);
                else if(skyStone.equals(Position.LEFT))
                    robot.forward(52);
            }
        }
        else if(steps == 6) {
            if(skyStone.equals(Position.RIGHT))
                robot.strafeRight(3, true);
            else
                robot.backward(18);
        }
        else if(steps == 7) {
            if(skyStone.equals(Position.RIGHT))
                robot.forward(36);
        }
        else if(skyStone.equals(Position.RIGHT)) {
            robot.backward(18);
        }*/
    }

    @Override
    public void stop() {

    }
}
