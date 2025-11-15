package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorInitializeOriginal {
    private DcMotor left_forward_drive;
    private DcMotor left_backward_drive;
    private DcMotor right_backward_drive;
    private DcMotor right_forward_drive;
    private DcMotor flywheel_drive;

    public void init (HardwareMap hwMap){
        left_forward_drive=hwMap.get(DcMotorEx.class,"left_forward_drive");
        left_forward_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_forward_drive=hwMap.get(DcMotorEx.class,"right_forward_drive");
        right_forward_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        left_backward_drive=hwMap.get(DcMotorEx.class,"left_backward_drive");
        left_backward_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        right_backward_drive=hwMap.get(DcMotorEx.class,"right_backward_drive");
        right_backward_drive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);



    }
    public void setLeft_forward_drive(double speed){
        left_forward_drive.setPower(speed);
    }
    public void setRight_forward_drive(double speed) {
     right_forward_drive.setPower(speed);
    }
    public void setLeft_backward_drive(double speed){
        left_backward_drive.setPower(speed);
    }
    public void setRight_backward_drive(double speed){
        right_backward_drive.setPower(speed);
    }
    public void setflywheel_drive(double speed){
        flywheel_drive.setPower(speed);
    }

}
