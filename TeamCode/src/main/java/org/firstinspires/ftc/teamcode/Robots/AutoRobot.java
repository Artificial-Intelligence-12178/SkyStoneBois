package org.firstinspires.ftc.teamcode.Robots;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class AutoRobot extends Robot {

    public AutoRobot(HardwareMap map){
        super(map, DcMotorEx.RunMode.RUN_USING_ENCODER);
    }

    public void forward(double inches){
        if(driveTrain.getAverageEncoderValue() < inchesToTicks(inches)){
            driveTrain.applyPower(.7, -.7, .7, -.7);
        } else {
            driveTrain.applyPower(0 , 0, 0 ,0 );
        }
    }


}
