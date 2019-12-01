package org.firstinspires.ftc.teamcode.Helpers;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.IterativeAutoStonePark;
import org.firstinspires.ftc.teamcode.Autonomous.TwoStonesParkBlue;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class AutoBot
{
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;

    public Servo grabLeft;
    public Servo grabRight;
    public String status = "";
    public double leftServoPosition;
    public double rightServoPosition;

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public AutoBot(){ }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        //4 Movement Motors

        //Left Front Motor
        try {
            frontL = hwmap.get(DcMotor.class, "DC3");
            frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontL (DC3) motor not mapping";
        }

        //Right Front Motor
        try {
            frontR = hwmap.get(DcMotor.class, "DC1");
            frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontR (DC1) motor not mapping";
        }

        //Left Back Motor
        try {
            backL = hwmap.get(DcMotor.class, "DC2");
            backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackL (DC2) motor not mapping";
        }

        //Right Back Motor
        try {
            backR = hwmap.get(DcMotor.class, "DC4");
            backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackR (DC4) motor not mapping";
        }

        try{
            grabLeft = hwmap.get(Servo.class, "Back1");
        }catch (Exception e){
            status+="\nLeft grabber (Back1) not mapping";
        }

        try{
            grabRight = hwmap.get(Servo.class, "Back2");
        }catch (Exception e){
            status+="\nRight grabber (Back2) not mapping";

        }

        leftServoPosition = .5;
        rightServoPosition = .5;
    }

    //basically a toString method. This tells the code how to display the status.
    public String getStatus()
    {
        return status;
    }

    public void resetEncoder(){
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    int errorSum = 0;
    int eChange = 0;
    int ePrevious = 0;
    public void forward(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks)
        {
            frontR.setPower(-pow);
            frontL.setPower(pow);
            backR.setPower(-pow);
            backL.setPower(pow);
        }
        else
        {
            //advance step or stop
            stop();
            errorSum = 0;
            eChange = 0;
            ePrevious = 0;
            IterativeAutoStonePark.steps++;
        }

    }


    public void backward(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks)
        {
            frontR.setPower(pow);
            frontL.setPower(-pow);
            backR.setPower(pow);
            backL.setPower(-pow);
        }
        else
        {
            //advance step or stop
            stop();
            IterativeAutoStonePark.steps++;
        }
    }

    public void strafeRight(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks)
        {
            frontR.setPower(pow);
            frontL.setPower(pow);
            backR.setPower(-pow);
            backL.setPower(-pow);
        }
        else
        {
            //advance step or stop
            stop();
            IterativeAutoStonePark.steps++;
        }
    }

    public void strafeLeft(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks)
        {
            frontR.setPower(-pow);
            frontL.setPower(-pow);
            backR.setPower(pow);
            backL.setPower(pow);
        }
        else
        {
            //advance step or stop
            stop();
            IterativeAutoStonePark.steps++;
        }
    }

    public void rotateRight(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(-pow);
            frontL.setPower(-pow);
            backR.setPower(-pow);
            backL.setPower(-pow);
        }
        else
        {
            //advance step or stop
            stop();
            IterativeAutoStonePark.steps++;
        }
    }

    public void rotateLeft(double inches, double pow){
        int ticks = inchesToTicks(inches);
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(pow);
            frontL.setPower(pow);
            backR.setPower(pow);
            backL.setPower(pow);
        }
        else
        {
            //advance step or stop
            stop();
            IterativeAutoStonePark.steps++;
        }
    }

    public void stop(){
        frontR.setPower(0);
        frontL.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
        resetEncoder();
    }

    public static int inchesToTicks(double in)
    {
        return (int)((960*in)/(3.93701*Math.PI));
    }

    public void forward(double pow){
        frontR.setPower(-pow);
        frontL.setPower(pow);
        backR.setPower(-pow);
        backL.setPower(pow);
    }

    public void backward(double pow){
        frontR.setPower(pow);
        frontL.setPower(-pow);
        backR.setPower(pow);
        backL.setPower(-pow);
    }

    public void strafeLeft(double pow){
        frontR.setPower(-pow);
        frontL.setPower(-pow);
        backR.setPower(pow);
        backL.setPower(pow);
    }

    public void strafeRight(double pow){
        frontR.setPower(pow);
        frontL.setPower(pow);
        backR.setPower(-pow);
        backL.setPower(-pow);
    }

    public void testingForward(double inches){
        int ticks = inchesToTicks(inches);
        if(frontL.getCurrentPosition() < ticks)
        {
            double m = -0.0005;
            double b = -1*m*ticks;
            double pow = m*frontL.getCurrentPosition()+b;
            if(pow > 1)
                pow = 1;

            frontL.setPower(pow);
            frontR.setPower(-pow);
            backL.setPower(pow);
            backR.setPower(-pow);
        }
        else {
            stop();
            TwoStonesParkBlue.steps++;
        }
    }
}