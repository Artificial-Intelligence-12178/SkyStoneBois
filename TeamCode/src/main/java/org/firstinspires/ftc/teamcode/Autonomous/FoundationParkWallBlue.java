package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Robots.AutoRobot;

@Autonomous (name = "Found Wall Blue")
public class FoundationParkWallBlue extends AutonomousClass {

    AutoRobot joe;

    @Override
    public void init() {
        joe = new AutoRobot(hardwareMap);
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

    }

    @Override
    public void stop() {

    }
}
