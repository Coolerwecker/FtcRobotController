package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;

@TeleOp
public class MotorPractice extends OpMode {
    MotorInitialize initialize = new MotorInitialize();

    @Override
    public void init() {
        initialize.init(hardwareMap);

    }
    public void loop(){
        initialize.setLeft_forward_drive(1);
    }
}
