package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class VerticalLift {

    DcMotor left;
    DcMotor right;

    protected String status = "";

    public VerticalLift(HardwareMap map) {
        try {
            left = map.get(DcMotor.class, "VL");
            left.setDirection(DcMotorSimple.Direction.FORWARD);
            left.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            left.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nLeft motor (VL) not mapping";
        }

        try {
            right = map.get(DcMotor.class, "VR");
            right.setDirection(DcMotorSimple.Direction.FORWARD);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            right.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        } catch (Exception e) {
            status += "\nRight motor (VR) not mapping";
        }
    }

    public String getStatus() { return status; }

    public void liftUp(){
        left.setPower(1);
        right.setPower(-1);
    }

    public void liftDown() {
        left.setPower(-1);
        right.setPower(1);
    }

    public void stopLift() {
        left.setPower(0);
        right.setPower(0);
    }

    public void applyPower(double power) {
        left.setPower(-power);
        right.setPower(power);
    }

    public void singleControl(boolean leftM, boolean up) {
        if(leftM) {
            if(up)
                left.setPower(1);
            else
                left.setPower(-1);
        }
        else {
            if(up)
                right.setPower(-1);
            else
                right.setPower(1);
        }
    }

    public double getAvgEncoderCount() {
        int sum = 0;

        sum += Math.abs(left.getCurrentPosition());
        sum += Math.abs(right.getCurrentPosition());

        return sum/2.0;
    }

    public int getLeftEncoderCount() {
        return left.getCurrentPosition();
    }

    public int getRightEncoderCount() {
        return right.getCurrentPosition();
    }
}
