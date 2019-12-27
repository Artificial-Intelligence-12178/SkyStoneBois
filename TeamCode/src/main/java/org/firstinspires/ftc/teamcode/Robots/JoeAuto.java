package org.firstinspires.ftc.teamcode.Robots;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Autonomous.Autonomous;
import org.firstinspires.ftc.teamcode.Autonomous.IterativeAutoStonePark;
import org.firstinspires.ftc.teamcode.Autonomous.TwoStonesParkBlue;

import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.PIDCoefficients;

public class JoeAuto
{
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;

    public Servo grabLeft;
    public Servo grabRight;
    public Servo found1;
    public Servo found2;
    public String status = "";
    public double leftServoPosition;
    public double rightServoPosition;

    public double power;

    public static double diamOfWheels = 3.93701;
    public static double circOfWheels = diamOfWheels*Math.PI;
    public static double ticksPerRev = 960;
    public static double ticksPerInch = ticksPerRev/circOfWheels;
    public static double inchesPerTick = circOfWheels/ticksPerRev;

    public Autonomous auto;

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public JoeAuto(Autonomous plan){ auto = plan; }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        //4 Movement Motors

        //Left Front Motor
        try {
            frontL = hwmap.get(DcMotor.class, "DC3");
            frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontL (DC3) motor not mapping";
        }

        //Right Front Motor
        try {
            frontR = hwmap.get(DcMotor.class, "DC1");
            frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontR (DC1) motor not mapping";
        }

        //Left Back Motor
        try {
            backL = hwmap.get(DcMotor.class, "DC2");
            backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackL (DC2) motor not mapping";
        }

        //Right Back Motor
        try {
            backR = hwmap.get(DcMotor.class, "DC4");
            backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackR (DC4) motor not mapping";
        }

        try{
            grabLeft = hwmap.get(Servo.class, "Back1");
        }catch (Exception e){
            status+="\nLeft grabber (Back1) not mapping";
        }

        try{
            grabRight = hwmap.get(Servo.class, "Back2");
        }catch (Exception e){
            status+="\nRight grabber (Back2) not mapping";
        }
        try {
            found1 = hwmap.get(Servo.class, "Found1");
        } catch (Exception e) {
            status += "\nFoundationServo1 not mapping";
        }
        try {
            found2 = hwmap.get(Servo.class, "Found2");
        } catch (Exception e) {
            status += "\nFoundationServo2 not mapping";
        }

        leftServoPosition = .5;
        rightServoPosition = .5;
        power = 0;
    }

    //basically a toString method. This tells the code how to display the status.
    public String getStatus()
    {
        return status;
    }

    public void resetEncoder(){
        frontR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backR.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        backL.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void forward(double inches){
        int ticks = inchesToTicks(inches);
        int pos = frontR.getCurrentPosition();
        if(Math.abs(pos) < ticks)
        {
            int minDist = inchesToTicks(36);
            double radPerTick = Math.PI/minDist;
            double rad = pos*radPerTick;
            power = 0.45*Math.cos(rad-Math.PI)+0.55;

            if(ticks < minDist)
                power = 0.3;
            else if(ticks-pos > minDist)
                power = 1;

            frontL.setPower(power);
            frontR.setPower(-power);
            backL.setPower(power);
            backR.setPower(-power);
        }
        else {
            stop();
            auto.steps++;
        }
    }

    public void backward(double inches){
        int ticks = inchesToTicks(inches);
        int pos = frontR.getCurrentPosition();
        if(Math.abs(pos) < ticks)
        {
            int minDist = inchesToTicks(36);
            double radPerTick = Math.PI/minDist;
            double rad = pos*radPerTick;
            power = 0.45*Math.cos(rad-Math.PI)+0.55;

            if(ticks < minDist)
                power = 0.4;
            else if(ticks-pos > minDist)
                power = 1;

            frontL.setPower(-power);
            frontR.setPower(power);
            backL.setPower(-power);
            backR.setPower(power);
        }
        else {
            stop();
            auto.steps++;
        }
    }

    public void strafeLeft(double inches){
        int ticks = inchesToTicks(inches);
        int pos = frontR.getCurrentPosition();
        if(Math.abs(pos) < ticks)
        {
            int minDist = inchesToTicks(36);
            double radPerTick = Math.PI/minDist;
            double rad = pos*radPerTick;
            power = 0.45*Math.cos(rad-Math.PI)+0.55;

            if(ticks < minDist)
                power = 0.4;
            else if(ticks-pos > minDist)
                power = 1;

            frontR.setPower(-power);
            frontL.setPower(-power);
            backR.setPower(power);
            backL.setPower(power);
        }
        else {
            stop();
            auto.steps++;
        }
    }

    public void strafeRight(double inches){
        int ticks = inchesToTicks(inches);
        int pos = frontR.getCurrentPosition();
        if(Math.abs(pos) < ticks)
        {
            int minDist = inchesToTicks(36);
            double radPerTick = Math.PI/minDist;
            double rad = pos*radPerTick;
            power = 0.45*Math.cos(rad-Math.PI)+0.55;

            if(ticks < minDist)
                power = 0.4;
            else if(ticks-pos > minDist)
                power = 1;

            frontR.setPower(power);
            frontL.setPower(power);
            backR.setPower(-power);
            backL.setPower(-power);
        }
        else {
            stop();
            auto.steps++;
        }
    }

    public void rotateRight(double degree, double power){
        double robotDiam = 41.5;
        double robotCirc = robotDiam*Math.PI;
        double dist = degree/360*robotCirc;
        int ticks = inchesToTicks(dist);
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(-power);
            frontL.setPower(-power);
            backR.setPower(-power);
            backL.setPower(-power);
        }
        else
        {
            //advance step or stop
            stop();
            auto.steps++;
        }
    }

    public void rotateLeft(double degree, double power){
        double robotDiam = 41.5;
        double robotCirc = robotDiam*Math.PI;
        double dist = degree/360*robotCirc;
        int ticks = inchesToTicks(dist);
        if(Math.abs(frontR.getCurrentPosition()) < ticks){
            frontR.setPower(power);
            frontL.setPower(power);
            backR.setPower(power);
            backL.setPower(power);
        }
        else
        {
            //advance step or stop
            stop();
            auto.steps++;
        }
    }

    public void stop(){
        power = 0;
        frontR.setPower(power);
        frontL.setPower(power);
        backR.setPower(power);
        backL.setPower(power);
        resetEncoder();
    }

    public static int inchesToTicks(double in) {
        return (int)(in*ticksPerInch);
    }

    public static double ticksToInches(int tick){ return inchesPerTick*tick; }

    public void forwardCont(double power){
        frontR.setPower(-power);
        frontL.setPower(power);
        backR.setPower(-power);
        backL.setPower(power);
    }

    public void backwardCont(double power){
        frontR.setPower(power);
        frontL.setPower(-power);
        backR.setPower(power);
        backL.setPower(-power);
    }

    public void strafeLeftCont(double power){
        frontR.setPower(-power);
        frontL.setPower(-power);
        backR.setPower(power);
        backL.setPower(power);
    }

    public void strafeRightCont(double power){
        frontR.setPower(power);
        frontL.setPower(power);
        backR.setPower(-power);
        backL.setPower(-power);
    }

    public void foundationGrab()
    {
        found1.setPosition(.2);
        found2.setPosition(.9);
    }

    public void foundationRelease()
    {
        found1.setPosition(.97);
        found2.setPosition(.1);
    }
}
