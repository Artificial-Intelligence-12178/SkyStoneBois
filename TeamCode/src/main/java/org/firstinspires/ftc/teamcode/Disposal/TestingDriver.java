package org.firstinspires.ftc.teamcode.Disposal;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp (name = "Testing TeleOp")
public class TestingDriver extends OpMode {

    Robot rob;
    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void init() {
        rob = new Robot();
        rob.init(hardwareMap);
    }

    @Override
    public void loop() {
        /*
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        //Getting the angle (in radians)
        double radian = Math.abs(Math.atan(y / x));
        double degree;

        //Finding the reference angle
        if(x < 0 && y > 0)  //quad 2
            radian = Math.PI - radian;
        else if(x < 0 && y <= 0)  //quad 3
            radian = Math.PI + radian;
        else if(x >= 0 && y < 0)  //quad 4
            radian = 2*Math.PI - radian;

        //Determining power
        double vec = Math.sqrt(x*x+y*y);
        if(vec > 1)
            vec = 1;

        degree = Math.toDegrees(radian);

        String motion = "";

        /**
         * DETERMINING THE ANGLES
         */
        //Movement Forward
        /*if(degree >= 265 && degree <= 275)
        {
            radian = Math.toRadians(270);
            motion = "Forward";
        }
        //Movement Backwards
        else if(degree >= 85 && degree <= 95)
        {
            radian = Math.toRadians(90);
            motion = "Backwards";
        }
        //Movement Right
        else if(degree >= 355 || degree <= 5)
        {
            radian = Math.toRadians(0);
            motion = "Right";
        }
        //Movement Left
        else if(degree >= 175 && degree <= 185)
        {
            radian = Math.toRadians(180);
            motion = "Left";
        }

        double frontLbackR = vec*Math.sin(radian+Math.PI/4);
        double frontRbackL = vec*Math.sin(radian-Math.PI/4);
        if(Math.abs(frontLbackR) < 0.00005)
            frontLbackR = 0;
        if(Math.abs(frontRbackL) < 0.00005)
            frontRbackL = 0;

        telemetry.addData("Direction", motion);

        if(rTrig != 0){
            rob.frontL.setPower(rTrig);
            rob.backL.setPower(rTrig);
            rob.frontR.setPower(-rTrig);
            rob.backR.setPower(-rTrig);
        }
        else if(lTrig != 0){
            rob.frontL.setPower(-lTrig);
            rob.backL.setPower(-lTrig);
            rob.frontR.setPower(lTrig);
            rob.backR.setPower(lTrig);
        }
        else {
            rob.frontL.setPower(frontLbackR);
            rob.backR.setPower(-frontLbackR);
            rob.frontR.setPower(-frontRbackL);
            rob.backL.setPower(frontRbackL);
        }*/

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;
        if(rTrig != 0){
            rob.frontL.setPower(-rTrig);
            rob.backL.setPower(-rTrig);
            rob.frontR.setPower(-rTrig);
            rob.backR.setPower(-rTrig);
        }
        else if(lTrig != 0){
            rob.frontL.setPower(lTrig);
            rob.backL.setPower(lTrig);
            rob.frontR.setPower(lTrig);
            rob.backR.setPower(lTrig);
        }
        else if(gamepad1.dpad_up){
            rob.frontL.setPower(-.25);
            rob.backL.setPower(-.25);
            rob.frontR.setPower(.25);
            rob.backR.setPower(.25);
        }
        else if(gamepad1.dpad_down){
            rob.frontL.setPower(.25);
            rob.backL.setPower(.25);
            rob.frontR.setPower(-.25);
            rob.backR.setPower(-.25);
        }
        else if(gamepad1.dpad_left){
            rob.frontL.setPower(.25);
            rob.backL.setPower(-.25);
            rob.frontR.setPower(.25);
            rob.backR.setPower(-.25);
        }
        else if(gamepad1.dpad_right){
            rob.frontL.setPower(-.25);
            rob.backL.setPower(.25);
            rob.frontR.setPower(-.25);
            rob.backR.setPower(.25);
        }
        else
        {
            rob.stopMotors();
        }
        String directions = "" + rob.frontL.getDirection() + ", " + rob.frontR.getDirection();
        String back = "" + rob.backR.getDirection() + ", " + rob.backL.getDirection();
        telemetry.addData("FRONT", directions);
        telemetry.addData("BACK", back);
        telemetry.update();

    }
}
