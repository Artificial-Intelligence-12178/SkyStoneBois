package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.opencv.core.Mat;

public class AutoRobot extends Robot {

    AutonomousClass autoClass;

    public AutoRobot(HardwareMap map, AutonomousClass autoClass) {
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
        this.autoClass = autoClass;
        driveTrain.resetEncoders();
        grabbers.grabbersUp();
    }

    public void forward(double inches) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double correction = imu.getCorrection(.01);
            double power = determinePower(target, error, false, false);
            double leftPower = power + correction;
            double rightPower = -power + correction;

            driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
            //driveTrain.applyPower(power, -power, power, -power);
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
            double correction = imu.getCorrection(.01);
            double power = determinePower(target, error, false, false);
            double leftPower = -power + correction;
            double rightPower = power + correction;

            driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
            //driveTrain.applyPower(-power, power, -power, power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }
    }

    public double strafeRight(double inches, boolean L) {
        double compensate = 8;
        if(inches >= 24) {
            double raw = inches-72;
            compensate = 8 + raw/24;
        }
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        double power = 0;
        if (driveTrain.getAverageEncoderValue() < target+inchesToTicks(compensate)) {
            double correction = imu.getCorrection(0.01);
            power = determinePower(target, error, false, true);
            double frontPower = power + correction;
            double backPower = -power + correction;

            driveTrain.applyPower(frontPower, frontPower, backPower, backPower);
            //driveTrain.applyPower(power, power, -power, -power);
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }

        return power;
    }

    public void strafeLeft(double inches, boolean lowPower) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target+inchesToTicks(4)) {
            double correction = imu.getCorrection(0.01);
            double power = determinePower(target, error, false, lowPower);
            double frontPower = -power + correction;
            double backPower = power + correction;

            driveTrain.applyPower(frontPower, frontPower, backPower, backPower);

            //driveTrain.applyPower(-power, -power, power, power);
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

    public void rotateAboutPoint(double target, boolean rear) {
        driveTrain.setZeroPower(DcMotor.ZeroPowerBehavior.FLOAT);

        imu.setTarget(target);

        double currHeading = imu.getHeading();
        double turnDegrees = Math.abs(target - currHeading);

        boolean turnLeft = target > currHeading;

        if (turnDegrees > 180) {
            turnLeft = !turnLeft;
            turnDegrees = 360 - turnDegrees;
        }

        if (turnDegrees > 0.5) {
            double power = determinePower(Math.abs(target), turnDegrees, true, false);

            //Negating power if turning left
            if (turnLeft) {
                power *= -1;
            }

            //Applying power to the drivetrain
            if(rear) {
                driveTrain.applyPower(power, power, 0, 0);
            }
            else {
                driveTrain.applyPower(0, 0, power, power);
            }

        }
        else {
            autoClass.steps++;
            driveTrain.setZeroPower(DcMotor.ZeroPowerBehavior.BRAKE);
            driveTrain.resetEncoders();
            driveTrain.applyPower(0);
        }
    }

    public double determinePower(double target, double error, boolean rotation, boolean strafe) {
        //Method used to determine power
        double power;
        double min = .12;
        double max = 1;
        double modifier;
        if (rotation) {
            //Power will decrease as error approaching zero
            max = .6;
            modifier = max / 180;
            power = modifier * error;
        } else {
            //Power will decrease as error approaches zero
            if (target < inchesToTicks(24)) {
                max = 0.6;

                modifier = max / inchesToTicks(24);
            }
            else
                modifier = max / target;


            power = modifier * error;
        }

        power = Math.sqrt(power);
        if (min > power)
            power = min;
        else if(power > max)
            power = max;

        return power;
    }

    //==============================================================================================
    //EXPERIMENTAL METHODS

    /**
     * Method used to test driving with auto-correction
     * @param power Base power applied to the wheels
     * @param gain Constant used to determine the weight of the correction value
     */
    public void correctionDrive(double power, double gain) {
        double correction = imu.getCorrection(gain);
        double leftPower = power + correction;
        double rightPower = -power + correction;

        driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
    }

    /**
     * Method used to drive this AutoRobot in an arc
     * @param radius Radius of the arc
     * @param baseVelocity The velocity that the chassis will travel at (Base Velocity)
     */
    public void driveInArc(double radius, double baseVelocity) {
        double leftPower, rightPower;
        double time = 2 * Math.PI * radius / baseVelocity;
        double leftDist = radius - (.5 * TRACK_WIDTH);
        double rightDist = radius + (.5 * TRACK_WIDTH);

        leftPower = 2 * Math.PI * leftDist / time;
        rightPower = -2 * Math.PI * rightDist / time;

        driveTrain.applyVelocities(leftPower, rightPower, leftPower, rightPower);
    }
}