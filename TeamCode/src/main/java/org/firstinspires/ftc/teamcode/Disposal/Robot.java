package org.firstinspires.ftc.teamcode.Disposal;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Robot {
    DcMotorEx frontL;
    DcMotorEx frontR;
    DcMotorEx backL;
    DcMotorEx backR;

    String status = "";
    public Robot(){

    }

    public void init(HardwareMap hwMap){
        try {
            frontL = hwMap.get(DcMotorEx.class, "DC1");
            frontL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            frontL.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            status+= "\nfrontL (DC1) not mapping";
        }

        try {
            frontR = hwMap.get(DcMotorEx.class, "DC2");
            frontR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            frontR.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            status+= "\nfrontR (DC2) not mapping";
        }

        try {
            backR = hwMap.get(DcMotorEx.class, "DC3");
            backR.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            backR.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            status+= "\nbackR (DC3) not mapping";
        }

        try {
            backL = hwMap.get(DcMotorEx.class, "DC4");
            backL.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
            backL.setDirection(DcMotorSimple.Direction.REVERSE);
        } catch (Exception e) {
            status+= "\nbackL (DC4) not mapping";
        }
    }

    public String getStatus(){
        return status;
    }

    public void stopMotors(){
        frontL.setPower(0);
        frontR.setPower(0);
        backR.setPower(0);
        backL.setPower(0);
    }
}
