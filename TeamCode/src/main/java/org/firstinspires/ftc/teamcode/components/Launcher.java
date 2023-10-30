package org.firstinspires.ftc.teamcode.components;

import com.qualcomm.robotcore.hardware.Servo;

public class Launcher {
    Servo airplaneServo;

    public Launcher(Servo s) {
        airplaneServo = s;
        airplaneServo.setPosition(0);
    }

    public void launch() {
        airplaneServo.setPosition(1); //launch
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            airplaneServo.setPosition(0); //if for whatev reason this does error then just like do it w/o a delay
        }
        airplaneServo.setPosition(0);

    }

    public void launch(int delayMs) {
        airplaneServo.setPosition(1); //launch
        try {
            Thread.sleep(delayMs); //so note that delay must be in milliseconds
        } catch (InterruptedException e) {
            airplaneServo.setPosition(0);
        }
        airplaneServo.setPosition(0);

    }

}
