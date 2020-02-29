package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;

public class IMU {

    BNO055IMU imu;
    Orientation angles;
    double target = 0;

    public IMU(HardwareMap map){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.calibrationDataFile = "BNO055IMUCalibration.json"; // see the calibration sample opmode
        parameters.loggingEnabled      = true;
        parameters.loggingTag          = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = map.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);

        while(!imu.isGyroCalibrated()) {
            //Just making sure gyro is calibrated before starting OpMode
        }
    }

    public double getHeading(){
        updateAngles();
        return angles.firstAngle;
    }


    public void updateAngles(){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }

    public double getCorrection(double gain) {
        double curr = getHeading();
        double diff = curr - target;
        return diff * gain;
    }

    public void setTarget(double target) {
        this.target = target;
    }

    //useless method
    public double[] getAngleValues(){
        updateAngles();

        double[] vals = new double[3];

        vals[0] = angles.firstAngle;
        vals[1] = angles.secondAngle;
        vals[2] = angles.thirdAngle;

        return vals;
    }

    //useless method
    public Orientation getAngles(){
        updateAngles();
        return angles;
    }

}
