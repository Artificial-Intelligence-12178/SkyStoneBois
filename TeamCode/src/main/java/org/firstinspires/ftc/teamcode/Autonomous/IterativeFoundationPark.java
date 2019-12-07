package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@Autonomous(name = "Foundation park", group = "joe")

public class IterativeFoundationPark extends OpMode
{
    private JoeAuto joeBot = new JoeAuto();
    private double power = .4;
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;

    public void init(){
        joeBot.init(hardwareMap);
        joeBot.resetEncoder();
    }

    public void init_loop(){
        telemetry.addLine(joeBot.getStatus());
        if(joeBot.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        steps = 0;
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
            joeBot.forward(27.86);
            telemetry.addData("position ", Math.abs(joeBot.frontR.getCurrentPosition()));
            telemetry.update();
        }
        else if(steps == 1)
        {
            joeBot.foundationGrab();
            steps++;
        }
        else if(steps == 2)
        {
            joeBot.backward(27.86);
        }
        else if(steps == 3)
        {
            joeBot.foundationRelease();
            steps++;
        }
        else if(steps == 4)
        {
            joeBot.strafeRight(22.015748);
        }
        telemetry.update();
    }


}
