package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;

@Autonomous
public class AprilTagWebcamTest extends OpMode {
    AprilTagWebcam aprilTagWebcam=  new AprilTagWebcam();
    public void init(){
       aprilTagWebcam.init(hardwareMap,telemetry);

    }

    @Override
    public void loop() {
    aprilTagWebcam.update();
        AprilTagDetection id24=aprilTagWebcam.getTagbySpecificId(24);
                aprilTagWebcam.displayDetectionTelemetry(id24);
    }
}
