package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class MotorInitialize {
    private DcMotor front_left_drive;
    private DcMotor back_left_drive;
    private DcMotor front_right_drive;
    private DcMotor back_right_drive;
    private static DcMotor flywheel_motor;
    private DcMotor intake_motor;
    public void init (HardwareMap hwMap) {

// --- Hardware Mapping (MUST match config names!) ---
        front_left_drive = hwMap.get(DcMotor.class, "front_left_drive");
        back_left_drive = hwMap.get(DcMotor.class, "back_left_drive");
        front_right_drive = hwMap.get(DcMotor.class, "front_right_drive");
        back_right_drive = hwMap.get(DcMotor.class, "back_right_drive");
        flywheel_motor = hwMap.get(DcMotor.class, "flywheel_drive");
        intake_motor = hwMap.get(DcMotor.class, "intake_motor");
// --- Directions (Typically right side needs to be reversed) ---
        front_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
        back_left_drive.setDirection(DcMotorSimple.Direction.FORWARD);
        front_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        back_right_drive.setDirection(DcMotorSimple.Direction.REVERSE);
        flywheel_motor.setDirection(DcMotorSimple.Direction.FORWARD);
        intake_motor.setDirection(DcMotorSimple.Direction.FORWARD);
// --- Run Mode ---
        front_left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        front_right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        back_right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        flywheel_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intake_motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

    }
    // --- D;rive Motor Setters ---
    public void setFrontLeftPower(double power){  front_left_drive.setPower(power); }
    public void setBackLeftPower(double power){  back_left_drive.setPower(power); }
    public void setFrontRightPower(double power){   front_right_drive.setPower(power); }
    public void setBackRightPower(double power){  back_right_drive.setPower(power); }
    // --- Flywheel Control ---
    public static void setFlywheelPower(double power){
         flywheel_motor.setPower(power);
    }
    public double getFlywheelPower() {
       return  flywheel_motor.getPower();
    }

    public void setIntakePower(double speed){
        intake_motor.setPower(speed);
    }
    public double getIntakePower() {
        return intake_motor.getPower();
    }
}