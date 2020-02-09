package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;

public class AutoRobot extends Robot {

    AutonomousClass autoClass;
    public AutoRobot(HardwareMap map, AutonomousClass autoClass) {
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
        this.autoClass = autoClass;
    }

    public void forward(double inches) {
        if (driveTrain.getAverageEncoderValue() < inchesToTicks(inches)) {
            driveTrain.applyPower(.7, -.7, .7, -.7);
        } else {
            driveTrain.applyPower(0, 0, 0, 0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void backward(double inches) {
        if (driveTrain.getAverageEncoderValue() < inchesToTicks(inches)) {
            driveTrain.applyPower(-.7, .7, -.7, .7);
        } else {
            driveTrain.applyPower(0, 0, 0, 0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void strafeRight(double inches) {
        if (driveTrain.getAverageEncoderValue() < inchesToTicks(inches)) {
            driveTrain.applyPower(.7, .7, -.7, -.7);
        } else {
            driveTrain.applyPower(0, 0, 0, 0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void strafeLeft(double inches) {
        if (driveTrain.getAverageEncoderValue() < inchesToTicks(inches)) {
            driveTrain.applyPower(-.7, -.7, .7, .7);
        } else {
            driveTrain.applyPower(0, 0, 0, 0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void rotate(double degrees) {
        double leftPower = .7;
        double rightPower = .7;

        if(degrees < -180)
            degrees+=360;
        else if(degrees > 180)
            degrees-=360;

        if(degrees < 0) {
            leftPower*=-1;
            rightPower*=-1;
        }

        double desired = imu.getHeading() + degrees;

        if(desired < -180)
            desired+=360;
        else if(desired > 180)
            desired-=360;
    }
}