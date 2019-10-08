package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Helpers.Joe;

import java.sql.SQLOutput;

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
        //Getting the angle (in radians)
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double angleRad = Math.abs(Math.atan(y / x));
        double degree;

        //Finding the reference angle
        if(x < 0 && y > 0)  //quad 2
            angleRad= Math.PI - angleRad;
        else if(x < 0 && y <= 0)  //quad 3
            angleRad = Math.PI + angleRad;
        else if(x >= 0 && y < 0)  //quad 4
            angleRad = 2*Math.PI - angleRad;

        //Determining power
        double vec = Math.sqrt(x*x+y*y);
        if(vec > 1)
            vec = 1;
        double topRbottomL, topLbottomR;
        topRbottomL = vec*Math.sin(angleRad+Math.PI/4);
        topLbottomR = vec*Math.sin(angleRad-Math.PI/4);

        //Displaying information
        degree = angleRad*(180/Math.PI);


        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;
        double xJoystick = gamepad1.left_stick_x;
        double yJoystick = gamepad1.left_stick_y;


        //Rotate right
        if(rTrig != 0)
        {
            joe.frontL.setPower(rTrig);
            joe.backL.setPower(rTrig);
            joe.frontR.setPower(-rTrig);
            joe.backR.setPower(-rTrig);
            telemetry.addData("rotating right:" , rTrig);
            telemetry.update();
        }
        //Rotate left
        else if(lTrig != 0)
        {
            joe.frontL.setPower(-rTrig);
            joe.backL.setPower(-rTrig);
            joe.frontR.setPower(rTrig);
            joe.backR.setPower(rTrig);
            telemetry.addData("rotating left:" , lTrig);
            telemetry.update();
        }

        //movement forward
        if((xJoystick <=  .10 && xJoystick >= -.10) && yJoystick<0)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backL.setPower(-topRbottomL);
            joe.frontR.setPower(topRbottomL);
            joe.backR.setPower(topLbottomR);
            telemetry.addData("moving forward:" , vec);
            telemetry.update();
        }
        //movement backwards
        else if((xJoystick <=  .10 && xJoystick >= -.10) && yJoystick>0)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backL.setPower(-topRbottomL);
            joe.frontR.setPower(topRbottomL);
            joe.backR.setPower(topLbottomR);
            telemetry.addData("moving backward:" , vec);
            telemetry.update();
        }
        //movement right
        else if((yJoystick <=  .10 && yJoystick >= -.10) && xJoystick>0)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backL.setPower(-topLbottomR);
            joe.frontR.setPower(topRbottomL);
            joe.backR.setPower(topLbottomR);
            telemetry.addData("moving right:" , vec);
            telemetry.update();
        }
        //movement left
        else if((yJoystick <=  .10 && yJoystick >= -.10) && xJoystick<0)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backL.setPower(-topRbottomL);
            joe.frontR.setPower(topRbottomL);
            joe.backR.setPower(topLbottomR);
            telemetry.addData("moving left:" , vec);
            telemetry.update();
        }
        //adjustable values (for leeway) H
        //diagonal north east  .707, -.707
        else if(degree >= 310 && degree <= 325)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backR.setPower(topLbottomR);
            telemetry.addData("northeast:" , vec);
            telemetry.update();
        }
        //diagonal north west -.707 , -.707
        else if(degree >= 220 && degree <= 230)
        {
            joe.frontR.setPower(topRbottomL);
            joe.backL.setPower(-topRbottomL);
            telemetry.addData("north west:" , vec);
            telemetry.update();
        }
        //diagonal south east
        else if(degree >= 40 && degree <= 50)
        {
            joe.frontR.setPower(topRbottomL);
            joe.backL.setPower(-topRbottomL);
            telemetry.addData("southeast:" , vec);
            telemetry.update();
        }
        //diagonal southwest
        else if(degree >= 130 && degree <= 140)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backR.setPower(topRbottomL);
            telemetry.addData("southwest:" , vec);
            telemetry.update();
        }
        else
        {
            joe.frontL.setPower(0);
            joe.backL.setPower(0);
            joe.frontR.setPower(0);
            joe.backR.setPower(0);
            telemetry.addData("Degree:" , degree);
            telemetry.addData("pow1 = ", topRbottomL);
            telemetry.addData("pow2 = ", topLbottomR);
            telemetry.update();
        }
    }
}


