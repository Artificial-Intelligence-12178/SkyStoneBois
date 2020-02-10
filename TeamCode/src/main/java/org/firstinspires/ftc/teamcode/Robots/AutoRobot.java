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

    public void rotateToHeading(double target, boolean highPower) {
        double currHeading = imu.getHeading();

        double turnDegrees = Math.abs(target - currHeading);

        boolean turnRight = target > currHeading;

        if(turnDegrees > 180) {
            turnRight = !turnRight;
            turnDegrees = 360 - turnDegrees;
        }

        if(turnDegrees > 2) {
            double maxPower = 1;
            double minPower = .2;
            if(highPower)
                minPower = 1;

            double power = turnDegrees/100;

            if(power > maxPower)
                power = maxPower;
            else if(power < minPower)
                power = minPower;

            if(!turnRight)
                power *= -1;

            driveTrain.applyPower(power, power, power, power);

        } else {
            driveTrain.applyPower(0, 0, 0, 0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }


}