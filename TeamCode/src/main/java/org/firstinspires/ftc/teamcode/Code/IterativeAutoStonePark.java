package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Helpers.AutoBot;
import org.firstinspires.ftc.teamcode.Helpers.Joe;
import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@Autonomous(name = "Testing Iterative:", group = "joe")
public class IterativeAutoStonePark extends OpMode {

    private JoeAuto joeAuto = new JoeAuto();
    private AutoBot joe = new AutoBot();
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;
    public void init()
    {
        joe.init(hardwareMap);

    }

    public void init_loop(){
        telemetry.addLine(joe.getStatus());
        if(joe.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        steps = 0;
    }

    public void start()
    {
        runtime.reset();
        joe.resetEncoder();
    }
    public void loop()
    {
        if(steps == 0)
            joe.forward(1000, .5);
        if(steps == 1)
            joe.backward(1000, .5);
    }

    public void stop(){

    }


}
