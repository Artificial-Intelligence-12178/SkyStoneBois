package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

public class IterativeParking extends OpMode
{
    private JoeAuto joeAuto = new JoeAuto();
    private double power = .4;
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;


    public void init(){
        joeAuto.init(hardwareMap);
        joeAuto.resetEncoder();
    }

    public void init_loop(){
        telemetry.addLine(joeAuto.getStatus());
        if(joeAuto.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        steps = 0;
        joeAuto.resetEncoder();
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
            joeAuto.backward(12);
        }
        telemetry.update();
    }


}
