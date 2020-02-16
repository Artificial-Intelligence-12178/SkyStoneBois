package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Autonomous.DetectionObject;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.firstinspires.ftc.teamcode.Robots.Robot;

@Autonomous (name = "ProtoSkyStone")
public class SkyStoneTest extends AutonomousClass {

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
            else if(skyStone.equals(Position.LEFT)) {
                robot.forward(16);
            }
            else {
                steps++;
            }
        }
        else if(steps == 1) {
            robot.strafeRight(39.5, true);
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
            robot.strafeLeft(19.75, true);
        }
        else if(steps == 5) {
            if(skyStone.equals(Position.MIDDLE))
                robot.forward(44);
            else if(skyStone.equals(Position.LEFT))
                robot.forward(36);
            else if(skyStone.equals(Position.RIGHT))
                robot.forward(52);
        }
        else {
            stop();
        }

    }

    @Override
    public void stop() {

    }
}
