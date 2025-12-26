package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;
import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;
@TeleOp
public class ServoTest extends OpMode {
    ServoInitialize servocontrol= new ServoInitialize();
    MotorInitialize motorInitialize= new MotorInitialize();
    public void init(){
        servocontrol.init(hardwareMap);
        motorInitialize.init(hardwareMap);
    }


    public void loop() {
        servocontrol.setPostion(gamepad1.left_stick_x);


        motorInitialize.setFlywheelPower(-gamepad1.left_stick_y);
    }
}
