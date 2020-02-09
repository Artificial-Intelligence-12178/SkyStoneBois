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
        } catch (Exception e) {
            status += "\nLeft motor (VL) not mapping";
        }

        try {
            right = map.get(DcMotor.class, "VR");
            right.setDirection(DcMotorSimple.Direction.FORWARD);
            right.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        } catch (Exception e) {
            status += "\nRight motor (VR) not mapping";
        }
    }

    public String getStatus() { return status; }

    public void liftUp(){
        left.setPower(0.4);
        right.setPower(-0.4);
    }

    public void liftDown() {
        left.setPower(-0.4);
        right.setPower(0.4);
    }

    public void stopLift() {
        left.setPower(0);
        right.setPower(0);
    }
}
