package org.firstinspires.ftc.teamcode.RandomTests;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Testing Parts")
public class TestingParts extends OpMode {

    Servo left;
    Servo right;

    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    @Override
    public void init() {
        left = hardwareMap.get(Servo.class, "ServoL");
        right = hardwareMap.get(Servo.class, "ServoR");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop(){
        double leftPos = left.getPosition();
        double rightPos = right.getPosition();

        telemetry.addData("left", leftPos);
        telemetry.addData("right", rightPos);

        if(gamepad1.x) {
            right.setPosition(0.05);
            left.setPosition(0.92);
            telemetry.addData("Servo", "down");
        } else {
            right.setPosition(.5);
            left.setPosition(.5);
            telemetry.addData("Servo", "up");
        }

        telemetry.update();
    }
}