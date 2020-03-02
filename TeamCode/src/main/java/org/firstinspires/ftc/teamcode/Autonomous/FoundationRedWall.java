package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Found RW")
@Disabled
public class FoundationRedWall extends AutonomousClass {
    ElapsedTime timer;
    AutoRobot robot;
    public void init() {
        steps = 0;
        robot = new AutoRobot(hardwareMap, this);
        timer = new ElapsedTime();
    }

    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
        telemetry.update();
    }

    public void start() {
        steps = 0;
    }

    public void stop() {

    }

    public void loop() {
        telemetry.addData("Step", steps);
        telemetry.update();

        if(steps == 0) {
            // GET REAL STRAFE VALUE
            robot.strafeRight(6);
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
            robot.backward(30);
        }
        else if(steps == 4) {
            // MAKE SURE ROTATION CAN TURN REAL FOUNDATION
            robot.rotateToHeading(-90);
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
            robot.strafeRight(10);
        }
        else if(steps == 8) {
            robot.backward(40);
        }
    }
}
