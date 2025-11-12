package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;

@TeleOp
public class Omniwheelpractice extends OpMode {
    MotorInitialize initialize = new MotorInitialize();

    @Override
    public void init() {
        initialize.init(hardwareMap);

    }

    @Override
    public void loop() {

            // 1. Get Joystick Inputs
            // Negate Y since pushing the stick UP is usually negative in FTC
            double forward = -gamepad1.left_stick_y;
            double strafe  = gamepad1.left_stick_x;
            double turn    = gamepad1.right_stick_x; // Usually the right stick controls turning

            // 2. Calculate Raw Motor Powers (Kinematics)
            double frontLeftPower = forward + strafe + turn;
            double backLeftPower  = forward - strafe + turn;
            double frontRightPower = forward - strafe - turn;
            double backRightPower = forward + strafe - turn;

            // 3. Power Scaling (Normalization)
            // Find the largest magnitude power
            double max;
            max = Math.abs(frontLeftPower);
            max = Math.max(max, Math.abs(backLeftPower));
            max = Math.max(max, Math.abs(frontRightPower));
            max = Math.max(max, Math.abs(backRightPower));

            // Scale down if any power exceeds 1.0
            if (max > 1.0) {
                frontLeftPower /= max;
                backLeftPower  /= max;
                frontRightPower /= max;
                backRightPower /= max;
            }

            // 4. Apply Final Powers
            // NOTE: You would need to update MotorInitialize to set four motor speeds,
            // or set them directly if you have access to the motor objects here.
            initialize.setLeft_forward_drive(0.25*frontLeftPower);
            initialize.setLeft_backward_drive(0.25*backLeftPower);
            initialize.setRight_forward_drive(0.25*frontRightPower);
            initialize.setRight_backward_drive(0.25*backRightPower);
        }
    }

