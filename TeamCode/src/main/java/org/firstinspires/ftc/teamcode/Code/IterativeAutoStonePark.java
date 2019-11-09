package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.Helpers.Joe;
import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@TeleOp(name = "Testing Iterative:", group = "joe")
public class IterativeAutoStonePark extends OpMode {

    private JoeAuto joeAuto = new JoeAuto();
    private ElapsedTime runtime = new ElapsedTime();
    private double steps = 0;
    public void init(){
        joeAuto.init(hardwareMap);
    }

    public void init_loop(){
        telemetry.addLine(joeAuto.getStatus());
        if(joeAuto.getStatus().equals(""))
            telemetry.addData("Status: ", "Working");
    }

    public void start(){
        runtime.reset();
    }

    public void loop(){

    }

    public void stop(){

    }


}
