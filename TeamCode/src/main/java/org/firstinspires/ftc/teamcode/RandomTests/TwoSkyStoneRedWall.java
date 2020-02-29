package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Autonomous.DetectionObject;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "2Stone RW")
public class TwoSkyStoneRedWall extends AutonomousClass {
    enum Position {
        LEFT,
        MIDDLE,
        RIGHT
    }

    AutoRobot robot;
    Position skyStone;
    DetectionObject camera;
    ElapsedTime intakeTimer;

    public void init() {
        super.init();
        robot = new AutoRobot(hardwareMap, this);
        camera = new DetectionObject(hardwareMap);
        intakeTimer = new ElapsedTime();
    }

    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
        telemetry.update();

        if(camera.getRightValue() == 0) {
            skyStone = Position.RIGHT;
        }
        else if(camera.getMiddleValue() == 0) {
            skyStone = Position.MIDDLE;
        }
        else if(camera.getLeftValue() == 0) {
            skyStone = Position.LEFT;
        }
    }

    public void start() {

    }

    public void loop() {
        if(skyStone.equals(Position.MIDDLE)) {  // MIDDLE CASE ======================================================================
            if(steps == 0) {
                robot.forward(8);
            }
            else if(steps == 1) {
                robot.strafeLeft(39.5, true);
                if(steps == 2)
                    robot.intake.powerOn();
            }
            else if(steps == 2) {
                robot.backward(6);
                if(steps == 3)
                    robot.intake.powerOff();
            }
            else if(steps == 3) {
                robot.forward(6);
            }
            else if(steps == 4) {
                robot.strafeRight(37, true);
            }
            else if(steps == 5) {
                robot.forward(44);
            }
            else if(steps == 6) {
                robot.rotateToHeading(-90);
                if(steps == 7) {
                    intakeTimer.reset();
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                }
            }
            else if(steps == 7) {
                if(intakeTimer.milliseconds() > 750) {
                    robot.intake.powerOff();
                    robot.intake.goForward();
                    steps++;
                }
            }
            else if(steps == 8) {
                robot.rotateToHeading(0);
            }
            else if(steps == 9) {
                robot.backward(60);
            }
            else if(steps == 10) {
                robot.strafeLeft(37, true);
                if(steps == 11) {
                    robot.intake.powerOn();
                }
            }
            else if(steps == 11) {
                robot.backward(6);
                if(steps == 12) {
                    robot.intake.powerOff();
                }
            }
            else if(steps == 12) {
                robot.forward(6);
            }
            else if(steps == 13) {
                robot.strafeRight(37, true);
            }
            else if(steps == 14) {
                robot.forward(60);
            }
            else if(steps == 15) {
                robot.backward(10);
            }
        }
        else if(skyStone.equals(Position.LEFT)) { // LEFT CASE ======================================================================
            if(steps == 0) {
                robot.strafeLeft(39.5, true);
                if(steps == 1)
                    robot.intake.powerOn();
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
                robot.strafeRight(37, true);
            }
            else if(steps == 4) {
                robot.forward(52);
            }
            else if(steps == 5) {
                robot.rotateToHeading(-90);
                if(steps == 6) {
                    intakeTimer.reset();
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                }
            }
            else if(steps == 6) {
                if(intakeTimer.milliseconds() > 750) {
                    robot.intake.powerOff();
                    robot.intake.goForward();
                    steps++;
                }
            }
            else if(steps == 7) {
                robot.backward(0);
            }
            else if(steps == 8) {
                robot.forward(68);
            }
            else if(steps == 9) {
                robot.strafeLeft(37, true);
                if(steps == 10) {
                    robot.intake.powerOn();
                }
            }
            else if(steps == 10) {
                robot.backward(6);
                if(steps == 11) {
                    robot.intake.powerOff();
                }
            }
            else if(steps == 11) {
                robot.forward(6);
            }
            else if(steps == 12) {
                robot.strafeRight(37, true);
            }
            else if(steps == 13) {
                robot.forward(68);
            }
            else if(steps == 14) {
                robot.backward(8);
            }
        }
        else { // RIGHT CASE ======================================================================
            if(steps == 0) {
                robot.forward(16);
            }
            else if(steps == 1) {
                robot.strafeLeft(27.5, true);
            }
            else if(steps == 2) {
                robot.rotateToHeading(-33.7);
                if(steps == 3)
                    robot.intake.powerOn();
            }
            else if(steps == 3) {
                robot.backward(8);
                if(steps == 4) {
                    robot.intake.powerOff();
                }
            }
            else if(steps == 4) {
                robot.forward(6);
            }
            else if(steps == 5) {
                robot.rotateToHeading(0);
            }
            else if(steps == 6) {
                robot.strafeRight(25, true);
            }
            else if(steps == 7) {
                robot.forward(36);
            }
            else if(steps == 8) {
                robot.rotateToHeading(-90);
                if(steps == 9) {
                    intakeTimer.reset();
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                }
            }
            else if(steps == 9) {
                if(intakeTimer.milliseconds() > 750) {
                    robot.intake.powerOff();
                    robot.intake.goForward();
                    steps++;
                }
            }
            else if(steps == 10) {
                robot.rotateToHeading(0);
            }
            else if(steps == 11) {
                robot.backward(52);
            }
            else if(steps == 12) {
                robot.strafeLeft(37, true);
                if(steps == 13) {
                    robot.intake.powerOn();
                }
            }
            else if(steps == 13) {
                robot.backward(6);
                if(steps == 14) {
                    robot.intake.powerOff();
                }
            }
            else if(steps == 14) {
                robot.forward(6);
            }
            else if(steps == 15) {
                robot.strafeRight(37, true);
            }
            else if(steps == 16) {
                robot.forward(44);
            }
            else if(steps == 17) {
                robot.backward(18);
            }
        }
    }

    public void stop() {

    }
}
