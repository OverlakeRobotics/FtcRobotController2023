package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.components.GamePositions.*;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.teamcode.components.TensorFlowDetector;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "Blue Team Autonomous Close", group = "Concept")
public class BlueTeamStartClose extends LinearOpMode {
    private static final int ATTEMPTS_TO_FIND_OBJECT = 30;
    private static final double DEG_THRESHOLD = 5d;

    private char path;

    private TrajectorySequence trajectory;

    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        if (path == 'c') {
            buildCenterPath(drive);
        }
        if (path == 'l') {
            buildLeftPath(drive);
        }
        if (path == 'r') {
            buildRightPath(drive);
        }

        waitForStart();
    }



    private void placeYellowPixel() {
        //@TODO FINISH THIS
    }

    private void dropPurplePixel() {
        //@TODO FINISH THIS
    }


    private void initialize() {


        TensorFlowDetector detector = new TensorFlowDetector("2023_Blue_Team_Object_3770.tflite", new String[]{"Blue_Owl"}, telemetry, hardwareMap);
        detector.initModel();
        detector.updateRecognitions();
        if (detector.getNumRecognitions() == 0) {
            for (int i = 0; i < ATTEMPTS_TO_FIND_OBJECT || detector.getNumRecognitions() != 0; i++) {
                detector.updateRecognitions();
                sleep(250);
            }
        }
        Recognition teamObject = detector.getHighestConfidenceRecognition();

        if (teamObject == null) {
            telemetry.addData("Object Detected - ", "No object was detected with a confidence above %f", detector.getConfidenceThreshold());
            telemetry.addData("Path Chosen - ", "Estimated angle = NULL deg, ready to follow c path");
            path = 'r';
        } else {
            telemetry.addData("Object Detected - ", "A(n) %s was found with %f confidence", teamObject.getLabel(), teamObject.getConfidence());
            if (teamObject.estimateAngleToObject(AngleUnit.DEGREES) < DEG_THRESHOLD) {
                path = 'l';
            } else {
                path = 'c';
            }
            telemetry.addData("Path Chosen - ", "Estimated angle = %f deg, ready to follow %c path", teamObject.estimateAngleToObject(AngleUnit.DEGREES), path);
        }

    }


    //**********************************************************************************************
    //**************************************** PATHS ***********************************************
    //**********************************************************************************************

    private void buildRightPath(SampleMecanumDrive drive) {
        trajectory = drive.trajectorySequenceBuilder(BLUE_START_POS_1)
                .splineToSplineHeading(BLUE_OBJECT_POS_3, Math.toRadians (0))
                .addTemporalMarker(() -> dropPurplePixel()) // This action should take X seconds or less, where X is the .waitSeconds below
                .waitSeconds(1)
                .splineToSplineHeading(BLUE_BACKDROP_RIGHT, Math.toRadians(180))
                .addTemporalMarker(() -> placeYellowPixel())
                .build();
    }

    private void buildLeftPath(SampleMecanumDrive drive) {
        trajectory = drive.trajectorySequenceBuilder(BLUE_START_POS_1)
                .splineToSplineHeading(BLUE_OBJECT_POS_1, Math.toRadians (0))
                .addTemporalMarker(() -> dropPurplePixel()) // This action should take X seconds or less, where X is the .waitSeconds below
                .waitSeconds(1)
                .splineToSplineHeading(BLUE_BACKDROP_LEFT, Math.toRadians(180))
                .addTemporalMarker(() -> placeYellowPixel())
                .build();
    }

    private void buildCenterPath(SampleMecanumDrive drive) {
        trajectory = drive.trajectorySequenceBuilder(BLUE_START_POS_1)
                .splineToSplineHeading(BLUE_OBJECT_POS_2, Math.toRadians (0))
                .addTemporalMarker(() -> dropPurplePixel()) // This action should take X seconds or less, where X is the .waitSeconds below
                .waitSeconds(1)
                .splineToSplineHeading(BLUE_BACKDROP_CENTER, Math.toRadians(180))
                .addTemporalMarker(() -> placeYellowPixel())
                .build();
    }

}
