package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

@TeleOp(name = "Testing Drive")
//@Disabled
public class DriverClass extends OpMode
{
    private DriveRobot joe;

    @Override
    public void init()
    {
        joe = new DriveRobot(hardwareMap);
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
        double angleRad = Math.atan2(y, x);
        double degree = Math.toDegrees(angleRad);
        if(degree < 0)
            degree+=360;

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

        //angleRad-=Math.toRadians(joe.imu.getHeading());
        topRbottomL = vec*Math.sin(angleRad+Math.PI/4);
        topLbottomR = vec*Math.sin(angleRad-Math.PI/4);

        if(Math.abs(topLbottomR) < 0.00005)
            topLbottomR = 0;

        if(Math.abs(topRbottomL) < 0.00005)
            topRbottomL = 0;

        telemetry.addData("Heading", joe.imu.getHeading());


        /**
         * SETTING POWER TO THE MOTORS
         */
        //Rotate right
        if(rTrig != 0)
        {
            joe.driveTrain.applyPower(rTrig, rTrig, rTrig, rTrig);
            telemetry.addData("Rotating Right:" , rTrig);
            telemetry.update();
        }
        //Rotate left
        else if(lTrig != 0)
        {
            joe.driveTrain.applyPower(-lTrig, -lTrig, -lTrig, -lTrig);
            telemetry.addData("Rotating Left:" , lTrig);
            telemetry.update();
        }
        else
        {
            joe.driveTrain.applyPower(-topLbottomR, topRbottomL, -topRbottomL, topLbottomR);
        }

        telemetry.addData("Angle = ", Math.toDegrees(angleRad));

        if(!motion.equals(""))
            telemetry.addData("Moving ", motion);

        if(gamepad1.a) {
            joe.intake.goForward();
            joe.intake.powerOn();
        } else if(gamepad1.b) {
            joe.intake.goReverse();
            joe.intake.powerOn();
        } else {
            joe.intake.powerOff();
        }

        if(gamepad1.dpad_up) {
            joe.verticalLift.liftUp();
        } else if (gamepad1.dpad_down) {
            joe.verticalLift.liftDown();
        } else {
            joe.verticalLift.stopLift();

        }

        telemetry.update();
    }


}


