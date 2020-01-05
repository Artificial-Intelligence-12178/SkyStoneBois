package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoRobot extends Robot {

    public AutoRobot(HardwareMap map){
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
    }
}