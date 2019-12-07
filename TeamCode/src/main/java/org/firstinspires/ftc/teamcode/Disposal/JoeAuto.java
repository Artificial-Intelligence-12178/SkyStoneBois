package org.firstinspires.ftc.teamcode.Disposal;


import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.DcMotor;

public class JoeAuto
{
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;
    public Servo daniel; //inner clamp motor  : left : port 0
    public Servo jorge; //outer clamp motor   : left  : port 1
    public Servo abe;  //inner clamp motor    : right  : port 2
    public Servo kim; //outer clamp motor    :right   : port 3
    public Servo back1;    //left back hook servo
    public Servo back2;     // right back hook servo
    public String status = "";

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public JoeAuto()
    {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        //4 Movement Motors

        //Left Front Motor
        try {
            frontL = hwmap.get(DcMotor.class, "DC3");
            frontL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //frontL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontL (DC3) motor not mapping";
        }

        //Right Front Motor
        try {
            frontR = hwmap.get(DcMotor.class, "DC1");
            frontR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //frontR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nFrontR (DC1) motor not mapping";
        }

        //Left Back Motor
        try {
            backL = hwmap.get(DcMotor.class, "DC2");
            backL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //backL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackL (DC2) motor not mapping";
        }

        //Right Back Motor
        try {
            backR = hwmap.get(DcMotor.class, "DC4");
            backR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            //backR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nBackR (DC4) motor not mapping";
        }
        try {
            daniel = hwmap.get(Servo.class, "Daniel");
        } catch (Exception e) {
            status += "\nDaniel not mapping";
        }
        try {
            jorge = hwmap.get(Servo.class, "Jorge");
        } catch (Exception e) {
            status += "\nJorge not mapping";
        }
        try {
            abe = hwmap.get(Servo.class, "Abe");
        } catch (Exception e) {
            status += "\nabe not mapping";
        }
        try {
            kim = hwmap.get(Servo.class, "Kim");
        } catch (Exception e) {
            status += "\nKim not mapping";
        }
        try {
            back1 = hwmap.get(Servo.class, "Back1");
        } catch (Exception e) {
            status += "\nBack1 not mapping";
        }
        try {
            back2 = hwmap.get(Servo.class, "Back2");
        } catch (Exception e) {
            status += "\nBack2 not mapping";
        }
    }

    //basically a toString method. This tells the code how to display the status.
    public String getStatus()
    {
        return status;
    }

}
