package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Helpers.Joe;
import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;
import org.firstinspires.ftc.teamcode.Helpers.AutoBot;

@Autonomous(name = "Testing Iterative:", group = "joe")
public class IterativeAutoStonePark extends OpMode {

    private AutoBot autoBot = new AutoBot();
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;
    public void init(){
        autoBot.init(hardwareMap);
    }

    public void init_loop(){
        telemetry.addLine(autoBot.getStatus());
        if(autoBot.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
        steps = 0;
    }

    public void start(){
        runtime.reset();
    }

    public void loop(){
        if(steps == 0)
            autoBot.forward(1500, 0.5);
        else if(steps == 1)
            autoBot.backward(1500, 0.5);
        else if(steps == 2)
            autoBot.strafeLeft(1500, 0.5);
        else if(steps == 3)
            autoBot.strafeRight(1500, 0.5);
        else if(steps == 4)
            autoBot.rotateLeft(1500, 0.5);
        else if(steps == 5)
            autoBot.rotateRight(1500, 0.5);
        else
            stop();
    }

    public void stop(){

    }


}
