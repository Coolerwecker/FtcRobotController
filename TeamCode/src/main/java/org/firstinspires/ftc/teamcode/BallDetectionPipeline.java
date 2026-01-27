package org.firstinspires.ftc.teamcode;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

public class BallDetectionPipeline extends OpenCvPipeline {

    // Da es gezoomt ist, nehmen wir kleine Boxen in der Mitte des Bildes
    // Rect(x, y, breite, höhe) - Passe x/y an, falls die Boxen nicht auf den Bällen liegen
// Vorschlag für stabilere Zonen:
    public Rect slot1_ROI = new Rect(30, 60, 60, 200);   // Etwas eingerückt
    public Rect slot2_ROI = new Rect(280, 180, 100, 250); // Etwas kürzer nach unten
    public Rect slot3_ROI = new Rect(540, 60, 60, 200);  // Etwas vom rechten Rand weg


    public enum BallColor { GREEN, PURPLE, EMPTY }
    public BallColor[] results = {BallColor.EMPTY, BallColor.EMPTY, BallColor.EMPTY};
    public double[] lastHues = {0, 0, 0};

    @Override
    public Mat processFrame(Mat input) {
        if (input.empty()) return input;

        try {
            Mat hsvMat = new Mat();
            Imgproc.cvtColor(input, hsvMat, Imgproc.COLOR_RGB2HSV);

            analyze(hsvMat, slot1_ROI, 0);
            analyze(hsvMat, slot2_ROI, 1);
            analyze(hsvMat, slot3_ROI, 2);

            drawOverlays(input);
            hsvMat.release();
        } catch (Exception e) {
            // Falls eine Box außerhalb des Bildrandes liegt
        }
        return input;
    }

    private void analyze(Mat hsv, Rect roi, int i) {
        if (roi.x + roi.width <= hsv.width() && roi.y + roi.height <= hsv.height()) {
            Mat sub = hsv.submat(roi);
            Scalar avg = Core.mean(sub);
            lastHues[i] = avg.val[0];
            double val = avg.val[2]; // Helligkeit

            // KALIBRIERTE LOGIK:
            // Grün ist ~90 -> Bereich 50 bis 105
            // Lila ist ~165 -> Bereich 100 bis 180
            if (val < 50) {
                results[i] = BallColor.EMPTY;
            } else if (lastHues[i] >= 50 && lastHues[i] <= 90) {
                results[i] = BallColor.GREEN;
            } else if (lastHues[i] >= 100 && lastHues[i] <= 180) {
                results[i] = BallColor.PURPLE;
            } else {
                results[i] = BallColor.EMPTY;
            }
            sub.release();
        }
    }

    private void drawOverlays(Mat display) {
        for (int i = 0; i < 3; i++) {
            Rect r = (i==0)?slot1_ROI:(i==1)?slot2_ROI:slot3_ROI;
            Scalar color = (results[i]==BallColor.GREEN)?new Scalar(0,255,0):
                    (results[i]==BallColor.PURPLE)?new Scalar(255,0,255):new Scalar(255,255,255);

            Imgproc.rectangle(display, r, color, 3);
            Imgproc.putText(display, "H:" + (int)lastHues[i], new Point(r.x, r.y-5), 0, 0.5, color, 2);
        }
    }
}