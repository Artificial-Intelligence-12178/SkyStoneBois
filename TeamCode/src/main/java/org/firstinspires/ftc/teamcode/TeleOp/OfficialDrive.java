package org.firstinspires.ftc.teamcode.TeleOp;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Robots.DriveRobot;

@TeleOp(name = "Official Drive")

public class OfficialDrive extends OpMode
{

    DriveRobot robot;
    @Override
    public void init()
    {
        robot = new DriveRobot(hardwareMap);
    }

    @Override
    public void init_loop()
    {
        if(robot.getStatus() == null) {
            telemetry.addData("Status", "Operational");
        }
        else {
            telemetry.addData("Status", robot.getStatus());
        }

        telemetry.update();
    }

    @Override
    public void start() {
    }

    @Override
    public void loop() {
        //Finding the angle of the joystick
        double x = gamepad1.left_stick_x;
        double y = gamepad1.left_stick_y;

        double degree = Math.toDegrees(Math.atan2(y, x));
        if(degree < 0) {
            degree+=360;
        }

        //Giving the driver some leeway
        String motion = "";
        if(degree >= 265 && degree <= 275) {        //Forward
            degree = 270;
            motion = "Forward";
        }
        else if(degree >= 85 && degree <= 95) {     //Backward
            degree = 90;
            motion = "Backwards";
        }
        else if(degree >= 355 || degree <= 5) {     //Right
            degree = 0;
            motion = "Right";
        }
        else if(degree >= 175 && degree <= 185) {   //Left
            degree = 180;
            motion = "Left";
        }
        else if(degree >= 310 && degree <= 320) {   //Northeast
            degree = 315;
            motion = "Northeast";
        }
        else if(degree >= 220 && degree <= 230) {   //Northwest
            degree = 225;
            motion = "Northwest";
        }
        else if(degree >= 40 && degree <= 50) {     //Southeast
            degree = 45;
            motion = "Southeast";
        }
        else if(degree >= 130 && degree <= 140) {   //Southwest
            degree = 135;
            motion = "Southwest";
        }

        double radian = Math.toRadians(degree);
        telemetry.addData("Degree", degree);


        double fLPower;
        double fRPower;
        double bLPower;
        double bRPower;

        double vec = Math.hypot(x, y);
        if(vec > 1)
            vec = 1;

        //Power equations
        fLPower = -1 * vec * Math.sin(radian - Math.PI/4);
        fRPower = vec * Math.sin(radian + Math.PI/4);
        bLPower = -1 * vec * Math.sin(radian + Math.PI/4);
        bRPower = vec * Math.sin(radian - Math.PI/4);

        double rTrig = gamepad1.right_trigger;
        double lTrig = gamepad1.left_trigger;

        //Applying correct power
        if(rTrig > 0) {
            robot.driveTrain.applyPower(rTrig, rTrig, rTrig, rTrig);
            motion = "Spin Right";
        }
        else if(lTrig > 0) {
            robot.driveTrain.applyPower(-lTrig, -lTrig, -lTrig, -lTrig);
            motion = "Spin Left";
        }
        else {
            robot.driveTrain.applyPower(fLPower, fRPower, bLPower, bRPower);
        }

        if(!motion.equals("")) {
            telemetry.addData("Motion", motion);
        }
        telemetry.update();


        //Handling grabbers
        if(gamepad1.dpad_up) {
            robot.grabbers.grabbersUp();
        } else if(gamepad1.dpad_down) {
            robot.grabbers.grabbersDown();
        }

        //Handling Intake
        if(gamepad1.a) {
            robot.intake.goForward();
            robot.intake.powerOn();
        }
        else if(gamepad1.b) {
            robot.intake.goReverse();
            robot.intake.powerOn();
        }
        else {
            robot.intake.powerOff();
        }


        //Handling Horizontal Slide
        /*if(gamepad1.dpad_left) {
            robot.horizontalSlide.extendSlide();
        }
        else if(gamepad1.dpad_right) {
            robot.horizontalSlide.retractSlide();
        }
        else {
            robot.horizontalSlide.maintainSlide();
        }

         */
        //Handling grabber
        /*if(gamepad1.a) {
            robot.horizontalSlide.grabStone();
        }
        else if(gamepad1.b) {
            robot.horizontalSlide.releaseStone();
        }*/

    }

    public void stop() {}


}


