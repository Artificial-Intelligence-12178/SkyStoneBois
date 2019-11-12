package org.firstinspires.ftc.teamcode.Helpers;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class AutoBot
{
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;

    public String status = "";

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public AutoBot(){ }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        //4 Movement Motors

        //Left Front Motor
        try {
            frontL = hwmap.get(DcMotor.class, "DC3");
            frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontL (DC3) motor not mapping";
        }

        //Right Front Motor
        try {
            frontR = hwmap.get(DcMotor.class, "DC1");
            frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontR (DC1) motor not mapping";
        }

        //Left Back Motor
        try {
            backL = hwmap.get(DcMotor.class, "DC2");
            backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackL (DC2) motor not mapping";
        }

        //Right Back Motor
        try {
            backR = hwmap.get(DcMotor.class, "DC4");
            backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackR (DC4) motor not mapping";
        }
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

    public void forward(int ticks, double pow){
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
        }

    }


    public void backward(int ticks, double pow){
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
        }
    }

    public void strafeRight(int ticks, double pow){
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
        }
    }

    public void strafeLeft(int ticks, double pow){
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
        }
    }

    public void rotateRight(int ticks, double pow){
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(-pow);
            frontL.setPower(-pow);
            backR.setPower(-pow);
            backL.setPower(-pow);
        }
        else
        {
            //advance step or stop
        }
    }

    public void rotateLeft(int ticks, double pow){
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(pow);
            frontL.setPower(pow);
            backR.setPower(pow);
            backL.setPower(pow);
        }
        else
        {
            //advance step or stop
        }
    }



}