package org.firstinspires.ftc.teamcode.Helpers;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Joe {
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;
    public DcMotor arm;
    public Servo daniel; //inner clamp motor  : left : B1 PORT 5
    public Servo jorge; //outer clamp motor   : left  : B1 PORT 4
    public Servo abe;  //inner clamp motor    : right  : B2 PORT 0
    public Servo kim; //outer clamp motor    :right   : B2 PORT 1
    public Servo back1;    //left back hook looking at FRONT servo B2 PORT 2
    public Servo back2;     // right back hook looking at FRONT servo B2 PORT 3

    public String status = "";

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public Joe() {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;


        /**
         * 4 MOVEMENT MOTORS
         */
        //Left Front Motor
        try {
            frontL = hwmap.get(DcMotor.class, "DC3");
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontL (DC3) motor not mapping";
        }

        //Right Front Motor
        try {
            frontR = hwmap.get(DcMotor.class, "DC1");
            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontR (DC1) motor not mapping";
        }

        //Left Back Motor
        try {
            backL = hwmap.get(DcMotor.class, "DC2");
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackL (DC2) motor not mapping";
        }

        //Right Back Motor
        try {
            backR = hwmap.get(DcMotor.class, "DC4");
            backR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackR (DC4) motor not mapping";
        }

        /**
         * ARM THANGS
         */
        //Arm motor
        try {
            arm = hwmap.get(DcMotor.class, "ARM1");
            arm.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nArm (ARM1) motor not mapping";
        }

        try {
            daniel = hwmap.get(Servo.class, "Daniel");
            daniel.setDirection(Servo.Direction.FORWARD);
        } catch (Exception e) {
            status += "\nDaniel not mapping";
        }
        try {
            jorge = hwmap.get(Servo.class, "Jorge");
        } catch (Exception e) {
            status += "\nJorge not mapping";
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
            back1.setDirection(Servo.Direction.FORWARD);
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
    public String getStatus() {
        return status;
    }


}