package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class FoundationGrabbers {

    //Servos for each grabber
    Servo leftGrabber;
    Servo rightGrabber;

    //Global positions
    final double LEFT_DOWN = 0.5;
    final double LEFT_UP = 0.5;

    final double RIGHT_DOWN = 0.5;
    final double RIGHT_UP = 0.5;

    //String to display status of each component
    protected String status = "";

    public FoundationGrabbers(HardwareMap map){
        //Initializing components
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

    //Method used to get the status of this FoundationGrabbers
    public String getStatus(){ return status; }

    //Method used to move grabbers down
    public void grabbersDown(){
        leftGrabber.setPosition(LEFT_DOWN);
        rightGrabber.setPosition(RIGHT_DOWN);
    }

    //Method used to move grabbers up
    public void grabbersUp(){
        leftGrabber.setPosition(LEFT_UP);
        rightGrabber.setPosition(RIGHT_UP);
    }
}
