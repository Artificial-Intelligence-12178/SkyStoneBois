package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class SideGrabbers {
    Servo leftPivot;
    Servo leftClaw;

    Servo rightPivot;
    Servo rightClaw;

    private final double LEFT_PIVOT_DOWN = .5;
    private final double LEFT_PIVOT_UP = .5;

    private final double LEFT_CLAW_GRAB = .5;
    private final double LEFT_CLAW_RELEASE = .5;

    private final double RIGHT_PIVOT_DOWN = .5;
    private final double RIGHT_PIVOT_UP = .5;

    private final double RIGHT_CLAW_GRAB = .5;
    private final double RIGHT_CLAW_RELEASE = .5;

    protected String status;

    public SideGrabbers(HardwareMap map) {
        status = "";

        try {
            leftPivot = map.get(Servo.class, "LeftPivot");
        } catch (Exception e) {
            status += "Left Pivot Servo (LeftPivot) not mapping";
        }

        try {
            leftClaw = map.get(Servo.class, "LeftClaw");
        } catch (Exception e) {
            status += "Left Grab Servo (LeftGrab) not mapping";
        }

        try {
            rightPivot = map.get(Servo.class, "RightPivot");
        } catch (Exception e) {
            status += "Right Pivot Servo (RightPivot) not mapping";
        }

        try {
            rightClaw = map.get(Servo.class, "RightClaw");
        } catch (Exception e) {
            status += "Right Grab Servo (RightGrab) not mapping";
        }
    }

    public String getStatus() {
        return status;
    }

    public void rightClawGrab() {
        rightClaw.setPosition(RIGHT_CLAW_GRAB);
    }

    public void rightClawRelease() {
        rightClaw.setPosition(RIGHT_CLAW_RELEASE);
    }

    public void leftClawGrab() {
        leftClaw.setPosition(LEFT_CLAW_GRAB);
    }

    public void leftClawRelease() {
        leftClaw.setPosition(LEFT_CLAW_RELEASE);
    }

    public void rightPivotUp() {
        rightPivot.setPosition(RIGHT_PIVOT_UP);
    }

    public void rightPivotDown() {
        rightPivot.setPosition(RIGHT_PIVOT_DOWN);
    }

    public void leftPivotUp() {
        leftPivot.setPosition(LEFT_PIVOT_UP);
    }

    public void leftPivotDown() {
        leftPivot.setPosition(LEFT_PIVOT_DOWN);
    }
}
