package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Autonomous.DetectionObject;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Proto2Skystones")
public class TwoSkyStoneTest extends AutonomousClass {

    enum Position {
        LEFT,
        MIDDLE,
        RIGHT
    }

    SkyStoneTest.Position skyStone = SkyStoneTest.Position.LEFT;
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
            skyStone = SkyStoneTest.Position.RIGHT;
        }
        else if(camera.getMiddleValue() == 0) {
            skyStone = SkyStoneTest.Position.MIDDLE;
        }
        else if(camera.getLeftValue() == 0) {
            skyStone = SkyStoneTest.Position.LEFT;
        }
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        if(steps == 0) {
            if(skyStone.equals(SkyStoneTest.Position.MIDDLE)) {
                robot.forward(8);
            }
            else if(skyStone.equals(SkyStoneTest.Position.LEFT)) {
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
            if(skyStone.equals(SkyStoneTest.Position.MIDDLE))
                robot.forward(44);
            else if(skyStone.equals(SkyStoneTest.Position.LEFT))
                robot.forward(36);
            else if(skyStone.equals(SkyStoneTest.Position.RIGHT))
                robot.forward(52);
        }
        else if(steps == 6) {
            robot.rotateToHeading(-90);
            if(steps == 7)
                intakeTimer.reset();
        }
        else if(steps == 7) {
            robot.intake.goReverse();
            robot.intake.powerOn();

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
            if(skyStone.equals(SkyStoneTest.Position.MIDDLE))
                robot.backward(60);
            else if(skyStone.equals(SkyStoneTest.Position.LEFT))
                robot.backward(52);
            else if(skyStone.equals(SkyStoneTest.Position.RIGHT))
                robot.backward(68);
        }
        else if(steps == 10) {
            robot.strafeRight(19.75, true);
            if(steps == 11)
                robot.intake.powerOn();
        }
        else if(steps == 11) {
            robot.backward(6);
            if(steps == 12)
                robot.intake.powerOff();
        }
        else if(steps == 12) {
            robot.forward(6);
        }
        else if(steps == 13) {
            robot.strafeLeft(19.75, true);
        }
        else if(steps == 14) {
            if(skyStone.equals(SkyStoneTest.Position.MIDDLE))
                robot.forward(60);
            else if(skyStone.equals(SkyStoneTest.Position.LEFT))
                robot.forward(52);
            else if(skyStone.equals(SkyStoneTest.Position.RIGHT))
                robot.forward(68);
        }
        else {
            stop();
        }
    }

    @Override
    public void stop() {

    }
}
