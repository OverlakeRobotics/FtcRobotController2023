package org.firstinspires.ftc.teamcode.opmodes.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.components.ArmSystem;
import org.firstinspires.ftc.teamcode.components.Launcher;
import org.firstinspires.ftc.teamcode.opmodes.base.BaseOpMode;

@TeleOp(name = "Base TeleOp", group="TeleOp")
public class BaseTeleOp extends BaseOpMode {

    Launcher launcher = new Launcher(hardwareMap.get(Servo.class, "left_hand"));

    public void loop() {
        float rx = (float) Math.pow(gamepad2.right_stick_x, 3);
        float lx = (float) Math.pow(gamepad2.left_stick_x, 3);
        float ly = (float) Math.pow(gamepad2.left_stick_y, 3);

        telemetry.addData("Right X", Math.pow(gamepad2.right_stick_x, 3));
        telemetry.addData("Left Y", Math.pow(gamepad2.left_stick_y, 3));

        // y button airplane launch
        if (gamepad1.y && launcher.isLaunching()) {
            launcher.launch();
        }

        // a button intake preset level
        if (gamepad1.a) {

        }

        // left trigger left intake expansion
        if (gamepad1.left_trigger > 0) {

        }

        // right trigger right intake expansion
        if (gamepad1.right_trigger > 0) {

        }

        // left bumper left intake closing
        if (gamepad1.left_bumper) {

        }

        // right bumper right intake closing
        if (gamepad1.right_bumper) {

        }

        // dpad up joystick arm up
        if (gamepad1.dpad_up) {

        }

        //dpad down joystick arm down
        if (gamepad1.dpad_down) {

        }
    }



}
