package org.firstinspires.ftc.teamcode.RandomTests;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp (name = "Testing Parts")
public class TestingParts extends OpMode {

    DcMotor frontLeft;

    @Override
    public void start(){

    }

    @Override
    public void stop(){

    }

    @Override
    public void init() {
        frontLeft = hardwareMap.get(DcMotor.class, "DC1");
        frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void loop(){
        frontLeft.setPower(0.5);
    }
}