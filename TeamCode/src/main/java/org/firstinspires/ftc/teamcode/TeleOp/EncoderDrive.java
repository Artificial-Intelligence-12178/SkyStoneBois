package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

@TeleOp(name = "Encoder Drive")

public class EncoderDrive extends OpMode
{
    private JoeAuto joe = new JoeAuto();

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
        boolean z = gamepad1.y;
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

        //Rotate right
        if(z == true)
        {
            joe.frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            joe.backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            joe.frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            joe.backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
            joe.frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            joe.backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            joe.frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            joe.backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        }

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

        topRbottomL = vec*Math.sin(angleRad+Math.PI/4);
        topLbottomR = vec*Math.sin(angleRad-Math.PI/4);
        if(Math.abs(topLbottomR) < 0.00005)
            topLbottomR = 0;

        if(Math.abs(topRbottomL) < 0.00005)
            topRbottomL = 0;

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
        else if(motion.compareTo("") != 0)
        {
            joe.frontL.setPower(-topLbottomR);
            joe.backL.setPower(-topRbottomL);
            joe.frontR.setPower(topRbottomL);
            joe.backR.setPower(topLbottomR);
        }
        else
        {
            joe.frontL.setPower(0);
            joe.backL.setPower(0);
            joe.frontR.setPower(0);
            joe.backR.setPower(0);
        }


        telemetry.addData("Front Right: ", topRbottomL);
        telemetry.addData("Front Left: ", -topLbottomR);
        telemetry.addData("Back Right: ", topLbottomR);
        telemetry.addData("Back Left: ", -topRbottomL);
        telemetry.addData("Angle = ", Math.toDegrees(angleRad));

        if(!motion.equals(""))
            telemetry.addData("Moving ", motion);

        int frontLPos = joe.frontL.getCurrentPosition();
        int frontRpos = joe.frontR.getCurrentPosition();
        int backRPos = joe.backR.getCurrentPosition();
        int backLPos = joe.backR.getCurrentPosition();
        telemetry.addData("Encoder Value FrontL: " , frontLPos);
        telemetry.addData("Encoder Value FrontR: " , frontRpos);
        telemetry.addData("Encoder Value BackR: " , backRPos);
        telemetry.addData("Encoder Value BackL: " , backLPos);

        int avg = Math.abs(frontLPos)+Math.abs(frontRpos)+Math.abs(backLPos)+Math.abs(backRPos);
        avg/=4;
        telemetry.addData("Encoder average: ", avg);
        telemetry.update();
    }
}
