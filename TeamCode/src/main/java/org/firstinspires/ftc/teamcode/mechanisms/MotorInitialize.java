package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class MotorInitialize {
    private DcMotor front_left_drive;
    private DcMotor back_left_drive;
    private DcMotor front_right_drive;
    private DcMotor back_right_drive;
    private DcMotor flywheel_motor;
    private DcMotor intake_motor;
    public void init (HardwareMap hwMap){
        try {
// --- Hardware Mapping (MUST match config names!) ---
            front_left_drive = hwMap.get(DcMotor.class,"front_left_drive");
            back_left_drive = hwMap.get(DcMotor.class,"back_left_drive");
            front_right_drive = hwMap.get(DcMotor.class,"front_right_drive");
            back_right_drive = hwMap.get(DcMotor.class,"back_right_drive");
            flywheel_motor = hwMap.get(DcMotor.class,"flywheel_motor");
            intake_motor = hwMap.get(DcMotor.class,"intake_motor");
// --- Directions (Typically right side needs to be reversed) ---
            front_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
            back_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
            front_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
            back_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
            flywheel_motor.setDirection(DcMotorSimple.Direction.FORWARD);
            intake_motor.setDirection(DcMotorSimple.Direction.FORWARD);
// --- Run Mode ---
            front_left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            back_left_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            front_right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            back_right_drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            flywheel_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
            intake_motor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        } catch (Exception e) {
            System.out.println("Motor initialization failed: " + e.getMessage());
        }
    }
    // --- Drive Motor Setters ---
    public void setFrontLeftPower(double power){ if (front_left_drive != null) front_left_drive.setPower(power); }
    public void setBackLeftPower(double power){ if (back_left_drive != null) back_left_drive.setPower(power); }
    public void setFrontRightPower(double power){ if (front_right_drive != null) front_right_drive.setPower(power); }
    public void setBackRightPower(double power){ if (back_right_drive != null) back_right_drive.setPower(power); }
    // --- Flywheel Control ---
    public void setFlywheelPower(double speed){
        if (flywheel_motor != null) flywheel_motor.setPower(speed);
    }
    public double getFlywheelPower() {
        return (flywheel_motor != null) ? flywheel_motor.getPower() : 0.0;
    }
    // --- Intake Control ---
    public void setIntakePower(double speed){
        if (intake_motor != null) intake_motor.setPower(speed);
    }
    public double getIntakePower() {
        return (intake_motor != null) ? intake_motor.getPower() : 0.0;
    }
}