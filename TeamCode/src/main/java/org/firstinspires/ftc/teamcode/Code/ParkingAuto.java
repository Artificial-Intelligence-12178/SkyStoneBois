package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

import java.util.List;

@Autonomous(name = "Parking Autonomous", group = "Joe")

public class ParkingAuto extends LinearOpMode
{
    private JoeAuto joe = new JoeAuto();
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
            forwardE(1800);

            backwardE(1800);

            leftE(1800);

            rightE(1800);
            
            rotateRight(100000);

            rotatingLeft(100000);
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
    public void encodeResetAndRun()
    {
        joe.frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        joe.frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        joe.backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
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
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(-ticks);

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
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(ticks);

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
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(ticks);

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
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(-ticks);

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
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(ticks);
        joe.frontR.setTargetPosition(ticks);
        joe.backL.setTargetPosition(ticks);
        joe.backR.setTargetPosition(ticks);

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
    public void rotatingLeft(int ticks)
    {
        //resetting da encoders cuz u gotta
        encodeResetAndRun();

        //setting values that the encoders will attempt to reach
        joe.frontL.setTargetPosition(-ticks);
        joe.frontR.setTargetPosition(-ticks);
        joe.backL.setTargetPosition(-ticks);
        joe.backR.setTargetPosition(-ticks);

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
