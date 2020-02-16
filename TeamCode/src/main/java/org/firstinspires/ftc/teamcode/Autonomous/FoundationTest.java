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
        steps = 0;
    }

    public void stop() {

    }

    public void loop() {
        telemetry.addData("Step", steps);
        telemetry.update();

        if(steps == 0) {
            // GET REAL STRAFE VALUE
            robot.strafeLeft(8, false);
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
            robot.rotateToHeading(90);
        }
        else if(steps == 5) {
            robot.forward(5);
        }
        else if(steps == 6) {
            robot.grabbers.grabbersUp();
            steps++;
        }
        else if(steps == 7) {
            robot.backward(40);
        }
    }
}
