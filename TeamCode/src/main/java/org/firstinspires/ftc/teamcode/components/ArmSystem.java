package org.firstinspires.ftc.teamcode.components;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import android.util.Log;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.DigitalChannel;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.params.DriveParams;

public class ArmSystem {
    public static final int GROUND = 0; //VALUE TBD

    public static final int BACKBOARD = 1000; //value TBD
    public static final int ARM_LIMIT = 100; //VALUE TBD
    public static final int SERVO_GROUND = 1; //VALUE TBD;

    public enum Direction {
        UP,
        DOWN,
    }
    private DcMotor armLeft;
    public DcMotor armRight;
    public Servo leftServo;
    public Servo rightServo;
    public IntakeSystem intake;
    private int mTargetPosition;



    public ArmSystem(DcMotor motor1, DcMotor motor2, Servo servo1, Servo servo2, Servo intake1, Servo intake2){
        this.armLeft = motor1;
        this.armRight = motor2;
        this.leftServo = servo1;
        this.rightServo = servo2;
        armLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        armRight.setDirection(DcMotorSimple.Direction.REVERSE);
        initMotors();
        intake = new IntakeSystem(intake1, intake2);
        mTargetPosition = 0;
    }
    public void initMotors() {
        armLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armLeft.setPower(0);
        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        armRight.setPower(0);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public boolean driveToLevel(int targetPosition, double power){
        armLeft.setDirection(DcMotorSimple.Direction.FORWARD);
        armRight.setDirection(DcMotorSimple.Direction.REVERSE);

//        if(mTargetPosition != targetPosition){
            mTargetPosition = targetPosition;
            armLeft.setTargetPosition(targetPosition);
            armLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armLeft.setPower(power);
          //  Log.d("armLeft", )
            armRight.setTargetPosition(targetPosition);
            armRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
            armRight.setPower(power);
  //      }
        int offsetLeft = Math.abs(armLeft.getCurrentPosition() - targetPosition);
        int offsetRight = Math.abs(armRight.getCurrentPosition() - targetPosition);
        if(targetPosition != 0 && offsetLeft < 20 && offsetRight < 20 ){
            return true;
        }
        else if (targetPosition > 0 && offsetLeft < 15 && offsetRight < 20){
            return true;
        }
        return false;
    }

    public boolean armToGround(){
        if(driveToLevel(GROUND, 1)) {
            armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armRight.setPower(0);
            armLeft.setPower(0);
            return true;
        }
        leftServo.setPosition(SERVO_GROUND);
        rightServo.setPosition(SERVO_GROUND);
        return false;
    }
    public boolean armToBackboard(){
        if(driveToLevel(BACKBOARD, 1)) {
            armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
            armRight.setPower(0);
            armLeft.setPower(0);
            return true;
        }
        leftServo.setPosition(SERVO_GROUND);
        rightServo.setPosition(SERVO_GROUND);
        return false;
    }
    public void driveArm(Direction direction, double pow){
        if(direction == Direction.UP && (armLeft.getCurrentPosition() >= ARM_LIMIT || armRight.getCurrentPosition() >= ARM_LIMIT))
        {
            killMotors();
        }
        if(direction == Direction.DOWN && (armLeft.getCurrentPosition() <= 5 || armRight.getCurrentPosition() <= 5))
        {
            killMotors();
        }
        if(direction == direction.UP)
        {
            armLeft.setDirection(DcMotorSimple.Direction.FORWARD);
            armRight.setDirection(DcMotorSimple.Direction.REVERSE);
        }
        else
        {
            armLeft.setDirection(DcMotorSimple.Direction.REVERSE);
            armRight.setDirection(DcMotorSimple.Direction.FORWARD);
        }

        armLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        armLeft.setPower(pow);//change to velocity
        armRight.setPower(pow); 
//        telemetry.addData("Position left:", armLeft.getCurrentPosition());
//        telemetry.addData("Position Right:", armRight.getCurrentPosition());
//        telemetry.update();

        leftServo.setPosition(Range.clip(armLeft.getCurrentPosition(), 0.0, 1.0));//scale to range of inputs for servo
        rightServo.setPosition(Range.clip(armLeft.getCurrentPosition(), 0.0, 1.0));//scale to range of inputs for servo
        armLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        armRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }
    public void killMotors(){
        armLeft.setPower(0);
        armRight.setPower(0);
    }
    public void intake(){
        intake.intake();
    }

    public void outtake(){
        intake.outtake();
    }

    public static class IntakeSystem {
        private final Servo intakeLeft;
        private final Servo intakeRight;
        private final int INTAKE_POS = 0; //VALUE TBD
        private final int OUTTAKE_POS = 1; //VALUE TBD

        public IntakeSystem(Servo intake1, Servo intake2){
            this.intakeLeft = intake1;
            this.intakeRight = intake2;
            intake();
        }
        public void intake(){
            intakeLeft.setPosition(INTAKE_POS);
            intakeRight.setPosition(INTAKE_POS);
        }

        public void outtake(){
            intakeLeft.setPosition(OUTTAKE_POS);
            intakeRight.setPosition(OUTTAKE_POS);
        }
    }
}