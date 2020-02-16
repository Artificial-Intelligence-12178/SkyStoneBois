// Simple autonomous program that drives bot forward until end of period
// or touch sensor is hit. If touched, backs up a bit and turns 90 degrees
// right and keeps going. Demonstrates obstacle avoidance and use of the
// REV Hub's built in IMU in place of a gyro. Also uses gamepad1 buttons to
// simulate touch sensor press and supports left as well as right turn.
//
// Also uses IMU to drive in a straight line when not avoiding an obstacle.

package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.Velocity;
import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

@TeleOp(name="IMU")
//@Disabled
public class TestingIMU extends OpMode
{
    DriveRobot robot;

    @Override
    public void init() {
        robot = new DriveRobot(hardwareMap);
    }

    @Override
    public void init_loop() {
        if(!robot.getStatus().equals(""))
            telemetry.addData("Status", robot.getStatus());

        telemetry.update();
    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        telemetry.addData("Heading", robot.imu.getHeading());
        telemetry.update();
    }

    @Override
    public void stop() {

    }

    public void getCorrection(){

    }

}
