package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Helpers.Joe;

@TeleOp(name = "Official Drive")

public class OfficialDrive extends OpMode
{
    private Joe joe = new Joe();
    private double grabStay = 0.1;

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
        //Values of the joysticks with the left for movement and right for arm vertical movement
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rY = gamepad1.right_stick_y;

        //Dpad values for making chomp chomp go up and down
        boolean dUp = gamepad1.dpad_up;
        boolean dDown = gamepad1.dpad_down;

        //Rotation of robot
        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        //In case the servos holding up our arm stops working for some reason
        boolean lBump = gamepad1.left_bumper;
        boolean rBump = gamepad1.right_bumper;

        //Expanding arm out
        boolean a = gamepad1.a;

        //Getting the angle (in radians)
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

        //Adjustable values (for leeway)
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

        telemetry.addData("Direction", motion);

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

        //Arm vertical movement
        if(rY < 0)
            joe.arm.setPower(rY);
        else if(rY > 0)
            joe.arm.setPower(rY);
        else
            joe.arm.setPower(0);



        /*Arm opening
        if(a)
        {
            joe.jorge.setPosition(1);
            joe.kim.setPosition(1);
        }
        else
        {
            joe.jorge.setPosition(0.5);
            joe.kim.setPosition(0.5);
        }

        //Arm servos that unfold arm from auto
        if(lBump)
            joe.daniel.setPosition(0.5);
        else
            joe.daniel.setPosition(0);
        if(rBump)
            joe.abe.setPosition(0.5);
        else
            joe.abe.setPosition(0);

        *///Chomper (2 servos moving under one condition) back of robot
        /*if(dUp)
        {
            if (grabStay != 1)
                grabStay++;
        }
        else if(dDown)
        {
            if (grabStay != 0)
                grabStay--;
        }
        telemetry.addData("num", grabStay);

        if(grabStay == 1)
        {
            joe.back1.setPosition(0);
            joe.back2.setPosition(1);
        }
        else
        {
            joe.back1.setPosition(1);
            joe.back2.setPosition(0);
        }*/
        //Testing

        if(dUp && grabStay != 0.9)
        {
            grabStay+=0.1;
        }
        else if(dDown && grabStay != 0.1)
        {
            grabStay-=0.1;
        }

        joe.back1.setPosition(grabStay);
        joe.back2.setPosition(1-grabStay);

        telemetry.addData("Angle = ", Math.toDegrees(angleRad));
        telemetry.update();
    }

}


