package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

import java.util.List;

@Autonomous(name = "Encode Debug", group = "Joe")

public class JoeMamaSux extends LinearOpMode
{
    private JoeAuto dancer = new JoeAuto();
    static final double SPEED = 0.6;

    @Override
    public void runOpMode() throws InterruptedException
    {
        dancer.init(hardwareMap);

        //Printing on the phone that the robot is ready
        telemetry.addData("Status", "Ready to friggin run");
        telemetry.update();

        waitForStart();
        if(opModeIsActive()) {
            forwardE(1000);
            //backwardE(1000);
        }
    }

    public void forwardE(int ticks){
        encodeResetAndRun();

        dancer.frontL.setTargetPosition(-ticks);
        dancer.frontR.setTargetPosition(ticks);
        dancer.backL.setTargetPosition(-ticks);
        dancer.backR.setTargetPosition(ticks);
        dancer.frontR.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dancer.frontL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dancer.backL.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        dancer.backR.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        power();

        while(opModeIsActive() && dancer.frontR.isBusy())
        {
            telemetry.addLine("" + dancer.frontR.getCurrentPosition());
            telemetry.addLine("" + dancer.frontL.getCurrentPosition());
            telemetry.addData("Moving forward", ticks);
            telemetry.update();
        }

        nStop();
    }

    public void backwardE(int ticks){
        encodeResetAndRun();

        dancer.backR.setTargetPosition(-ticks);
        dancer.backL.setTargetPosition(ticks);
        dancer.frontR.setTargetPosition(-ticks);
        dancer.frontL.setTargetPosition(ticks);

        power();

        while(opModeIsActive() && dancer.backR.isBusy())
        {
            telemetry.addLine("" + dancer.backR.getCurrentPosition());
            telemetry.addLine("" + dancer.backL.getCurrentPosition());
            telemetry.addData("Moving Backward", ticks);
            telemetry.update();
        }

        nStop();
    }

    public void encodeResetAndRun(){
        dancer.backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dancer.backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dancer.frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        dancer.frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

    }

    public void nStop(){
        dancer.frontL.setPower(0);
        dancer.frontR.setPower(0);
        dancer.backL.setPower(0);
        dancer.backR.setPower(0);
    }

    public void power(){
        dancer.frontL.setPower(SPEED);
        dancer.frontR.setPower(SPEED);
        dancer.backL.setPower(SPEED);
        dancer.backR.setPower(SPEED);
    }
}