package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorInitialize {
    private DcMotor left_drive;
    private DcMotor right_drive;

    public void init (HardwareMap hwMap){
        left_drive=hwMap.get(DcMotorEx.class,"left_drive");
        left_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_drive=hwMap.get(DcMotorEx.class,"right_drive");
        right_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }
    public void setLeft_driveSpeed(double speed){
        left_drive.setPower(speed);
    }

    public void setRight_driveSpeed(double speed) {
     right_drive.setPower(speed);
    }
}
