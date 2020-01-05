package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    DcMotor[] motors;

    protected String status;

    public DriveTrain(HardwareMap map, DcMotor.RunMode mode){
        motors[0] = frontLeft;
        motors[1] = frontRight;
        motors[2] = backLeft;
        motors[3] = backRight;

        try {
            frontLeft = map.get(DcMotor.class, "DC1");
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = map.get(DcMotor.class, "DC2");
        } catch (Exception e) {
            status+="\nFront Right motor (DC2) not mapping";
        }

        try {
            backRight = map.get(DcMotor.class, "DC3");
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = map.get(DcMotor.class, "DC4");
        } catch (Exception e) {
            status+="\nBack Left motor (DC4) not mapping";
        }

        for(int i = 0; i < motors.length; i++){
            if(motors[i] != null){
                motors[i].setMode(mode);
                motors[i].setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
                motors[i].setDirection(DcMotor.Direction.FORWARD);
            }
        }
    }

    public String getStatus(){
        return status;
    }

    public void applyPower(double fl, double fr, double bl, double br){
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }
}
