package org.firstinspires.ftc.teamcode.Autonomous;

import android.os.health.SystemHealthManager;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.Joe;
import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@Autonomous(name = "Foundation park", group = "joe")

public class IterativeFoundationPark extends org.firstinspires.ftc.teamcode.Autonomous.Autonomous
{
    private JoeAuto joeBot = new JoeAuto(this);
    private ElapsedTime runtime = new ElapsedTime();
    private long timeStarted;

    public void init(){
        joeBot.init(hardwareMap);
        joeBot.resetEncoder();
        super.steps = 0;
    }

    public void init_loop(){
        telemetry.addLine(joeBot.getStatus());
        if(joeBot.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        super.steps = 0;
    }
    //
    public void start()
    {
        runtime.reset();
    }

    public void loop()
    {
        if(steps == 0)
        {
            joeBot.forward(26.36);
            telemetry.addData("position ", JoeAuto.ticksToInches(Math.abs(joeBot.frontR.getCurrentPosition())));
            telemetry.addData("pow ", joeBot.power);
            telemetry.update();
        }
        else if(steps == 1)
        {
            joeBot.strafeLeft(22.7);
        }
        else if(steps == 2)
        {
            joeBot.foundationGrab();
            timeStarted = System.currentTimeMillis();
            steps++;
        }
        else if(steps == 3)
        {
            if(timeStarted+1300 < System.currentTimeMillis())
                steps++;
        }
        else if(steps == 4)
        {
            joeBot.strafeLeft(1.2);
        }
        else if(steps == 5)
        {
            joeBot.backward(28.8);
        }
        else if(steps == 6)
        {
            joeBot.foundationRelease();
            steps++;
        }
        else if(steps == 7)
        {
            joeBot.strafeRight(54.015748);
        }
        telemetry.update();
    }


}
