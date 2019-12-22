package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.*;

public class AdultJoe {

    public DcMotorEx frontRight;
    public DcMotorEx frontLeft;
    public DcMotorEx backRight;
    public DcMotorEx backLeft;

    HardwareMap hwmap = null;

    String status = "";

    public AdultJoe(){

    }

    public void init(HardwareMap hwMap){
        hwmap = hwMap;

        /**
         * 4 MOVEMENT MOTORS
         */
        //Left Front Motor?
        try {
            frontLeft = hwmap.get(DcMotorEx.class, "DC1");
            frontLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontL (DC1) motor not mapping";
        }

        //Right Front Motor
        try {
            frontRight = hwmap.get(DcMotorEx.class, "DC2");
            frontRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nFrontR (DC2) motor not mapping";
        }

        //Left Back Motor
        try {
            backLeft = hwmap.get(DcMotorEx.class, "DC4");
            backLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackL (DC4) motor not mapping";
        }

        //Right Back Motor
        try {
            backRight = hwmap.get(DcMotorEx.class, "DC3");
            backRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            status += "\nBackR (DC3) motor not mapping";
        }
    }

    public String getStatus(){
        return status;
    }
}
