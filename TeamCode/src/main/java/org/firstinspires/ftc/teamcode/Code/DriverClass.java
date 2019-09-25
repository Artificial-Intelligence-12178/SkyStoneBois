package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Helpers.Joe;

@TeleOp(name = "Official Drive")

public class DriverClass extends OpMode
{
    private Joe joe = new Joe();

    @Override
    public void init()
    {
        joe.init(hardwareMap);
    }

    @Override
    public void init_loop()
    {
        telemetry.addLine(joe.getStatus());
        if(joe.getStatus().equals(""));
            telemetry.addData("Status", "Working");
    }

    @Override
    public void start()
    {

    }

    @Override
    public void loop()
    {
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double angleRad = Math.atan(y / x);

        String quadrant = "";
        if(angleRad < 0)
            angleRad*=-1;

        if(x < 0 && y > 0)
            angleRad = Math.PI - angleRad;
        else if(x < 0 && y < 0)
            angleRad = Math.PI + angleRad;
        else if(x > 0 && y < 0)
            angleRad = 2*Math.PI - angleRad;
        else if(y == 0 && x < 0)
            angleRad = Math.PI;
        else if(x == 0 && y < 0)
            angleRad = 3*Math.PI/2;

        //telemetry.addData("Degree:" , angleRad*(180/Math.PI) + "");
        telemetry.update();

        /*
        *Quadrant Code*
        ONLY FOR QUADRANTS 2-4
         */

        //Q1


        double vec = Math.sqrt(x*x+y*y);
        if(vec > 1)
            vec = 1;
        double pow1 = 0;
        double pow2 = 0;
        pow1 = vec*Math.sin(angleRad+Math.PI/4);
        pow2 = vec*Math.sin(angleRad-Math.PI/4);

        telemetry.addData("pow1 = ", pow1);
        telemetry.addData("pow2 = ", pow2);
        telemetry.update();




    }

}


