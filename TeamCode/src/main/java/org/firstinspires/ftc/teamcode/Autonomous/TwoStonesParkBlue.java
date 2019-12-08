package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.matrices.OpenGLMatrix;
import org.firstinspires.ftc.robotcore.external.matrices.VectorF;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackable;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackableDefaultListener;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaTrackables;
import org.firstinspires.ftc.teamcode.Helpers.JoeAuto;

import java.util.ArrayList;
import java.util.List;

import static org.firstinspires.ftc.robotcore.external.navigation.AngleUnit.DEGREES;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.XYZ;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesOrder.YZX;
import static org.firstinspires.ftc.robotcore.external.navigation.AxesReference.EXTRINSIC;
import static org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer.CameraDirection.BACK;

@Autonomous(name = "2 Stones Park Blue", group = "auto bot")
public class TwoStonesParkBlue extends org.firstinspires.ftc.teamcode.Autonomous.Autonomous {

    private JoeAuto joeAuto = new JoeAuto(this);
    private ElapsedTime runtime = new ElapsedTime();
    public static double steps = 0;
    // IMPORTANT:  For Phone Camera, set 1) the camera source and 2) the orientation, based on how your phone is mounted:
    // 1) Camera Source.  Valid choices are:  BACK (behind screen) or FRONT (selfie side)
    // 2) Phone Orientation. Choices are: PHONE_IS_PORTRAIT = true (portrait) or PHONE_IS_PORTRAIT = false (landscape)
    //
    // NOTE: If you are running on a CONTROL HUB, with only one USB WebCam, you must select CAMERA_CHOICE = BACK; and PHONE_IS_PORTRAIT = false;
    //
    private static final VuforiaLocalizer.CameraDirection CAMERA_CHOICE = BACK;
    private static final boolean PHONE_IS_PORTRAIT = false  ;
    /*
     * IMPORTANT: You need to obtain your own license key to use Vuforia. The string below with which
     * 'parameters.vuforiaLicenseKey' is initialized is for illustration only, and will not function.
     * A Vuforia 'Development' license key, can be obtained free of charge from the Vuforia developer
     * web site at https://developer.vuforia.com/license-manager.
     *
     * Vuforia license keys are always 380 characters long, and look as if they contain mostly
     * random data. As an example, here is a example of a fragment of a valid key:
     *      ... yIgIzTqZ4mWjk9wd3cZO9T1axEqzuhxoGlfOOI2dRzKS4T0hQ8kT ...
     * Once you've obtained a license key, copy the string from the Vuforia web site
     * and paste it in to your code on the next line, between the double quotes.
     */
    private static final String VUFORIA_KEY =
            "ATMeJeb/////AAAAGaZ47DzTRUyOhcXnfJD+z89ATBWAF+fi+oOutLvXaf0YT/RPuf2mu6VJsJowCDiWiOzGMHUjXKsLBqA4Ziar76oZY/juheUactiQaY6Z3qPHnGmchAMlYuqgKErvggTuqmFca8VvTjtB6YOheJmAbllTDTaCudODpnIDkuFNTa36WCTr4L8HcCnIsB7bjF8pZoivYEBwPkfCVtfAiEpqxbyDAZgNXnuCyp6v/oi3FYZbp7JkFVorcM182+q0PVN5gIr14SKEMlDcDFDiv/sQwNeQOs5iNBd1OSkCoTe9CYbdmtE0gUXxKN2w9NqwATYC6YRJP9uoopxqmr9zkepI10peh2/RnU6pHOmR0KKRAVh8";

    // Since ImageTarget trackables use mm to specifiy their dimensions, we must use mm for all the physical dimension.
    // We will define some constants and conversions here
    private static final float mmPerInch        = 25.4f;
    private static final float mmTargetHeight   = (6) * mmPerInch;          // the height of the center of the target image above the floor

    // Constant for Stone Target
    private static final float stoneZ = 2.00f * mmPerInch;

    // Constants for the center support targets
    private static final float bridgeZ = 6.42f * mmPerInch;
    private static final float bridgeY = 23 * mmPerInch;
    private static final float bridgeX = 5.18f * mmPerInch;
    private static final float bridgeRotY = 59;                                 // Units are degrees
    private static final float bridgeRotZ = 180;

    // Constants for perimeter targets
    private static final float halfField = 72 * mmPerInch;
    private static final float quadField  = 36 * mmPerInch;

    // Class Members
    private OpenGLMatrix lastLocation = null;
    private VuforiaLocalizer vuforia = null;
    private boolean targetVisible = false;
    private float phoneXRotate    = 0;
    private float phoneYRotate    = 0;
    private float phoneZRotate    = 0;

    private double skyX, skyY, skyZ;
    private boolean once = false;
    private String side;
    @Override
    public void init() {
        joeAuto.init(hardwareMap);
        initDetection();
        joeAuto.resetEncoder();
        telemetry.addData("pos ", joeAuto.frontR.getCurrentPosition());
        telemetry.update();
        super.steps=0;
    }

    @Override
    public void init_loop(){
        super.steps = 0;
    }

    private double feetRequired = 3.2;
    private double feetBack = 5.2;
    double requiredX = 0;

    @Override
    public void loop() {
        if(steps == 0)
            joeAuto.forward(15);
        else if(steps == 1)
        {
            if(targetVisible)
            {

                if(skyX <= 0)
                {
                    side = "LEFT";
                    requiredX = -1.9685;
                }
                else
                {
                    side = "RIGHT";
                    requiredX = 7.283465;
                }

                double dist = Math.abs(requiredX-skyX);
                if(side.equals("LEFT"))
                    feetRequired-=dist/12;
                else
                    feetRequired+=dist/12;

                steps++;
            }

        }
        else if(steps == 2)
        {
            
            if(skyX < requiredX-.5)
                joeAuto.strafeLeftCont(0.2);
            else if(skyX > requiredX+.5)
                joeAuto.strafeRightCont(0.2);
            else
            {
                joeAuto.stop();
                steps++;
            }

        }
        else if(steps == 3) {
            if (skyY + 5 < -7.05)
                joeAuto.forwardCont(0.2);
            else {
                joeAuto.stop();
                steps++;
            }
        }
        else if(steps == 4)
        {
            if(side.equals("LEFT"))
            {
                joeAuto.leftServoPosition=1;
            }
            else
            {
                joeAuto.rightServoPosition=1;
            }

            steps++;
            targetsSkyStone.deactivate();
        }
        else if(steps == 5)
        {
            joeAuto.backward(6);
        }
        else if(steps == 6)
        {
            joeAuto.strafeLeft(feetRequired*12);
        }
        else if(steps == 7)
        {
            //code to release
            if(side.equals("LEFT"))
            {
                joeAuto.leftServoPosition=0.5;
            }
            else
            {
                joeAuto.rightServoPosition=0.5;
            }

            steps++;
        }
        else if(steps == 8)
        {
            joeAuto.strafeRight(feetBack*12);
            feetRequired = 5.2;
        }
        else if(steps == 9)
        {
            if(!once)
            {
                once = true;
                targetsSkyStone.activate();
            }

            if(targetVisible)
            {
                if(skyX < -1 || skyX > 1)
                    feetRequired+=skyX/12;
                steps++;
            }
        }
        else if(steps == 10)
        {
            if(skyX < -1)
                joeAuto.strafeLeftCont(0.2);
            else if(skyX > 1)
                joeAuto.strafeRightCont(0.2);
            else
            {
                steps++;
            }
        }
        else if(steps == 11)
        {
            //code to move forward
            if(skyY+5 < -7.05)
                joeAuto.forwardCont(0.2);
            else
                steps++;
        }
        else if(steps == 12)
        {
            if(side.equals("LEFT"))
            {
                joeAuto.leftServoPosition=1;
            }
            else
            {
                joeAuto.rightServoPosition=1;
            }

            steps++;
            targetsSkyStone.deactivate();
        }
        else if(steps == 13)
        {
            joeAuto.backward(6);
        }
        else if(steps == 14)
        {
            joeAuto.strafeLeft(feetRequired);
        }
        else if(steps == 15)
        {
            //code to release
            if(side.equals("LEFT"))
            {
                joeAuto.leftServoPosition=0.5;
            }
            else
            {
                joeAuto.rightServoPosition=0.5;
            }

            steps++;
        }steps
        else if(steps == 16)
        {
            joeAuto.strafeRight(0.2*12);
        }
        detection();
        updateServos();
    }

    @Override
    public void stop() {

    }

    VuforiaTrackables targetsSkyStone;
    List<VuforiaTrackable> allTrackables;
    public void initDetection(){
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         * We can pass Vuforia the handle to a camera preview resource (on the RC phone);
         * If no camera monitor is desired, use the parameter-less constructor instead (commented out below).
         */
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters(cameraMonitorViewId);

        // VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Para]meters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection   = CAMERA_CHOICE;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Load the data sets for the trackable objects. These particular data
        // sets are stored in the 'assets' part of our application.
        targetsSkyStone = this.vuforia.loadTrackablesFromAsset("Skystone");

        VuforiaTrackable stoneTarget = targetsSkyStone.get(0);
        stoneTarget.setName("Stone Target");

        // For convenience, gather together all the trackable objects in one easily-iterable collection */
        allTrackables = new ArrayList<VuforiaTrackable>();
        allTrackables.addAll(targetsSkyStone);

        /**
         * In order for localization to work, we need to tell the system where each target is on the field, and
         * where the phone resides on the robot.  These specifications are in the form of <em>transformation matrices.</em>
         * Transformation matrices are a central, important concept in the math here involved in localization.
         * See <a href="https://en.wikipedia.org/wiki/Transformation_matrix">Transformation Matrix</a>
         * for detailed information. Commonly, you'll encounter transformation matrices as instances
         * of the {@link OpenGLMatrix} class.
         *
         * If you are standing in the Red Alliance Station looking towards the center of the field,
         *     - The X axis runs from your left to the right. (positive from the center to the right)
         *     - The Y axis runs from the Red Alliance Station towards the other side of the field
         *       where the Blue Alliance Station is. (Positive is from the center, towards the BlueAlliance station)
         *     - The Z axis runs from the floor, upwards towards the ceiling.  (Positive is above the floor)
         *
         * Before being transformed, each target image is conceptually located at the origin of the field's
         *  coordinate system (the center of the field), facing up.
         */

        // Set the position of the Stone Target.  Since it's not fixed in position, assume it's at the field origin.
        // Rotated it to to face forward, and raised it to sit on the ground correctly.
        // This can be used for generic target-centric approach algorithms
        stoneTarget.setLocation(OpenGLMatrix
                .translation(0, 0, stoneZ)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, XYZ, DEGREES, 90, 0, -90)));

        //
        // Create a transformation matrix describing where the phone is on the robot.
        //
        // NOTE !!!!  It's very important that you turn OFF your phone's Auto-Screen-Rotation option.
        // Lock it into Portrait for these numbers to work.
        //
        // Info:  The coordinate frame for the robot looks the same as the field.
        // The robot's "forward" direction is facing out along X axis, with the LEFT side facing out along the Y axis.
        // Z is UP on the robot.  This equates to a bearing angle of Zero degrees.
        //
        // The phone starts out lying flat, with the screen facing Up and with the physical top of the phone
        // pointing to the LEFT side of the Robot.
        // The two examples below assume that the camera is facing forward out the front of the robot.

        // We need to rotate the camera around it's long axis to bring the correct camera forward.
        if (CAMERA_CHOICE == BACK) {
            phoneYRotate = -90;
        } else {
            phoneYRotate = 90;
        }

        // Rotate the phone vertical about the X axis if it's in portrait mode
        if (PHONE_IS_PORTRAIT) {
            phoneXRotate = 90 ;
        }

        // Next, translate the camera lens to where it is on the robot.
        // In this example, it is centered (left to right), but forward of the middle of the robot, and above ground level.
        final float CAMERA_FORWARD_DISPLACEMENT  = 3.5f * mmPerInch;   // eg: Camera is 4 Inches in front of robot center
        final float CAMERA_VERTICAL_DISPLACEMENT = 10.0f * mmPerInch;   // eg: Camera is 8 Inches above ground
        final float CAMERA_LEFT_DISPLACEMENT     = 0;     // eg: Camera is ON the robot's center line

        OpenGLMatrix robotFromCamera = OpenGLMatrix
                .translation(CAMERA_FORWARD_DISPLACEMENT, CAMERA_LEFT_DISPLACEMENT, CAMERA_VERTICAL_DISPLACEMENT)
                .multiplied(Orientation.getRotationMatrix(EXTRINSIC, YZX, DEGREES, phoneYRotate, phoneZRotate, phoneXRotate));

        /**  Let all the trackable listeners know where the phone is.  */
        for (VuforiaTrackable trackable : allTrackables) {
            ((VuforiaTrackableDefaultListener) trackable.getListener()).setPhoneInformation(robotFromCamera, parameters.cameraDirection);
        }

        targetsSkyStone.activate();
    }

    public void detection(){
        // check all the trackable targets to see which one (if any) is visible.
        targetVisible = false;
        for (VuforiaTrackable trackable : allTrackables) {
            if (((VuforiaTrackableDefaultListener)trackable.getListener()).isVisible()) {
                telemetry.addData("Visible Target", trackable.getName());
                targetVisible = true;
                telemetry.addData("Target visible", "we in this shet");
                // getUpdatedRobotLocation() will return null if no new information is available since
                // the last time that call was made, or if the trackable is not currently visible.
                OpenGLMatrix robotLocationTransform = ((VuforiaTrackableDefaultListener)trackable.getListener()).getUpdatedRobotLocation();
                if (robotLocationTransform != null) {
                    lastLocation = robotLocationTransform;
                }
                break;
            }
        }

        // Provide feedback as to where the robot is located (if we know).
        if (targetVisible) {
            // express position (translation) of robot in inches.
            VectorF translation = lastLocation.getTranslation();
            skyX = translation.get(0);
            skyY = translation.get(1);
            skyZ = translation.get(2);
            telemetry.addData("X pos:", skyX);
            telemetry.addData("Y pos:", skyY);
            telemetry.addData("Z Pos:", skyZ);

            // express the rotation of the robot in degrees.
            Orientation rotation = Orientation.getOrientation(lastLocation, EXTRINSIC, XYZ, DEGREES);
            telemetry.addData("Rot (deg)", "{Roll, Pitch, Heading} = %.0f, %.0f, %.0f", rotation.firstAngle, rotation.secondAngle, rotation.thirdAngle);
            targetsSkyStone.deactivate();
            steps++;
        }
        else {
            telemetry.addData("Visible Target", "none");
        }
        telemetry.update();
    }

    public void updateServos(){
        joeAuto.grabRight.setPosition(joeAuto.rightServoPosition);
        joeAuto.grabLeft.setPosition(joeAuto.leftServoPosition);
    }
}
