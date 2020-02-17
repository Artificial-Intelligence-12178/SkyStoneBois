package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "FoundStone RW")
public class FoundSkyRedWall extends AutonomousClass {
    enum Position {
        LEFT,
        MIDDLE,
        RIGHT
    }

    Position skyStone = Position.LEFT;
    DetectionObject camera;
    AutoRobot robot;
    ElapsedTime intakeTimer, grabTimer;

    @Override
    public void init() {
        super.init();
        camera = new DetectionObject(hardwareMap);
        robot = new AutoRobot(hardwareMap, this);
        intakeTimer = new ElapsedTime();
        grabTimer = new ElapsedTime();
    }

    @Override
    public void init_loop() {
        if(!robot.getStatus().equals("")) {
            telemetry.addData("Status", robot.getStatus());
            telemetry.update();
        }

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

    @Override
    public void start() {
        camera.stopDetection();
    }

    @Override
    public void loop() {
        if(steps == 0) {
            if(skyStone.equals(Position.MIDDLE)) {
                robot.forward(8);
            }
            else if(skyStone.equals(Position.LEFT)) {
                robot.forward(16);
            }
            else {
                steps++;
            }
        }
        else if(steps == 1) {
            if(skyStone.equals(Position.LEFT)) {
                robot.strafeRight(27.5, true);
            } else {
                robot.strafeRight(39.5, true);
                if(steps == 2)
                    robot.intake.powerOn();
            }
        }
        else if(steps == 2) {
            if(skyStone.equals(Position.LEFT)) {
                robot.rotateToHeading(33.7);
                if(steps == 3)
                    robot.intake.powerOn();
            } else {
                robot.backward(6);
                if (steps == 3)
                    robot.intake.powerOff();
            }
        }
        else if(steps == 3) {
            if(skyStone.equals(Position.LEFT)) {
                robot.backward(8);
                if(steps == 4)
                    robot.intake.powerOff();
            } else {
                robot.forward(6);
            }
        }
        else if(steps == 4) {
            if(skyStone.equals(Position.LEFT)) {
                robot.forward(8);
            } else {
                robot.strafeLeft(16.5, true);
            }
        }
        else if(steps == 5) {
            if(skyStone.equals(Position.LEFT)) {
                robot.rotateToHeading(0);
            } else {
                if(skyStone.equals(Position.MIDDLE))
                    robot.forward(56);
                else if(skyStone.equals(Position.RIGHT))
                    robot.forward(64);
            }
        }
        else if(steps == 6) {
            if(skyStone.equals(Position.LEFT))
                robot.strafeLeft(5, true);
            else {
                robot.rotateToHeading(90);
                if(steps == 7) {
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                }
            }
        }
        else if(steps == 7) {
            if(skyStone.equals(Position.LEFT))
                robot.forward(48);
            else {
                robot.forward(14);
                if(steps == 8)
                    grabTimer.reset();
            }
        }
        else if(steps == 8) {
            if(skyStone.equals(Position.LEFT)) {
                robot.rotateToHeading(90);
                if(steps == 9) {
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                }
            }
            else {
                robot.grabbers.grabbersDown();
                if(grabTimer.milliseconds() > 500)
                    steps++;
            }
        }
        else if(steps == 9) {
            if(skyStone.equals(Position.LEFT)) {
                robot.forward(14);
                grabTimer.reset();
            }
            else
                robot.backward(30);
        }
        else if(steps == 10) {
            if(skyStone.equals(Position.LEFT)) {
                robot.grabbers.grabbersDown();
                if(grabTimer.milliseconds() > 500)
                    steps++;
            }
            else
                robot.rotateToHeading(0);
        }
        else if(steps == 11) {
            if(skyStone.equals(Position.LEFT))
                robot.backward(30);
            else {
                robot.grabbers.grabbersUp();
                steps++;
            }
        }

    }

    @Override
    public void stop() {

    }
}
