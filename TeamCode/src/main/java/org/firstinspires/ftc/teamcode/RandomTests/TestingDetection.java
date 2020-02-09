package org.firstinspires.ftc.teamcode.RandomTests;

import android.hardware.Camera;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.vuforia.Vuforia;
import com.vuforia.ar.pl.DebugLog;

import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaSkyStone;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamSource;
import org.firstinspires.ftc.teamcode.Autonomous.AutonomousClass;
import org.firstinspires.ftc.teamcode.Autonomous.DetectionObject;
import org.firstinspires.ftc.teamcode.Robots.AutoRobot;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraBase;
import org.openftc.easyopencv.OpenCvInternalCamera;
import com.acmerobotics.dashboard.FtcDashboard;

import java.util.List;

@Autonomous (name = "Testing Detection")
public class TestingDetection extends AutonomousClass {

    DetectionObject detect;

    @Override
    public void init() {
        detect = new DetectionObject(hardwareMap);
    }

    @Override
    public void init_loop() {

    }

    @Override
    public void start() {

    }

    @Override
    public void loop() {
        telemetry.addLine("Left: " + detect.getLeftValue() + " Middle: " + detect.getMiddleValue() + "Right "+
                detect.getRightValue());

        OpenCvCamera cam = detect.getCamera();
    }

    @Override
    public void stop() {

    }

}
