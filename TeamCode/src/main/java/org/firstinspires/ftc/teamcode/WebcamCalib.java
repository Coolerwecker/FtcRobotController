package org.firstinspires.ftc.teamcode;

import static java.lang.Thread.sleep;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.controls.FocusControl;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.vision.VisionPortal;

@TeleOp
public class WebcamCalib extends OpMode {
    AprilTagWebcam aprilTagWebcam=new AprilTagWebcam();
    public int count=0;
    @Override
    public void init() {
        aprilTagWebcam.init(hardwareMap);



    }
    public void loop(){
        if (gamepad1.a){
            aprilTagWebcam.visionPortal.saveNextFrameRaw("Calibration"+System.currentTimeMillis());
            telemetry.addLine("Frame saved");
            try {
                sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            count++;
        }
        else {
            telemetry.clear();
            telemetry.addLine("No frame taken");
        }
    }
}
