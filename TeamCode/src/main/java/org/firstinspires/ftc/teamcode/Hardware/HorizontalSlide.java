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

    final double EXTEND_TIME = 1;
    final double RETRACT_TIME = 1;

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

        if(stage.equals(Stage.RETRACTING) || stage.equals(Stage.EXTENDING))
            timer.reset();
    }

    public void handleSlide(){
        if(stage.equals(Stage.EXTENDING)) {
            extendSlide();
            if(timer.seconds() > EXTEND_TIME) {
                maintainSlide();
                advanceStage();
            }
        }
        else if(stage.equals(Stage.RETRACTING)) {
            retractSlide();
            if(timer.seconds() > RETRACT_TIME) {
                maintainSlide();
                advanceStage();
            }
        }

    }

    public void releaseStone() {
        grabber.setPosition(GRABBER_UP);
    }

    public void grabStone() {
        grabber.setPosition(GRABBER_DOWN);
    }

    public void extendSlide() {
        extender.setPosition(1);
    }

    public void retractSlide() {
        extender.setPosition(0);
    }

    public void maintainSlide() {
        extender.setPosition(.5);
    }
}
