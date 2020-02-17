package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.PIXEL_FORMAT;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "2 SkyStone RW")
public class TwoSkyStoneRedWall extends AutonomousClass {
    enum Position {
        LEFT,
        MIDDLE,
        RIGHT
    }

    Position skyStone = Position.LEFT;
    DetectionObject camera;
    AutoRobot robot;
    ElapsedTime intakeTimer;

    @Override
    public void init() {
        super.init();
        camera = new DetectionObject(hardwareMap);
        robot = new AutoRobot(hardwareMap, this);
        intakeTimer = new ElapsedTime();
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
            /*robot.strafeRight(39.5, true);
            if(steps == 2)
                robot.intake.powerOn();*/
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
            /*robot.backward(6);
            if(steps == 3)
                robot.intake.powerOff();*/
        }
        else if(steps == 3) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.backward(8);
                if(steps == 4)
                    robot.intake.powerOff();
            } else {
                robot.forward(6);
            }
            // robot.forward(6);
        }
        else if(steps == 4) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.forward(8);
            } else {
                robot.strafeRight(37, true);
            }

            //robot.strafeLeft(16.5, true);
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
            /*if(skyStone.equals(Position.MIDDLE))
                robot.forward(44);
            else if(skyStone.equals(Position.LEFT))
                robot.forward(36);
            else if(skyStone.equals(Position.RIGHT))
                robot.forward(52);*/
        }
        else if(steps == 6) {
            if(skyStone.equals(Position.RIGHT))
                robot.strafeRight(25, true);
            else {
                robot.rotateToHeading(-90);
                if(steps == 7) {
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                    intakeTimer.reset();
                }
            }
        }
        else if(steps == 7) {
            if(skyStone.equals(Position.RIGHT))
                robot.forward(36);
            else {
                if(intakeTimer.seconds() > 1) {
                    robot.intake.powerOff();
                    robot.intake.goForward();
                    steps++;
                }
            }
        }
        else if(steps == 8) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.rotateToHeading(-90);
                if(steps == 9) {
                    robot.intake.goReverse();
                    robot.intake.powerOn();
                    intakeTimer.reset();
                }
            }
            else {
                robot.rotateToHeading(0);
            }
        }
        else if(steps == 9) {
            if(skyStone.equals(Position.RIGHT)) {
                if(intakeTimer.seconds() > 1) {
                    robot.intake.powerOff();
                    robot.intake.goForward();
                    steps++;
                }
            }
            else {
                if(skyStone.equals(Position.LEFT))
                    robot.backward(68);
                else if(skyStone.equals(Position.MIDDLE))
                    robot.backward(60);
            }
        }
        else if(steps == 10) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.rotateToHeading(0);
            }
            else {
                robot.strafeLeft(37, true);
                if(steps == 11) {
                    robot.intake.powerOn();
                }
            }
        }
        else if(steps == 11) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.backward(52);
            }
            else {
                robot.backward(6);
                if(steps == 12) {
                    robot.intake.powerOff();
                }
            }
        }
        else if(steps == 12) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.strafeLeft(37, true);
                if(steps == 13)
                    robot.intake.powerOn();
            }
            else {
                robot.strafeLeft(25, false);
            }
        }
        else if(steps == 13) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.backward(6);
                if(steps == 14)
                    robot.intake.powerOff();
            }
            else {
                if(skyStone.equals(Position.LEFT))
                    robot.forward(68);
                else if(skyStone.equals(Position.MIDDLE))
                    robot.forward(60);
            }
        }
        else if(steps == 14) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.strafeLeft(25, false);
            }
            else {
                robot.backward(14);
            }
        }
        else if(steps == 15) {
            if(skyStone.equals(Position.RIGHT)) {
                robot.forward(52);
            }
        }
        else if(steps == 16) {
            robot.backward(14);
        }
    }

    @Override
    public void stop() {

    }
}
