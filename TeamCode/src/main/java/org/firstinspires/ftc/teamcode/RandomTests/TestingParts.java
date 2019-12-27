package org.firstinspires.ftc.teamcode.RandomTests;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Testing Parts")
public class TestingParts extends OpMode {

    DcMotor ex;
    Servo servo;

    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    @Override
    public void init() {
        servo = hardwareMap.get(Servo.class, "Servo1");
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop(){
        telemetry.addData("Position: ", servo.getPosition());
        telemetry.update();
    }
}