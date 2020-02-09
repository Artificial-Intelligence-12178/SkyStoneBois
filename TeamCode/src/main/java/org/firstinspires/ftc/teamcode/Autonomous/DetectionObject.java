package org.firstinspires.ftc.teamcode.Autonomous;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvInternalCamera;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class DetectionObject {
    OpenCvCamera cam;
    DetectionPipeline pipeline;
    private final int rows = 320, cols = 240;

    private static int middleValue, leftValue, rightValue;

    public DetectionObject(HardwareMap map){
        middleValue = -1;
        leftValue = -1;
        rightValue = -1;

        int cameraMonitorViewId = map.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", map.appContext.getPackageName());
        cam = OpenCvCameraFactory.getInstance().createInternalCamera(OpenCvInternalCamera.CameraDirection.FRONT, cameraMonitorViewId);
        pipeline = new DetectionPipeline();
        cam.setPipeline(pipeline);
        startDetection();
    }

    public OpenCvCamera getCamera() {
        return cam;
    }
    public int getLeftValue(){
        return leftValue;
    }

    public int getMiddleValue(){
        return middleValue;
    }

    public int getRightValue(){
        return rightValue;
    }

    public void startDetection(){
        cam.openCameraDevice();
        cam.startStreaming(rows, cols, OpenCvCameraRotation.UPSIDE_DOWN);
    }

    public void stopDetection(){
        cam.stopStreaming();
        cam.closeCameraDevice();
    }

    static class DetectionPipeline extends OpenCvPipeline {

        Mat yCbCrChan2Mat = new Mat();
        Mat thresholdMat = new Mat();
        Mat all = new Mat();
        List<MatOfPoint> contoursList = new ArrayList<>();

        enum Stage {
            DETECTION,
            THRESHOLD,
            RAW_IMAGE
        }

        private Stage stageToViewport = Stage.DETECTION;
        private Stage[] stages = Stage.values();

        @Override
        public void onViewportTapped() {
            int currentStage = stageToViewport.ordinal();

            int nextStage = currentStage+1;

            if(nextStage >= stages.length)
                nextStage = 0;

            stageToViewport = stages[nextStage];
        }

        private float offsetX = 0f/8f, offsetY = 0f/8f;
        private float leftX = 2f/8f + offsetX , midX = 4f/8f + offsetX , rightX = 6f/8f + offsetX;
        private float leftY = 4f/8f + offsetY, midY = 4f/8f + offsetY , rightY = 4f/8f + offsetY;

        private float rectHeight = .6f/8f;
        private float rectWidth = 1.5f/8f;

        public Mat processFrame(Mat input){

            contoursList.clear();

            //Converting RGB to YCrCb and extracting Cb channel
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2);

            //Black and white filter
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 102, 255, Imgproc.THRESH_BINARY_INV);

            //Finding contours (outlines)
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            //Copying to all
            yCbCrChan2Mat.copyTo(all);

            int rows = input.rows();
            int cols = input.cols();

            //Getting the values of the frame at 3 points
            double[] leftPix = thresholdMat.get((int)(rows*leftY), (int)(cols*leftX));
            double[] middlePix = thresholdMat.get((int)(rows*midY), (int)(cols*midX));
            double[] rightPix = thresholdMat.get((int)(rows*rightY), (int)(cols*rightX));

            leftValue = (int)leftPix[0];
            middleValue = (int)middlePix[0];
            rightValue = (int)rightPix[0];

            //Creating 3 points used to draw
            Point leftPoint = new Point(cols*leftX, rows*leftY);
            Point midPoint = new Point(cols*midX, rows*midY);
            Point rightPoint = new Point(cols*rightX, rows*rightY);

            //Drawing 3 circles
            Imgproc.circle(all, leftPoint, 3, new Scalar(255, 0, 0), 1);
            Imgproc.circle(all, midPoint, 3, new Scalar(255, 0, 0), 1);
            Imgproc.circle(all, rightPoint, 3, new Scalar(255, 0, 0), 1);

            //Drawing 3 rectangles
            Imgproc.rectangle(
                    all,
                    new Point(cols * (leftX-rectWidth/2),
                            rows * (leftY-rectHeight/2)),
                    new Point(cols * (leftX+rectWidth/2),
                            rows * (leftY+rectHeight/2)),
                    new Scalar(0, 255, 0),
                    3);

            Imgproc.rectangle(
                    all,
                    new Point(cols * (midX-rectWidth/2),
                            rows * (midY-rectHeight/2)),
                    new Point(cols * (midX+rectWidth/2),
                            rows * (midY+rectHeight/2)),
                    new Scalar(0, 255, 0),
                    3);

            Imgproc.rectangle(
                    all,
                    new Point(cols * (rightX-rectWidth/2),
                            rows * (rightY-rectHeight/2)),
                    new Point(cols * (rightX+rectWidth/2),
                            rows * (rightY+rectHeight/2)),
                    new Scalar(0, 255, 0),
                    3);


            switch(stageToViewport) {
                case DETECTION: {
                    return all;
                }

                case THRESHOLD: {
                    return thresholdMat;
                }

                case RAW_IMAGE: {
                    return input;
                }

                default: {
                    return input;
                }
            }
        }
    }
}
