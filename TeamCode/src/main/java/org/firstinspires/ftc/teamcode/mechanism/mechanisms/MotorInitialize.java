package org.firstinspires.ftc.teamcode.mechanism.mechanisms;


import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

public class MotorInitialize {
    public  DcMotor front_left_drive;
    public DcMotor back_left_drive;
    public DcMotor front_right_drive;
    public DcMotor back_right_drive;
   public  DcMotor flywheel_motor;
    public DcMotor intake_motor;
    public DcMotor turret_motor;
    double voltage = hardwareMap.voltageSensor.iterator().next().getVoltage();
    public void init (HardwareMap hwMap) {

// --- Hardware Mapping (MUST match config names!) ---
        front_left_drive = hwMap.get(DcMotor.class, "frontleft");
        back_left_drive = hwMap.get(DcMotor.class, "backleft");
        front_right_drive = hwMap.get(DcMotor.class, "frontright");
        back_right_drive = hwMap.get(DcMotor.class, "backright");
        flywheel_motor = hwMap.get(DcMotor.class, "flywheel");
        intake_motor = hwMap.get(DcMotor.class, "intake");
        turret_motor=hwMap.get(DcMotor.class,"turret");
// --- Directions (Typically right side needs to be reversed) ---
        front_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
        back_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
        front_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        flywheel_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        intake_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        turret_motor.setDirection(DcMotorSimple.Direction.FORWARD);
// --- Run Mode ---
        front_left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        intake_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        turret_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        front_left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_left_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        front_right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        back_right_drive.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        turret_motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);




    }
    // --- D;rive Motor Setters ---
    public void setFrontLeftPower(double power){  front_left_drive.setPower(power); }
    public void setBackLeftPower(double power){  back_left_drive.setPower(power); }
    public void setFrontRightPower(double power){   front_right_drive.setPower(power); }
    public void setBackRightPower(double power){  back_right_drive.setPower(power); }
    // --- Flywheel Control ---
    public  void setFlywheelPower(double power){
         flywheel_motor.setPower(Range.clip(power * (13 / voltage),0,1));
    }

    public void setIntakePower(double speed){
        intake_motor.setPower(speed);
    }

    public void setTurretPower(double power){
        turret_motor.setPower(power);
    }
    public double getTurretPower(double power){
        return turret_motor.getPower();
    }
    public double getTurretPos(){
        return turret_motor.getCurrentPosition();
    }
}