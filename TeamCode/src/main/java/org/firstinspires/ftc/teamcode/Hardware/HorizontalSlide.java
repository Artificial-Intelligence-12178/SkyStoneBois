package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class HorizontalSlide {
    Servo extender;
    Servo grabber;

    protected String status;

    ElapsedTime timer;

    final double GRABBER_DOWN = .5;
    final double GRABBER_UP = 0.5;

    enum Stage {
        RETRACTED,
        EXTENDING,
        EXTENDED,
        RETRACTING
    }

    Stage stage = Stage.RETRACTED;
    Stage[] stages = Stage.values();

    public HorizontalSlide(HardwareMap map) {
        try {
            extender = map.get(Servo.class, "Extend");
        } catch (Exception e) {
            status += "\nExtension Servo (Extend) not mapping";
        }

        try {
            grabber = map.get(Servo.class, "Grab");
        } catch (Exception e) {
            status += "\nGrab Servo (Grab) not mapping";
        }

        timer = new ElapsedTime();
    }

    public String getStatus() { return status; }

    public void advanceStage() {
        int current = stage.ordinal();

        int next = current+1;

        if(next >= stages.length)
            next = 0;

        stage = stages[next];
    }

    public void handleSlide(){

    }
}
