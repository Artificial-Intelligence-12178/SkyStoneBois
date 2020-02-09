package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Park Bridge")
public class ParkBridge extends AutonomousClass {
    AutoRobot joe;

    @Override
    public void init() {
        steps = 0;
        joe = new AutoRobot(hardwareMap, this);
    }

    @Override
    public void init_loop() {
        telemetry.addData("Status", joe.getStatus());
        telemetry.update();
    }

    @Override
    public void start() {
        steps = 0;
    }

    @Override
    public void loop() {

        if(steps == 0) {
            //Code to move away from wall
        }
        else if(steps == 1) {
            //Code to move onto tape
        }
    }

    @Override
    public void stop() {

    }
}
