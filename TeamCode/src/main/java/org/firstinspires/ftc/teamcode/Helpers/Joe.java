package org.firstinspires.ftc.teamcode.Helpers;


import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;

public class Joe {
    //declaring DcMotors
    public DcMotor frontL;
    public DcMotor frontR;
    public DcMotor backL;
    public DcMotor backR;

    public String status = "";

    HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

    public Joe()
    {

    }

    public void init(HardwareMap ahwmap) {
        hwmap = ahwmap;

        //4 Movement Motors

        //Left Front Motor
        try
        {
            frontL = hwmap.get(DcMotor.class, "DC1");
            frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontL (DC1) motor not mapping";
        }

        //Right Front Motor
        try
        {
            frontR = hwmap.get(DcMotor.class, "DC2");
            frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontR (DC2) motor not mapping";
        }

        //Left Back Motor

        try
        {
            backL = hwmap.get(DcMotor.class, "DC4");
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackL (DC4) motor not mapping";
        }



        //Right Back Motor

        try
        {
            backR = hwmap.get(DcMotor.class, "DC3");
            backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackR (DC3) motor not mapping";
        }
    }

    //basically a toString method. This tells the code how to display the status.
    public String getStatus()
    {
        return status;
    }

    public static class JoeAuto
    {
        //declaring DcMotors
        public DcMotor frontL;
        public DcMotor frontR;
        public DcMotor backL;
        public DcMotor backR;

        public String status = "";

        HardwareMap hwmap = null; //need a reference for op mode so the code doesnt think this is the op mode to use right now

        public JoeAuto() {

        }

        public void init(HardwareMap ahwmap) {
            hwmap = ahwmap;

            //4 Movement Motors

            //Left Front Motor
            try {
                frontL = hwmap.get(DcMotor.class, "DC1");
                frontL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } catch (Exception e) {
                status += "\nFrontL (DC1) motor not mapping";
            }

            //Right Front Motor
            try {
                frontR = hwmap.get(DcMotor.class, "DC2");
                frontR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } catch (Exception e) {
                status += "\nFrontR (DC2) motor not mapping";
            }

            //Left Back Motor

            try {
                backL = hwmap.get(DcMotor.class, "DC4");
                backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } catch (Exception e) {
                status += "\nBackL (DC4) motor not mapping";
            }



            //Right Back Motor

            try {
                backR = hwmap.get(DcMotor.class, "DC3");
                backL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            } catch (Exception e) {
                status += "\nBackR (DC3) motor not mapping";
            }
        }

        //basically a toString method. This tells the code how to display the status.
        public String getStatus()
        {
            return status;
        }

    }
}
