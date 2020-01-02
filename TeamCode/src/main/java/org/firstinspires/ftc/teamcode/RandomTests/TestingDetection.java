package org.firstinspires.ftc.teamcode.RandomTests;

import android.hardware.Camera;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.vuforia.ar.pl.DebugLog;

import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Autonomous.DetectionObject;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.openftc.easyopencv.OpenCvInternalCamera;

import java.util.List;

@Autonomous (name = "Testing Detection")
public class TestingDetection extends AutonomousClass {

    DetectionObject detect;

    @Override
    public void init() {
        detect = new DetectionObject(hardwareMap);
        detect.startDetection();
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {

    }

    @Override
    public void stop() {
        detect.stopDetection();
    }

}
