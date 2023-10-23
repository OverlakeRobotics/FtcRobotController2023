package com.example.meepmeeptesting;

import static com.example.meepmeeptesting.GamePositions.*;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;




public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(BLUE_START_POS_2)
                                .splineToSplineHeading(BLUE_OBJECT_POS_4_1, Math.toRadians(-90))
                                .splineToSplineHeading(BLUE_OBJECT_POS_4_2, Math.toRadians(-30))
                                .waitSeconds(1)
                                .splineToSplineHeading(BLUE_OBJECT_POS_4_3, Math.toRadians(135))
                                .splineToSplineHeading(BLUE_WAYPOINT_1, Math.toRadians(-45))
                                .splineToSplineHeading(BLUE_WAYPOINT_2, Math.toRadians(45))
                                .splineToSplineHeading(BLUE_BACKDROP_LEFT, Math.toRadians(0))
                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}