package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.Position;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class IMU {

    BNO055IMU imu;
    Orientation angles;

    public IMU(HardwareMap map){
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();

        parameters.mode                = BNO055IMU.SensorMode.IMU;
        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        parameters.loggingEnabled      = false;

        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".
        imu = map.get(BNO055IMU.class, "imu");

        imu.initialize(parameters);
    }

    public Orientation getAngles(){
        updateAngles();
        return angles;
    }

    public double getHeading(){
        updateAngles();
        return angles.firstAngle;
    }

    public double[] getAngleValues(){
        updateAngles();

        double[] vals = new double[3];

        vals[0] = angles.firstAngle;
        vals[1] = angles.secondAngle;
        vals[2] = angles.thirdAngle;

        return vals;
    }

    public Position getPosition(DistanceUnit unit){
        return imu.getPosition().toUnit(unit);
    }

    public void updateAngles(){
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.ZYX, AngleUnit.DEGREES);
    }
}
