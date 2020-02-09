package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Park Wall")
public class ParkWall extends AutonomousClass {

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
            joe.forward(36);
        }
    }

    @Override
    public void stop() {

    }
}
