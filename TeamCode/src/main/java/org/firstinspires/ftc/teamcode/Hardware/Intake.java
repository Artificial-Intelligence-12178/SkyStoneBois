package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    final double MAX_POWER = 1;

    DcMotor leftWheel;
    DcMotor rightWheel;

    protected String status = "";

    public Intake(HardwareMap map){
        try {
            leftWheel = map.get(DcMotor.class, "IntakeL");
            leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception e) {
            status+="\nLeft Intake Motor (IntakeL) not mapping";
        }

        try {
            rightWheel = map.get(DcMotor.class, "IntakeR");
            rightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception e) {
            status += "\nRight Intake Motor (IntakeR) not mapping";
        }
    }

    public String getStatus() { return status; }

    public void goReverse(){
        leftWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        rightWheel.setDirection(DcMotorSimple.Direction.REVERSE);
    }

    public void goForward(){
        leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        rightWheel.setDirection(DcMotorSimple.Direction.FORWARD);
    }

    public void powerOn(){
        leftWheel.setPower(MAX_POWER);
        rightWheel.setPower(MAX_POWER);
    }

    public void powerOff(){
        leftWheel.setPower(0);
        rightWheel.setPower(0);
    }


}
