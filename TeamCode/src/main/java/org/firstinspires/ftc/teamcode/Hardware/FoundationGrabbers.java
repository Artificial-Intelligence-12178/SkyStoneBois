package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FoundationGrabbers {

    Servo leftGrabber;
    Servo rightGrabber;

    final double LEFT_DOWN = 0.5;
    final double LEFT_UP = 0.5;

    final double RIGHT_DOWN = 0.5;
    final double RIGHT_UP = 0.5;

    protected String status = "";

    public FoundationGrabbers(HardwareMap map){
        try {
            leftGrabber = map.get(Servo.class, "ServoL");
        } catch (Exception e) {
            status += "\nLeft Grabber (ServoL) not mapping";
        }

        try {
            rightGrabber = map.get(Servo.class, "ServoR");
        } catch (Exception e) {
            status += "\nRight Grabber (ServoR) not mapping";
        }
    }

    public String getStatus(){ return status; }

    public void grabbersDown(){
        leftGrabber.setPosition(LEFT_DOWN);
        rightGrabber.setPosition(RIGHT_DOWN);
    }

    public void grabbersUp(){
        leftGrabber.setPosition(LEFT_UP);
        rightGrabber.setPosition(RIGHT_UP);
    }
}
