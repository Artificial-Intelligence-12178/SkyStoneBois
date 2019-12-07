package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Helpers.Joe;

@TeleOp(name = "Official Drive")

public class OfficialDrive extends OpMode
{
    private Joe joe = new Joe();
    private double grabStay = 0;
    private double grabStay2 = 0.7;
    private int locoActive = 0;
    private boolean loco = false;
    private double SLOWSPEED = 0.3;
    private long timeStarted;

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
        timeStarted = System.currentTimeMillis();
    }

    @Override
    public void loop()
    {
        //Values of the joysticks with the left for movement and right for arm vertical movement
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;
        double rY = gamepad1.right_stick_y;

        //Dpad values for making the back things whip and nae nae
        boolean dUp = gamepad1.dpad_up;
        boolean dDown = gamepad1.dpad_down;

        //Rotation of robot
        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        //In case the servos holding up our arm stops working for some reason
        boolean dPadR = gamepad1.dpad_right;

        //Chomp chomp arm out/in
        boolean b = gamepad1.b;
        boolean lBump = gamepad1.left_bumper;

        //Loco mode
        boolean rBump = gamepad1.right_bumper;

        //Foundation thangs
        boolean xButt = gamepad1.x;

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
         * SETTING POWER TO THE MOTORS // LOCO MODE
         */
        //Rotate right
        if(rBump)
        {
            if(rTrig != 0)
            {
                joe.frontL.setPower(-SLOWSPEED);
                joe.backL.setPower(-SLOWSPEED);
                joe.frontR.setPower(-SLOWSPEED);
                joe.backR.setPower(-SLOWSPEED);
                telemetry.addLine("SRotateR");
            }
            //Rotate left
            else if(lTrig != 0)
            {
                joe.frontL.setPower(SLOWSPEED);
                joe.backL.setPower(SLOWSPEED);
                joe.frontR.setPower(SLOWSPEED);
                joe.backR.setPower(SLOWSPEED);
                telemetry.addLine("SRotateL");
            }
            else if(x > 0 && y < 0.3 && y > -0.2)
            {
                joe.frontL.setPower(-SLOWSPEED);
                joe.frontR.setPower(-SLOWSPEED);
                joe.backL.setPower(SLOWSPEED);
                joe.backR.setPower(SLOWSPEED);
                telemetry.addLine("SRight");
            }
            else if(x < 0 && y < 0.2 && y > -0.2)
            {
                joe.frontL.setPower(SLOWSPEED);
                joe.frontR.setPower(SLOWSPEED);
                joe.backL.setPower(-SLOWSPEED);
                joe.backR.setPower(-SLOWSPEED);
                telemetry.addLine("SLeft");
            }
            else if(y < -0.2 && x < 0.2 && x > -0.2)
            {
                joe.frontL.setPower(-SLOWSPEED);
                joe.frontR.setPower(SLOWSPEED);
                joe.backL.setPower(-SLOWSPEED);
                joe.backR.setPower(SLOWSPEED);
                telemetry.addLine("SForward");
            }
            else if(y > 0.2 && x < 0.2 && x > -0.2)
            {
                joe.frontL.setPower(SLOWSPEED);
                joe.frontR.setPower(-SLOWSPEED);
                joe.backL.setPower(SLOWSPEED);
                joe.backR.setPower(-SLOWSPEED);
                telemetry.addLine("SBackward");
            }
            else
            {
                joe.frontL.setPower(0);
                joe.frontR.setPower(0);
                joe.backL.setPower(0);
                joe.backR.setPower(0);
            }
        }
        else
        {
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
        }

        /**
         * ARM VERTICAL MOVEMENT
         */
        //Arm vertical movement
        if(rY < 0)
            joe.arm.setPower(rY);
        else if(rY > 0)
            joe.arm.setPower(rY);
        else
            joe.arm.setPower(0);

        /**
         * ARMS UNFOLDING
         */
        //Arm servos that unfold arm from auto
        joe.daniel.setPosition(0.7);
        joe.abe.setPosition(0.6);
        /*if(dPadR)
        {
            joe.daniel.setPosition(0.7); //IS 180 SERVO
            joe.abe.setPosition(0.6);
            telemetry.addLine("Opening Oven");
        }
        else {
            //joe.daniel.setPosition(0);
            telemetry.addLine("Closing Oven");
        }

        //ABE (pov of reg front, right one
        if(rBump)
        {
            joe.abe.setPosition(0.6);
            telemetry.addLine("Opened Abe's Oven");
            //DIS WORKING
        }
        else
        {
            //joe.abe.setPosition(1);
            telemetry.addLine("Closed Abe's Oven");
        }*/

        /**
         * ARM OPENING FOR CHOMP CHOMP
         */
        //Arm opening
        //if(System.currentTimeMillis() > timeStarted+1500)
        //{
            if(lBump) //Close
            {
                joe.jorge.setPosition(0.4); //Used to be 0
                joe.kim.setPosition(0.6); //Used to be 0.9
                telemetry.addLine("Open Sesame");
            }
            else if(b) //Open
            {
                joe.jorge.setPosition(0.7); //Used to be 0
                joe.kim.setPosition(0.3); //Used to be 0.9
                telemetry.addLine("Slap slap");
            }
            else
            {
                joe.jorge.setPosition(0.5); //Used to be 0.15
                joe.kim.setPosition(0.5); //Used tp be 0.8
                telemetry.addLine("She stop");
            }
        //}

        /**
         * THINGS IN THE BACK
         */
        //Chomper (2 servos moving under one condition) back of robot
        if(dDown)
        {
            if(grabStay != 1)
                grabStay++;
        }
        else if(dUp)
        {
            if(grabStay != 0)
                grabStay--;
        }
        telemetry.addData("num", grabStay);

        if(grabStay == 1)
        {
            joe.back1.setPosition(0.8);
            joe.back2.setPosition(0.3);
            telemetry.addLine("Whip");
        }
        else
        {
            joe.back2.setPosition(0.87);
            joe.back1.setPosition(0.25);
            telemetry.addLine("Nae nae");
        }

        /**
         * FOUNDATION SERVOS
         */
        if(xButt) //clamp onto foundation
        {
            joe.found1.setPosition(0.2);
            joe.found2.setPosition(0.9);
        }
        else
        {
            joe.found1.setPosition(.97);
            joe.found2.setPosition(.1);
        }

        telemetry.addData("Angle = ", Math.toDegrees(angleRad));
        telemetry.update();
    }

}


