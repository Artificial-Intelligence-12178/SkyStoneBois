package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveRobot extends Robot {

    public DriveRobot(HardwareMap map){
        super(map, DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);
    }
}
