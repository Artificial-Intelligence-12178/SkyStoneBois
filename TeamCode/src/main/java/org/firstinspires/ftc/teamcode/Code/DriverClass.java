package org.firstinspires.ftc.teamcode.Code;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import org.firstinspires.ftc.teamcode.Helpers.Joe;

import java.sql.SQLOutput;

@TeleOp(name = "Testing Drive")

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
        if(joe.getStatus().equals(""))
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
        boolean dUp = gamepad1.dpad_up;
        boolean dDown = gamepad1.dpad_down;
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

        //Displaying information
        degree = Math.toDegrees(angleRad);

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        String motion = "";

        /**
         * DETERMINING THE ANGLES
         */
        //Movement Forward
        if(degree >= 265 && degree <= 275)
        {
            angleRad = Math.toRadians(270);
            motion = "Forward";
        }
        //Movement Backwards
        else if(degree >= 85 && degree <= 95)
        {
            angleRad = Math.toRadians(90);
            motion = "Backwards";
        }
        //Movement Right
        else if(degree >= 355 || degree <= 5)
        {
            angleRad = Math.toRadians(0);
            motion = "Right";
        }
        //Movement Left
        else if(degree >= 175 && degree <= 185)
        {
            angleRad = Math.toRadians(180);
            motion = "Left";
        }

        //Adjustable values (for leeway) H
        //Diagonal north east  .707, -.707
        else if(degree >= 310 && degree <= 320)
        {
            angleRad = Math.toRadians(315);
            motion = "Northeast";
        }
        //Diagonal north west -.707 , -.707
        else if(degree >= 220 && degree <= 230)
        {
            angleRad = Math.toRadians(225);
            motion = "Northwest";
        }
        //Diagonal south east
        else if(degree >= 40 && degree <= 50)
        {
            angleRad = Math.toRadians(45);
            motion = "Southeast";
        }
        //Diagonal southwest
        else if(degree >= 130 && degree <= 140)
        {
            angleRad = Math.toRadians(135);
            motion = "Southwest";
        }

        topRbottomL = vec*Math.sin(angleRad+Math.PI/4);
        topLbottomR = vec*Math.sin(angleRad-Math.PI/4);
        if(Math.abs(topLbottomR) < 0.00005)
            topLbottomR = 0;

        if(Math.abs(topRbottomL) < 0.00005)
            topRbottomL = 0;

        /**
         * SETTING POWER TO THE MOTORS
         */
        //Rotate right
        if(rTrig != 0)
        {
            joe.frontL.setPower(-rTrig);
            joe.backL.setPower(-rTrig);
            joe.frontR.setPower(-rTrig);
            joe.backR.setPower(-rTrig);
            telemetry.addData("Rotating Right:" , rTrig);
            telemetry.update();
        }
        //Rotate left
        else if(lTrig != 0)
        {
            joe.frontL.setPower(lTrig);
            joe.backL.setPower(lTrig);
            joe.frontR.setPower(lTrig);
            joe.backR.setPower(lTrig);
            telemetry.addData("Rotating Left:" , lTrig);
            telemetry.update();
        }
        else
        {
            joe.frontL.setPower(topLbottomR);
            joe.backL.setPower(topRbottomL);
            joe.frontR.setPower(-topRbottomL);
            joe.backR.setPower(-topLbottomR);
        }

        telemetry.addData("Angle = ", Math.toDegrees(angleRad));

        if(!motion.equals(""))
            telemetry.addData("Moving ", motion);

        if(dUp)
        {
            joe.arm.setPower(0.5);
            telemetry.addData("Arm ", "moving up");
        }
        else if(dDown)
        {
            joe.arm.setPower(-0.5);
            telemetry.addData("Arm ", " moving down");
        }
        else
            joe.arm.setPower(0);

        telemetry.update();
    }
}


