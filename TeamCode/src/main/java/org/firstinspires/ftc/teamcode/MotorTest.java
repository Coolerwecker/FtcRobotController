package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.mechanisms.MotorInitialize;

@TeleOp
public class MotorTest extends OpMode {
    MotorInitialize motorInitialize=new MotorInitialize();
    @Override
    public void init() {
motorInitialize.init(hardwareMap);
    }

    @Override
    public void loop() {
        if (gamepad1.a){
       motorInitialize.setFrontLeftPower(1);
    }
}}
