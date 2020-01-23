package org.firstinspires.ftc.teamcode.Hardware;

import com.qualcomm.robotcore.hardware.DcMotor;

import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveTrain {
    //Motors for each wheel
    DcMotor frontLeft;
    DcMotor frontRight;
    DcMotor backLeft;
    DcMotor backRight;

    //String used to display status of each component
    protected String status;

    public DriveTrain(HardwareMap map, DcMotor.RunMode mode){

        //Initializing components
        try {
            frontLeft = map.get(DcMotor.class, "DC1");
            frontLeft.setMode(mode);
        } catch (Exception e) {
            status+="\nFront Left motor (DC1) not mapping";
        }

        try {
            frontRight = map.get(DcMotor.class, "DC2");
            frontRight.setMode(mode);
        } catch (Exception e) {
            status+="\nFront Right motor (DC2) not mapping";
        }

        try {
            backRight = map.get(DcMotor.class, "DC3");
            backRight.setMode(mode);
        } catch (Exception e) {
            status+="\nBack Right motor (DC3) not mapping";
        }

        try {
            backLeft = map.get(DcMotor.class, "DC4");
            backLeft.setMode(mode);
        } catch (Exception e) {
            status+="\nBack Left motor (DC4) not mapping";
        }
    }

    //Method used to get the status of this DriveTrain
    public String getStatus(){
        return status;
    }

    /**
     * Method used to apply power to this DriveTrain
     *
     * @param fl Power applied to front left motor
     * @param fr Power applied to front right motor
     * @param bl Power applied to back left motor
     * @param br Power applied to back right motor
     */
    public void applyPower(double fl, double fr, double bl, double br){
        frontLeft.setPower(fl);
        frontRight.setPower(fr);
        backLeft.setPower(bl);
        backRight.setPower(br);
    }

    //Method used to get the average encoder value across all motors
    public int getAverageEncoderValue(){
        int sum = 0;

        sum += frontLeft.getCurrentPosition();
        sum += frontRight.getCurrentPosition();
        sum += backLeft.getCurrentPosition();
        sum += backRight.getCurrentPosition();

        return sum/4;
    }
}
