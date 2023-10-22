package org.firstinspires.ftc.teamcode.opmodes;

import static org.firstinspires.ftc.teamcode.components.GamePositions.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.acmerobotics.roadrunner.trajectory.TrajectoryBuilder;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import java.util.ArrayList;
import java.util.List;

import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;

public class RoadRunnerTest extends LinearOpMode {

    public static final Pose2d START_POSE = new Pose2d(8, 4, Math.toRadians(-60));

    @Override
    public void runOpMode() throws InterruptedException {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d (0,0,0));

        testPlusSign();


//boomer manual code
//        Trajectory myTrajectory = drive.trajectoryBuilder(BLUE_START_POS_1)
//                .splineTo(BLUE_OBJECT_POS_1.vec(), BLUE_OBJECT_POS_1.getHeading())
//                .build();



        //Trajectory myTrajectory = buildTrajectoryFromPoses(BLUE_START_POS_1, poses, drive);

        // DELTA I CHANGED .SPLINETO TO .SPLINETOCONSTANTHEADING BC I THINK ITS BETTER?? IDK FEEL FREE TO CHANGE BACK - VED

        //drive.followTrajectory(myTrajectory); //we live we love we lie
    }


    private void testPlusSign() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d (0,0,0));

        List<Pose2d> poses = new ArrayList<Pose2d>();
        poses.add(new Pose2d (12, 0, 0));
        poses.add(new Pose2d (-12, 0, 0));
        poses.add(new Pose2d (0, 0, 0));
        poses.add(new Pose2d (0, 12, 0));
        poses.add(new Pose2d (0, -12, 0));
        poses.add(new Pose2d (0, 0, 0));

        Trajectory test = buildTrajectoryFromPoses(new Pose2d (0,0,0), poses, drive);

        drive.followTrajectory(test);

    }

    private void testSpline() {
        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);

        drive.setPoseEstimate(new Pose2d (0,0,0));

        List<Pose2d> poses = new ArrayList<Pose2d>();
        poses.add(new Pose2d (12, 0, 0));
        poses.add(new Pose2d (-12, 0, 0));
        poses.add(new Pose2d (0, 0, 0));
        poses.add(new Pose2d (0, 12, 0));
        poses.add(new Pose2d (0, -12, 0));
        poses.add(new Pose2d (0, 0, 0));

        Trajectory test = buildTrajectoryFromPoses(new Pose2d (0,0,0), poses, drive);

        drive.followTrajectory(test);

    }

    private Trajectory buildTrajectoryFromPoses (Pose2d startPose, List<Pose2d> poses, SampleMecanumDrive drive) {

        TrajectoryBuilder test = drive.trajectoryBuilder(startPose); //instantiate trajectory builder
        for (int i = 0; i < poses.size(); i++) {
            test.splineToConstantHeading(poses.get(i).vec(), poses.get(i).getHeading()); //spline to each elem in list poses
        }

        return test.build(); //build and return
    }
}