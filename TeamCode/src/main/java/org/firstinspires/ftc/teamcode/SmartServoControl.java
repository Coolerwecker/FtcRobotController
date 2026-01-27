package org.firstinspires.ftc.teamcode;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.IMU;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.stream.CameraStreamServer;
import org.firstinspires.ftc.teamcode.BallDetectionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

@TeleOp(name = "SmartBallControl_FieldCentric_Final", group = "Production")
public class SmartServoControl extends LinearOpMode {

    private Servo rightServo, backServo, leftServo, hood;
    private DcMotorEx flywheel, intake;
    private DcMotor turret;
    private DcMotor frontleft, frontright, backleft, backright;
    private IMU imu;

    private OpenCvCamera webcam;
    private BallDetectionPipeline pipeline;

    final double REST_POS = 0.62;
    final double KICK_POS = 0.0;
    final double DRIVE_SPEED_LIMIT = 0.8;
    double hoodPos = 0.3;
    final double HOOD_SPEED = 0.001;

    @Override
    public void runOpMode() {
        rightServo = hardwareMap.get(Servo.class, "right");
        backServo = hardwareMap.get(Servo.class, "back");
        leftServo = hardwareMap.get(Servo.class, "left");
        hood = hardwareMap.get(Servo.class, "hood");
        flywheel = hardwareMap.get(DcMotorEx.class, "flywheel");
        intake = hardwareMap.get(DcMotorEx.class, "intake");
        turret = hardwareMap.get(DcMotor.class, "turret");
        frontleft = hardwareMap.get(DcMotor.class, "frontleft");
        frontright = hardwareMap.get(DcMotor.class, "frontright");
        backleft = hardwareMap.get(DcMotor.class, "backleft");
        backright = hardwareMap.get(DcMotor.class, "backright");

        // --- IMU SETUP (KORRIGIERT FÜR USB UP + RICHTIGE RICHTUNG) ---
        imu = hardwareMap.get(IMU.class, "imu");
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
                RevHubOrientationOnRobot.UsbFacingDirection.UP));
        imu.initialize(parameters);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        webcam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        pipeline = new BallDetectionPipeline();
        webcam.setPipeline(pipeline);
        webcam.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override public void onOpened() {
                webcam.startStreaming(640, 480, OpenCvCameraRotation.UPRIGHT);
                CameraStreamServer.getInstance().setSource(webcam);
            }
            @Override public void onError(int errorCode) {}
        });

        flywheel.setDirection(DcMotorEx.Direction.REVERSE);
        intake.setDirection(DcMotorEx.Direction.REVERSE);
        frontleft.setDirection(DcMotor.Direction.REVERSE);
        backleft.setDirection(DcMotor.Direction.REVERSE);
        turret.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        rightServo.setPosition(REST_POS);
        backServo.setPosition(REST_POS);
        leftServo.setPosition(REST_POS);
        hood.setPosition(hoodPos);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.start) {
                imu.resetYaw();
            }

            double y = -gamepad1.right_stick_y;
            double x = gamepad1.right_stick_x;
            double rx = 0;

            double botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);
            double rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
            double rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

            double denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);
            frontleft.setPower(((rotY + rotX + rx) / denominator) * DRIVE_SPEED_LIMIT);
            backleft.setPower(((rotY - rotX + rx) / denominator) * DRIVE_SPEED_LIMIT);
            frontright.setPower(((rotY - rotX - rx) / denominator) * DRIVE_SPEED_LIMIT);
            backright.setPower(((rotY + rotX - rx) / denominator) * DRIVE_SPEED_LIMIT);

            turret.setPower(gamepad1.left_stick_x * 0.3);
            flywheel.setPower(gamepad1.right_trigger > 0.1 ? 1.0 : 0.0);
            intake.setPower(gamepad1.left_trigger > 0.1 ? 1.0 : 0.0);

            if (gamepad1.dpad_up) hoodPos += HOOD_SPEED;
            else if (gamepad1.dpad_down) hoodPos -= HOOD_SPEED;
            if (hoodPos > 0.5) hoodPos = 0.5;
            if (hoodPos < 0.0) hoodPos = 0.0;
            hood.setPosition(hoodPos);

            if (gamepad1.left_bumper) smartKick(BallDetectionPipeline.BallColor.GREEN);
            if (gamepad1.right_bumper) smartKick(BallDetectionPipeline.BallColor.PURPLE);
            if (gamepad1.a) simpleKick(rightServo);
            if (gamepad1.b) simpleKick(backServo);
            if (gamepad1.x) simpleKick(leftServo);

            // Telemetrie
            telemetry.addLine("=== ROBOT STATUS ===");
            telemetry.addData("Heading", "%.1f°", Math.toDegrees(botHeading));
            telemetry.addData("Hood Pos", "%.3f", hoodPos);
            telemetry.addData("Balls", "L:%s B:%s R:%s", pipeline.results[0], pipeline.results[1], pipeline.results[2]);
            telemetry.update();
        }
    }

    private void smartKick(BallDetectionPipeline.BallColor targetColor) {
        if (pipeline.results[0] == targetColor) { simpleKick(leftServo); return; }
        if (pipeline.results[1] == targetColor) { simpleKick(backServo); return; }
        if (pipeline.results[2] == targetColor) { simpleKick(rightServo); return; }
        if (pipeline.results[0] != BallDetectionPipeline.BallColor.EMPTY) { simpleKick(leftServo); return; }
        if (pipeline.results[1] != BallDetectionPipeline.BallColor.EMPTY) { simpleKick(backServo); return; }
        if (pipeline.results[2] != BallDetectionPipeline.BallColor.EMPTY) { simpleKick(rightServo); return; }
    }

    private void simpleKick(Servo kicker) {
        kicker.setPosition(KICK_POS);
        sleep(400);
        kicker.setPosition(REST_POS);
        sleep(200);
    }
}
