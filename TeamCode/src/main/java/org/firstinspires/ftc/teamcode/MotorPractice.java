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

    @Override
    public void loop() {
        double turn=gamepad1.left_stick_x;
        double drive=-gamepad1.left_stick_y;
        double leftpower=drive+turn;
        double rightpower=drive-turn;

        double max=Math.max(Math.abs(leftpower), Math.abs(rightpower));
        if (max>1.0){
            leftpower=leftpower/max;
            rightpower=rightpower/max;
        }
        initialize.setRight_driveSpeed(rightpower);
        initialize.setLeft_driveSpeed(leftpower);
    }
}