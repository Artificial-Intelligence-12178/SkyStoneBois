package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "ArcFoundation")
public class ArcFoundationRed extends AutonomousClass {
    AutoRobot robot;
    ElapsedTime timer;

    @Override
    public void init() {
        super.init();
        robot = new AutoRobot(hardwareMap, this);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
        telemetry.update();
    }

    @Override
    public void start() {
        timer = new ElapsedTime();
    }

    @Override
    public void loop() {
        if(steps == 0) {
            robot.strafeRight(3);
        }
        else if(steps == 1) {
            robot.forward(32);
            if(steps == 2) {
                timer.reset();
            }
        }
        else if(steps == 2) {
            robot.grabbers.grabbersDown();
            if(timer.milliseconds() > 500) {
                steps++;
            }
        }
        else if(steps == 3) {
            robot.arcLeftToHeading(-88, 16.5, 25, true);
        }
        else if(steps == 4) {
            robot.forward(24);
        }
        else if(steps == 5) {
            robot.grabbers.grabbersUp();
            steps++;
        }
        else if(steps == 6) {
            robot.backward(46);
        }
    }

    @Override
    public void stop() {

    }
}
