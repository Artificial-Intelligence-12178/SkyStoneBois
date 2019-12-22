package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Robots.JoeAuto;

public class IterativeParking extends Autonomous
{
    private JoeAuto joeAuto = new JoeAuto(this);
    private double power = .4;
    private ElapsedTime runtime = new ElapsedTime();


    public void init(){
        joeAuto.init(hardwareMap);
        joeAuto.resetEncoder();
    }

    public void init_loop(){
        telemetry.addLine(joeAuto.getStatus());
        if(joeAuto.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        super.steps = 0;
        joeAuto.resetEncoder();
    }
    //
    public void start()
    {
        runtime.reset();
    }

    public void loop()
    {
        if(super.steps == 0)
        {
            joeAuto.backward(12);
        }
        telemetry.update();
    }


}
