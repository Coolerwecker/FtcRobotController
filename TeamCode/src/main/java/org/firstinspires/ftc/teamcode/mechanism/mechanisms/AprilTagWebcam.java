package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import android.util.Size;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;
import java.util.List;

public class AprilTagWebcam {
    private AprilTagProcessor aprilTagProcessor;
    public VisionPortal visionPortal;

    // Lens Intrinsics (Deine Kalibrierungswerte)
    private final double fx = 727.238;
    private final double fy = 727.238;
    private final double cx = 286.175;
    private final double cy = 239.482;

    public void init(HardwareMap hwMap) {
        aprilTagProcessor = new AprilTagProcessor.Builder()
                .setDrawTagID(true)
                .setDrawTagOutline(true)
                .setDrawAxes(true)
                .setDrawCubeProjection(true)
                .setOutputUnits(DistanceUnit.INCH, AngleUnit.RADIANS)
                .setLensIntrinsics(fx, fy, cx, cy)
                .build();

        visionPortal = new VisionPortal.Builder()
                .setCamera(hwMap.get(WebcamName.class, "Webcam 1"))
                .setCameraResolution(new Size(640, 480))
                .addProcessor(aprilTagProcessor)
                .setAutoStopLiveView(false)
                .build();
    }

    /**
     * Überprüft, ob aktuell mindestens ein AprilTag erkannt wird.
     */
    public boolean hasDetections() {
        List<AprilTagDetection> currentDetections = aprilTagProcessor.getDetections();
        return currentDetections != null && !currentDetections.isEmpty();
    }

    /**
     * Gibt das komplette Objekt der letzten/neuesten Detection zurück.
     */
    public AprilTagDetection getLastDetection() {
        List<AprilTagDetection> detections = aprilTagProcessor.getDetections();
        if (detections != null && !detections.isEmpty()) {
            return detections.get(detections.size() - 1);
        }
        return null;
    }

    /**
     * Gibt nur die ID der letzten Detection zurück.
     * @return ID oder -1, wenn nichts erkannt wurde.
     */
    public int getLastDetectedId() {
        AprilTagDetection last = getLastDetection();
        return (last != null) ? last.id : -1;
    }

    public void stop() {
        if (visionPortal != null) {
            visionPortal.close();
        }
    }
}




