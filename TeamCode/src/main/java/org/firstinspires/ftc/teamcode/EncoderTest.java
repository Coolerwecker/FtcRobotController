package org.firstinspires.ftc.teamcode;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class EncoderTest extends OpMode {

    DcMotor leftEncoder;
    DcMotor rightEncoder;
    DcMotor strafeEncoder;
    @Override

        public void init() {
        // Based on your Constants.java mapping:
        // Left: front_left_drive
        // Right: back_left_drive
        // Strafe: front_right_drive

        leftEncoder = hardwareMap.get(DcMotor.class, "frontright");
        rightEncoder = hardwareMap.get(DcMotor.class, "frontleft");
        strafeEncoder = hardwareMap.get(DcMotor.class, "backleft");

        telemetry.addLine("Odometry Test: Move the robot manually.");
        telemetry.update();


    }
            public void loop(){
                telemetry.addData("Left Encoder (front_left)", leftEncoder.getCurrentPosition());
                telemetry.addData("Right Encoder (back_left)", rightEncoder.getCurrentPosition());
                telemetry.addData("Strafe Encoder (front_right)", strafeEncoder.getCurrentPosition());

                telemetry.addLine("\n--- Directions Check ---");
                telemetry.addLine("Push Forward: Left & Right should increase (+)");
                telemetry.addLine("Push Right: Strafe should increase (+)");
                telemetry.update();
            }
        }



