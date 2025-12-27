package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;

@TeleOp
public class MotorTest extends OpMode {
    MotorInitialize motorInitialize= new MotorInitialize();

    double MotorSpeed=1;

    public void init() {
        motorInitialize.init(hardwareMap);
    }


    public void loop() {
        if(gamepad1.a){
            motorInitialize.setBackLeftPower(MotorSpeed);
        }
        else if(gamepad1.b)   {
            motorInitialize.setBackLeftPower(0);
            }
    }
}

