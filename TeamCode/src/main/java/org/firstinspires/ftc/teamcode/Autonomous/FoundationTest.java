package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "ProtoFound")
public class FoundationTest extends AutonomousClass {

    ElapsedTime timer;
    AutoRobot robot;
    public void init() {
        steps = 0;
        robot = new AutoRobot(hardwareMap, this);
        timer = new ElapsedTime();
    }

    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
    }

    public void start() {

    }

    public void stop() {

    }

    public void loop() {
        if(steps == 0) {
            robot.strafeLeft(10);
        }
        else if(steps == 1) {
            robot.forward(33);
            if(steps == 2)
                timer.reset();
        }
        else if(steps == 2) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 500)
                steps++;
        }
        else if(steps == 3) {
            robot.backward(30);
        }
    }
}
