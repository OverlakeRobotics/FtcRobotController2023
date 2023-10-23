package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.gamepad1;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.util.Range;

public class AirplaneLaunch {
    Servo airplaneServo;

    public void turnServo() {
        airplaneServo = hardwareMap.get(Servo.class, "airplane_launch_servo");
        airplaneServo.setPosition(0);
        if (gamepad1.y) {
            airplaneServo.setPosition(1);
        }
    }

}

// left trigger left intake expansion
// right trigger right intake expansion
// left bumper left intake closing
// right bumper right intake closing
// dpad up and down  joystick arm up and down
// y button airplane launch
// a button intake preset level
// 
