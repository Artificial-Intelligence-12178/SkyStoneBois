package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;

public class AutoRobot extends Robot {

    AutonomousClass autoClass;

    public AutoRobot(HardwareMap map, AutonomousClass autoClass) {
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
        this.autoClass = autoClass;
        driveTrain.resetEncoders();
    }

    public void forward(double inches) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double power = determinePower(target, error, false, false);
            driveTrain.applyPower(power, -power, power, -power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void backward(double inches) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double power = determinePower(target, error, false, false);
            driveTrain.applyPower(-power, power, -power, power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void strafeRight(double inches, boolean lowPower) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target+inchesToTicks(4)) {
            double power = determinePower(target, error, false, lowPower);
            driveTrain.applyPower(power, power, -power, -power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public void strafeLeft(double inches, boolean lowPower) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target+inchesToTicks(4)) {
            double power = determinePower(target, error, false, lowPower);
            driveTrain.applyPower(-power, -power, power, power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    /**
     * @param target a degree value on the interval [-180, 180]
     */
    public void rotateToHeading(double target) {
        //Used for straight line movement (Auto correction)
        imu.setTarget(target);

        //Finding the degrees needed to turn
        double currHeading = imu.getHeading();
        double turnDegrees = Math.abs(target - currHeading);

        //Determining to turn left or right
        boolean turnLeft = target > currHeading;

        //If the robot will turn more than 180 degrees, turn the opposite direction instead
        if(turnDegrees > 180) {
            turnLeft = !turnLeft;
            turnDegrees = 360-turnDegrees;
        }

        //If the degrees needed to turn is close enough to target
        if(turnDegrees > 0.5) {
            //Determining the power
            double power = determinePower(Math.abs(target), turnDegrees, true, false);

            //Negating power if turning left
            if(turnLeft) {
                power*=-1;
            }

            //Applying power to the drivetrain
            driveTrain.applyPower(power);
        }
        else {
            //Advancing the autonomous
            autoClass.steps++;
            driveTrain.resetEncoders();
            driveTrain.applyPower(0);
        }
    }

    public double determinePower(double target, double error, boolean rotation, boolean lowPower) {
        //Method used to determine power
        double power;
        double min = .15;
        if(rotation) {
            //Power will decrease as error approaching zero
            double max = .6;
            double modifier = max/180;
            power = modifier*error;
        }
        else {
            //Power will decrease as error approaches zero
            double max = 1;
            double modifier;
            if(lowPower)
                max = .6;

            if(target < inchesToTicks(24))
                modifier = max/inchesToTicks(24);
            else
                modifier = max/target;


            power = modifier*error;
        }

        if(min > power)
            power = min;

        return power;
    }

    //Method used to test straight line movement
    public void testingCorrection(double pow) {
        double correction = imu.getCorrection();
        double rightPower = -pow+correction;
        double leftPower = pow+correction;

        rightPower = Range.clip(rightPower, -1, 1);
        leftPower = Range.clip(leftPower, -1, 1);

        driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
    }


}