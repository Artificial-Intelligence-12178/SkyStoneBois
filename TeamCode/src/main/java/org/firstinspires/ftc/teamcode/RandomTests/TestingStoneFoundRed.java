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
            robot.strafeLeft(40.5);
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
            robot.strafeRight(15);
        }
        else if(steps == 3) {
            robot.forward(66);
        }
        else if(steps == 4) {
            robot.rotateToHeading(90);
        }
        else if(steps == 5) {
            robot.forward(10);
            if(steps == 6)
                timer.reset();
        }
        else if(steps == 6) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 600)
                steps++;
        }
        else if(steps == 7) {
            robot.backward(25);
            if(steps == 8) {
                robot.intake.goReverse();
                robot.intake.powerOn();
            }
        }
        else if(steps == 8) {
            // MAKE SURE ROTATION CAN TURN REAL FOUNDATION
            robot.rotateAboutPoint(0, false);
            if(steps == 9) {
                robot.intake.powerOff();
                robot.intake.goForward();
            }
        }
        else if(steps == 9) {
            robot.forward(9);
        }
        else if(steps == 10) {
            robot.grabbers.grabbersUp();
            steps++;
            timer.reset();
        }
        else if(steps == 11) {
            robot.backward(79.5);
        }
        else if(steps == 12) {
            robot.strafeLeft(14.75);
            if(steps == 13) {
                robot.intake.powerOn();
            }
        }
        else if(steps == 13) {
            robot.backward(6);
        }
        else if(steps == 14) {
            robot.strafeRight(12.5);
            if(steps == 15) {
                robot.intake.powerOff();
            }
        }
        else if(steps == 15) {
            robot.forward(67);
        }
        else if(steps == 16) {
            robot.backward(12);
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
