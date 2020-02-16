package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.teamcode.Hardware.IMU;
import org.firstinspires.ftc.teamcode.Robots.DriveRobot;
import org.opencv.core.Mat;


@TeleOp (name = "Vertical Control")
public class TestingDriving extends OpMode {

    DriveRobot joe;

    @Override
    public void init_loop() {
        telemetry.addData("Status", joe.getStatus());
    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void init() {
        joe = new DriveRobot(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.dpad_left) {
            joe.verticalLift.liftUp();
        }
        else if(gamepad1.dpad_down) {
            joe.verticalLift.liftDown();
        }
        else if(gamepad1.right_trigger > 0) {
            joe.verticalLift.singleControl(false, true);
        }
        else if(gamepad1.left_trigger > 0) {
            joe.verticalLift.singleControl(true, true);
        }
        else if(gamepad1.left_bumper) {
            joe.verticalLift.singleControl(true, false);
        }
        else if(gamepad1.right_bumper) {
            joe.verticalLift.singleControl(false, false);
        }
        else {
            joe.verticalLift.stopLift();
        }
    }
}
