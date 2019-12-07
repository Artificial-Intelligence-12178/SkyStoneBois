package org.firstinspires.ftc.teamcode.Disposal;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@Autonomous(name = "Foundation Auto", group = "Joe")

public class FoundationAuto extends LinearOpMode
{
    private org.firstinspires.ftc.teamcode.Disposal.JoeAuto joe = new org.firstinspires.ftc.teamcode.Disposal.JoeAuto();
    static final double SPEED = 0.6;


    @Override
    public void runOpMode() throws InterruptedException
    {
        joe.init(hardwareMap);

        //Printing on the phone that the robot is ready
        telemetry.addData("Status", "Ready to friggin run");
        telemetry.update();

        waitForStart();

        if(opModeIsActive())
        {
            //I havent set up the servos for  this  yet but its the movement for it
            leftE(JoeAuto.inchesToTicks(36));
            forwardE(JoeAuto.inchesToTicks(24));
            backwardE(JoeAuto.inchesToTicks(24));
            leftE(JoeAuto.inchesToTicks(36));
        }

    }
    //movement methods start here
    public void encStop()
    {
        joe.frontL.setPower(0);
        joe.backL.setPower(0);
        joe.frontR.setPower(0);
        joe.backR.setPower(0);
    }
    public void encodeRun()
    {
        joe.frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void encodeReset(){
        joe.frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
    }
    public void power()
    {
        joe.frontL.setPower(SPEED);
        joe.backL.setPower(SPEED);
        joe.frontR.setPower(SPEED);
        joe.backR.setPower(SPEED);
    }
    public void forwardE(int ticks)  //negate right side if you want wheels to move up  // negate left side if you want wheels to move down
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(-ticks);

        encodeRun();

        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Moving Forward", ticks);
            telemetry.update();
        }
        encStop();
    }
    public void backwardE(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(ticks);

        encodeRun();
        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Moving Backwards", ticks);
            telemetry.update();
        }
        encStop();
    }
    public void leftE(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(ticks);

        encodeRun();
        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Moving Left", ticks);
            telemetry.update();
        }
        encStop();
    }
    public void rightE(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(-ticks);

        encodeRun();

        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Moving Right", ticks);
            telemetry.update();
        }
        encStop();
    }
    public void rotateRight(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(ticks);

        encodeRun();
        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Rotating Right", ticks);
            telemetry.update();
        }
        encStop();
    }
    public void rotateLeft(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeReset();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(-ticks);

        encodeRun();
        //making motors move
        power();

        while(opModeIsActive() && joe.frontL.isBusy() && joe.frontR.isBusy() && joe.backL.isBusy() && joe.backR.isBusy())
        {
            telemetry.addLine("" + joe.frontL.getCurrentPosition());
            telemetry.addLine("" + joe.backL.getCurrentPosition());
            telemetry.addLine("" + joe.frontR.getCurrentPosition());
            telemetry.addLine("" + joe.backR.getCurrentPosition());
            telemetry.addData("Rotating Left", ticks);
            telemetry.update();
        }
        encStop();
    }



}
