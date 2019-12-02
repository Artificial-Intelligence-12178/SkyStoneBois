package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.AutoBot;

public class IterativeParking extends OpMode
{
    private AutoBot autoBot = new AutoBot();
    private double power = .4;
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;


    public void init(){
        autoBot.init(hardwareMap);
        autoBot.resetEncoder();
    }

    public void init_loop(){
        telemetry.addLine(autoBot.getStatus());
        if(autoBot.getStatus().equals(""))
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
            autoBot.backward(34, .4);
        }
        telemetry.update();
    }


}
