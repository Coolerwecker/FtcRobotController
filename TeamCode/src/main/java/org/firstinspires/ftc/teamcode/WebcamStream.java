package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanism.mechanisms.AprilTagWebcam;

@TeleOp
public class WebcamStream extends OpMode {
    AprilTagWebcam aprilTagWebcam=new AprilTagWebcam();
    @Override
    public void init() {

        aprilTagWebcam.init(hardwareMap);

    }

    @Override
    public void loop() {

    }
}
