package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class CapstoneRelease {
    Servo release;
    protected String status;

    public CapstoneRelease(HardwareMap map) {
        try {
            release = map.get(Servo.class, "Release");
        } catch (Exception e) {
            status += "Capstone release (Release) not mapping";
        }
    }

    public String getStatus() {
        return status;
    }

    public void releaseStone(){
        release.setPosition(0);
    }
}
