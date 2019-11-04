package org.firstinspires.ftc.teamcode.RandomTests;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


@TeleOp(name = "Testing ")
@Disabled

public class TestingWireless extends LinearOpMode
{
    @Override
    public void runOpMode()
    {
        telemetry.addLine("hi do you see me");
        waitForStart();

        telemetry.addLine("she here now");
    }
}
