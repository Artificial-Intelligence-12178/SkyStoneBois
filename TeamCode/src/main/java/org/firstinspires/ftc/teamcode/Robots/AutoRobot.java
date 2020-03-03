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

    private final double STRAFE_GAIN = 1.075;
    private final double CORRECTION_GAIN = 0.0225;
    private double accLimiter = .15;
    private double acceleration = .10;

    public AutoRobot(HardwareMap map, AutonomousClass autoClass) {
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
        this.autoClass = autoClass;
        driveTrain.resetEncoders();
        grabbers.grabbersUp();
    }

    public double forward(double inches) {
        double target = inchesToTicks(inches-1);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double correction = imu.getCorrection(CORRECTION_GAIN);
            double power = determinePower(target, error, 0);
            double leftPower = power + correction;
            double rightPower = -power + correction;

            driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
            return power;
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
            accLimiter = .15;
            return 0;
        }
    }

    public double backward(double inches) {
        double target = inchesToTicks(inches-1);
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double correction = imu.getCorrection(CORRECTION_GAIN);
            double power = determinePower(target, error, 0);
            double leftPower = -power + correction;
            double rightPower = power + correction;

            driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);
            return power;

        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
            accLimiter = .15;
            return 0;
        }
    }

    public double strafeRight(double inches) {
        double target = inchesToTicks(inches-1) * STRAFE_GAIN;
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double correction = imu.getCorrection(CORRECTION_GAIN);
            double power = determinePower(target, error, 0);
            double frontPower = power + correction;
            double backPower = -power + correction;

            driveTrain.applyPower(frontPower, frontPower, backPower, backPower);
            return power;
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
            return 0;
        }
    }

    public double strafeLeft(double inches) {
        double target = inchesToTicks(inches-1) * STRAFE_GAIN;
        double error = target - driveTrain.getAverageEncoderValue();
        if (driveTrain.getAverageEncoderValue() < target) {
            double correction = imu.getCorrection(CORRECTION_GAIN);
            double power = determinePower(target, error, 0);
            double frontPower = -power + correction;
            double backPower = power + correction;

            driveTrain.applyPower(frontPower, frontPower, backPower, backPower);
            return power;
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
            return 0;
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
        if(turnDegrees > 0.2) {
            //Determining the power
            double power = determinePower(Math.abs(target), turnDegrees, 1);

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

        if (turnDegrees > 0.2) {
            double power = determinePower(Math.abs(target), turnDegrees, 2);
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

    public double determinePower(double target, double error, int rotation) {
        //Method used to determine power
        double power;
        double min = .175;
        double max = 1;
        double modifier;
        if (rotation != 0) {
            //Power will decrease as error approaching zero
            min = .15;
            modifier = max / 45;
            power = modifier * error;
        } else {
            //Power will decrease as error approaches zero
            if (target < inchesToTicks(24)) {
                max = 0.6;

                modifier = max / inchesToTicks(24);
            }
            else
                modifier = 1.2 * max  / target;


            power = modifier * error;
        }

        if(rotation == 1) {
            power = Math.pow(power, .8);
        }
        else if(rotation == 2) {
            power = Math.pow(power, .7);
        }
        else {
            power = Math.pow(power, .4);
        }

        power = Range.clip(power, min, max);
        if(power > accLimiter && rotation == 0) {
            power = accLimiter;
        }

        accLimiter += acceleration;
        Range.clip(accLimiter, 0, 1);

        return power;
    }

    public void arcLeftToHeading(double target, double radius, double baseVelocity, boolean reverse) {
        double arcDistance = Math.abs(target - imu.getHeading());
        imu.setTarget(target);
        if(arcDistance > .5) {
            double minVel = baseVelocity/4;
            double velocity = baseVelocity / 45 * arcDistance;
            if(velocity < minVel) {
                velocity = minVel;
            }

            double leftVel, rightVel;
            double time = 2 * Math.PI * radius / velocity;
            double leftDist = radius - (.5 * TRACK_WIDTH);
            double rightDist = radius + (.5 * TRACK_WIDTH);

            leftVel = 2 * Math.PI * leftDist / time;
            rightVel = -2 * Math.PI * rightDist / time;

            if(reverse) {
                leftVel *= -1;
                rightVel *= -1;
            }

            driveTrain.applyVelocities(leftVel, rightVel, leftVel, rightVel);
        }
        else {
            driveTrain.applyVelocity(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }

    }

    public void arcRightToHeading(double target, double radius, double baseVelocity, boolean reverse) {
        double arcDistance = Math.abs(target - imu.getHeading());
        imu.setTarget(target);
        if(arcDistance > .5) {
            double minVel = baseVelocity/4;
            double velocity = baseVelocity / 45 * arcDistance;
            if(velocity < minVel) {
                velocity = minVel;
            }

            double leftVel, rightVel;
            double time = 2 * Math.PI * radius/velocity;
            double leftDist = radius + (.5 * TRACK_WIDTH);
            double rightDist = radius - (.5 * TRACK_WIDTH);

            leftVel = 2 * Math.PI * leftDist / time;
            rightVel = -2 * Math.PI * rightDist / time;

            if(reverse) {
                leftVel *= -1;
                rightVel *= -1;
            }

            driveTrain.applyVelocities(leftVel, rightVel, leftVel, rightVel);
        }
        else {
            driveTrain.applyVelocity(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
        }

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

    public double issueFixing(double inches) {
        double target = inchesToTicks(inches);
        double error = target - driveTrain.getAverageEncoderValue();
        if (error > 0) {
            double correct = imu.getCorrection(.01);
            double leftPower = determinePower(target, error, 0);
            double rightPower = -determinePower(target, error, 0);

            driveTrain.applyPower(leftPower, rightPower, leftPower, rightPower);

            return correct;
        } else {
            driveTrain.applyPower(0);
            driveTrain.resetEncoders();
            autoClass.steps++;
            return 0;
        }
    }
}