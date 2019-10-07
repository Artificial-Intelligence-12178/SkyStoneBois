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
        double pow1, pow2;
        pow1 = vec*Math.sin(angleRad+Math.PI/4);
        pow2 = vec*Math.sin(angleRad-Math.PI/4);

        //Displaying information
        degree = angleRad*(180/Math.PI);
        telemetry.addData("Degree:" , degree);
        telemetry.addData("pow1 = ", pow1);
        telemetry.addData("pow2 = ", pow2);
        telemetry.update();


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
        }
        //Rotate left
        else if(lTrig != 0)
        {
            joe.frontL.setPower(-rTrig);
            joe.backL.setPower(-rTrig);
            joe.frontR.setPower(rTrig);
            joe.backR.setPower(rTrig);
        }

        //movement forward
        if((xJoystick <=  .05 && xJoystick >= -.05) && yJoystick<0)
        {
            joe.frontL.setPower(vec);
            joe.backL.setPower(vec);
            joe.frontR.setPower(vec);
            joe.backR.setPower(vec);
        }
        //movement backwards
        else if((xJoystick <=  .05 && xJoystick >= -.05) && yJoystick>0)
        {
            joe.frontL.setPower(vec);
            joe.backL.setPower(vec);
            joe.frontR.setPower(vec);
            joe.backR.setPower(vec);
        }
        //movement right
        else if((yJoystick <=  .05 && yJoystick >= -.05) && xJoystick>0)
        {
            joe.frontL.setPower(vec);
            joe.backL.setPower(-vec);
            joe.frontR.setPower(-vec);
            joe.backR.setPower(vec);
        }
        //movement left
        else if((yJoystick <=  .05 && yJoystick >= -.05) && xJoystick<0)
        {
            joe.frontL.setPower(-vec);
            joe.backL.setPower(vec);
            joe.frontR.setPower(vec);
            joe.backR.setPower(-vec);
        }
        //adjustable values (for leeway) H
        //diagonal north east  .707, -.707
        else if((yJoystick >= -.757 && yJoystick <= -.657) && (xJoystick <=  .757 && xJoystick >= .657))
        {
            joe.frontL.setPower(vec);
            joe.backR.setPower(vec);
        }
        //diagonal north west -.707 , -.707
        else if((yJoystick >= -.757 && yJoystick <= -.657) && (xJoystick >=  -.757 && xJoystick <= -.657))
        {
            joe.frontR.setPower(vec);
            joe.backL.setPower(vec);
        }
        //diagonal south east
        else if((yJoystick <= .757 && yJoystick >= .657) && (xJoystick <=  .757 && xJoystick >= .657))
        {
            joe.frontR.setPower(-vec);
            joe.backL.setPower(-vec);
        }
        else if((yJoystick <= .757 && yJoystick >= .657) && (xJoystick >=  -.757 && xJoystick <= -.657))
        {
            joe.frontL.setPower(-vec);
            joe.backR.setPower(-vec);
        }
    }
}


