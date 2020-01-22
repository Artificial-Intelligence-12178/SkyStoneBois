package org.firstinspires.ftc.teamcode.RandomTests;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Testing Parts")
public class TestingParts extends OpMode {

    DcMotorEx ex;
    Servo servo;

    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    @Override
    public void init() {
        ex = hardwareMap.get(DcMotorEx.class, "DC1");
        ex.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop(){
        ex.getVelocity();
    }
}