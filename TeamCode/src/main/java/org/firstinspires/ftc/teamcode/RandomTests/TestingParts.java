package org.firstinspires.ftc.teamcode.RandomTests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;
import org.firstinspires.ftc.teamcode.Robots.Robot;

@TeleOp (name = "Testing Parts")
public class TestingParts extends OpMode {

    DriveRobot robot;
    ElapsedTime timer;
    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    @Override
    public void init() {
        robot = new DriveRobot(hardwareMap);
    }

    @Override
    public void init_loop() {
        timer = new ElapsedTime();
    }

    double vel = 0;
    double max = vel;
    @Override
    public void loop(){
        if(timer.seconds() > 1)
        {
            vel+=50;
            timer.reset();
        }

        robot.driveTrain.applyPower(vel,vel,vel,vel);
        if(robot.driveTrain.frontLeft.getVelocity() > max)
            max = robot.driveTrain.frontLeft.getVelocity();
        telemetry.addData("max", max);
        telemetry.addData("vel", vel);
        telemetry.update();
    }
}