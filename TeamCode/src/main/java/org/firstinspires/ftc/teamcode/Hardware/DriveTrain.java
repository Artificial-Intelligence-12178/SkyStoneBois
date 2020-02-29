package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.R;
import org.firstinspires.ftc.teamcode.Robots.Robot;

public class DriveTrain {
    //Motors for each wheel
    DcMotorEx frontLeft;
    DcMotorEx frontRight;
    DcMotorEx backLeft;
    DcMotorEx backRight;

    //String used to display status of each component
    protected String status;

    //Timer used to determine speed
    ElapsedTime speedTimer;

    public DriveTrain(HardwareMap map, DcMotor.RunMode mode){

        status = "";

        //Initializing components
        try {
            frontLeft = map.get(DcMotorEx.class, "DC1");
            frontLeft.setMode(mode);
            frontLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            frontLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = map.get(DcMotorEx.class, "DC4");
            frontRight.setMode(mode);
            frontRight.setDirection(DcMotorSimple.Direction.REVERSE);
            frontRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nFront Right motor (DC4) not mapping";
        }

        try {
            backRight = map.get(DcMotorEx.class, "DC3");
            backRight.setMode(mode);
            backRight.setDirection(DcMotorSimple.Direction.REVERSE);
            backRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = map.get(DcMotorEx.class, "DC2");
            backLeft.setMode(mode);
            backLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            backLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status+="\nBack Left motor (DC2) not mapping";
        }

        speedTimer = new ElapsedTime();
    }

    //Method used to get the status of this DriveTrain
    public String getStatus(){
        return status;
    }

    /**
     * Method used to apply unique powers to each motor in this DriveTrain
     *
     * @param fl Power applied to front left motor
     * @param fr Power applied to front right motor
     * @param bl Power applied to back left motor
     * @param br Power applied to back right motor
     */
    public void applyPower(double fl, double fr, double bl, double br){
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    /**
     * Method used to apply same power to each motor in this DriveTrain
     *
     * @param pow Power applied to all motors
     */
    public void applyPower(double pow) {
        applyPower(pow, pow, pow, pow);
    }

    /**
     * Method used to obtain the average encoder count of this DriveTrain
     * @return The average encoder count of this DriveTrain
     */
    public double getAverageEncoderValue(){
        int sum = 0;

        sum += Math.abs(frontLeft.getCurrentPosition());
        sum += Math.abs(frontRight.getCurrentPosition());
        sum += Math.abs(backLeft.getCurrentPosition());
        sum += Math.abs(backRight.getCurrentPosition());

        return sum/4.0;
    }

    /**
     * Method used to change the current RunMode of the motors
     *
     * @param mode A RunMode to set motors to
     */
    public void setMode(DcMotor.RunMode mode) {
        frontRight.setMode(mode);
        frontLeft.setMode(mode);
        backRight.setMode(mode);
        backLeft.setMode(mode);
    }


    /**
     * Method used to reset DriveTrain encoders
     */
    public void resetEncoders(){
        frontLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        frontLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    /**
     * Method used to get the current motor velocities
     * @param inches Boolean used to determine whether to return in in/sec or tick/sec
     * @return A array of doubles containing all motor velocities
     */
    public double[] getMotorVelocities(boolean inches){
        double[] returnSpeeds = new double[4];
        returnSpeeds[0] = frontLeft.getVelocity();
        returnSpeeds[1] =  frontRight.getVelocity();
        returnSpeeds[2] = backLeft.getVelocity();
        returnSpeeds[3] = backRight.getVelocity();

        if(inches) {
            for(int i = 0; i < returnSpeeds.length; i++) {
                returnSpeeds[i] = Robot.ticksToInches((int)returnSpeeds[i]);
            }
        }

        return returnSpeeds;
    }

    /**
     * Method used to set the current ZeroPowerBehavior of each motor
     * @param behavior ZeroPowerBehavior object that each motor will be set to
     */
    public void setZeroPower(DcMotor.ZeroPowerBehavior behavior) {
        frontLeft.setZeroPowerBehavior(behavior);
        frontRight.setZeroPowerBehavior(behavior);
        backLeft.setZeroPowerBehavior(behavior);
        backRight.setZeroPowerBehavior(behavior);
    }

    /**
     * Method used to apply a velocity to all motors
     * @param vel Velocity in inches per second
     */
    public void applyVelocity(double vel) {
        double velTicks = Robot.inchesToTicks(vel);
        frontLeft.setVelocity(velTicks);
        frontRight.setVelocity(velTicks);
        backLeft.setVelocity(velTicks);
        backRight.setVelocity(velTicks);
    }

    /**
     * Method used to apply unique velocities to each motor
     * @param velFL Velocity of front left motor in inches per second
     * @param velFR Velocity of front right motor in inches per second
     * @param velBL Velocity of back left motor in inches per second
     * @param velBR Velocity of back right motor in inches per second
     */
    public void applyVelocities(double velFL, double velFR, double velBL, double velBR) {
        double velTicksFL = Robot.inchesToTicks(velFL);
        double velTicksFR = Robot.inchesToTicks(velFR);
        double velTicksBL = Robot.inchesToTicks(velBL);
        double velTicksBR = Robot.inchesToTicks(velBR);

        frontLeft.setVelocity(velTicksFL);
        frontRight.setVelocity(velTicksFR);
        backLeft.setVelocity(velTicksBL);
        backRight.setVelocity(velBR);
    }
}