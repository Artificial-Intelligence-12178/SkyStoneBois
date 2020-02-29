package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Intake {
    private final double MAX_POWER = 0.5;

    private DcMotor leftWheel;
    private DcMotor rightWheel;

    protected String status = "";

    public Intake(HardwareMap map){

        status = "";

        try {
            leftWheel = map.get(DcMotor.class, "IL");
            leftWheel.setDirection(DcMotorSimple.Direction.FORWARD);
        } catch (Exception e) {
            status+="\nLeft Intake Motor (IntakeL) not mapping";
        }

        try {
            rightWheel = map.get(DcMotor.class, "IR");
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
        applyPower(-MAX_POWER, MAX_POWER);
    }

    public void powerOff(){
        applyPower(0 , 0);
    }

    public void applyPower(double left, double right) {
        leftWheel.setPower(left);
        rightWheel.setPower(right);
    }


}
