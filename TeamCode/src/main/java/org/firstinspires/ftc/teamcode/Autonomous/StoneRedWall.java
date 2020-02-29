package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name =  "SkyStone RW")
public class StoneRedWall extends AutonomousClass {
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

    @Override
    public void start() {
        camera.stopDetection();
    }

    @Override
    public void loop() {
        if(skyStone.equals(Position.MIDDLE)) {
            if(steps == 0) {
                robot.forward(8);
            }
            else if(steps == 1) {
                robot.strafeLeft(39.5, true);
                if(steps == 2) {
                    robot.intake.powerOn();
                }
            }
            else if(steps == 2) {
                robot.backward(6);
                if(steps == 3) {
                    robot.intake.powerOff();
                }
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
                robot.backward(18);
            }
        }
        else if(skyStone.equals(Position.LEFT)) {
            if(steps == 0) {
                robot.strafeLeft(39.5, true);
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
                robot.strafeRight(37, true);
            }
            else if(steps == 4) {
                robot.forward(44);
            }
            else if(steps == 5) {
                robot.backward(18);
            }
        }
        else if(skyStone.equals(Position.RIGHT)) {
            if(steps == 0) {
                robot.forward(16);
            }
            else if(steps == 1) {
                robot.strafeLeft(27.5, true);
            }
            else if(steps == 2) {
                robot.rotateToHeading(-33.7);
                if(steps == 3) {
                    robot.intake.powerOn();
                }
            }
            else if(steps == 3) {
                robot.backward(8);
                if(steps == 4) {
                    robot.intake.powerOff();
                }
            }
            else if(steps == 4) {
                robot.forward(8);
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
                robot.backward(18);
            }
        }
        else {
            telemetry.addData("Error", "Stone not detected");
            telemetry.update();
        }

        /*if(steps == 0) {
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
                robot.strafeRight(37, true);
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
                robot.strafeRight(25, true);
            else
                robot.backward(18);
        }
        else if(steps == 7) {
            if(skyStone.equals(Position.RIGHT))
                robot.forward(36);
        }
        else if(steps == 8) {
            if(skyStone.equals(Position.RIGHT))
                robot.backward(18);

        }*/
    }

    @Override
    public void stop() {

    }
}
