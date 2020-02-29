package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Park Bridge")
@Disabled
public class ParkBridge extends AutonomousClass {
    AutoRobot robot;

    @Override
    public void init() {
        steps = 0;
        robot = new AutoRobot(hardwareMap, this);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", robot.getStatus());
        telemetry.update();
    }

    @Override
    public void start() {
        steps = 0;
    }

    @Override
    public void loop() {

        if(steps == 0) {
            robot.forward(36);
        }
        else if(steps == 1) {
            //BLUE SIDE
            robot.strafeRight(12);
            //RED SIDE
            //robot.strafeLeft(12, true);
        }
    }

    @Override
    public void stop() {

    }
}
