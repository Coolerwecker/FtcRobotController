/*package org.firstinspires.ftc.teamcode;

import static com.sun.tools.javac.jvm.ByteCodes.error;

import com.bylazar.configurables.annotations.Configurable;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.teamcode.mechanism.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.MotorInitialize;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
@Configurable
@TeleOp
public class TurretTrackingTester extends OpMode {
    AprilTagWebcam aprilTagWebcam=new AprilTagWebcam();
   public DcMotor motor;



       public double p = 0.005;
       public double d = 0.0002;


    public double lastError;
    double power=0;




    ElapsedTime timer = new ElapsedTime();

    @Override
    public void init() {
        motor=hardwareMap.get(DcMotor.class,"turretmotor");
        aprilTagWebcam.init(hardwareMap);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
   }

    @Override
        public void loop() {
        double EncoderPos= motor.getCurrentPosition();

        if (gamepad1.a){
            p=p+0.0005;

        }
        if(gamepad1.b){
            p=p-0.0005;

        }
        if (gamepad1.x){
            d=d+0.00005;
        }
        if (gamepad1.y){
            d=d-0.00005;
        }
        if (EncoderPos < -500 && power < 0) {

            power = 0;

        }
        else if (EncoderPos> 500 && power > 0) {

            power = 0;

        }
            if (!aprilTagWebcam.detectedTags.isEmpty()) {
                AprilTagDetection detection = aprilTagWebcam.detectedTags.get(0);
                if (detection.metadata != null) {

                   double bearing=detection.ftcPose.bearing;
                    power = calculatePD(bearing, 0);


                    // Wir setzen hier die "Velocity" (Geschwindigkeit)
                    // Der Motor nutzt seinen internen PID, um diese Power stabil zu halten
                  motor.setPower(Range.clip(power, -0.3, 0.3));
                }

            } else {
                motor.setPower(0);
            }

            telemetry.addData("Kamera Winkel",);
            telemetry.addData("Encoder Pos",EncoderPos);
            telemetry.addData("P", p);
            telemetry.addData("d", d);

            telemetry.update();
        }

    public double calculatePD(double current, double target) {
        double error = target - current;
        if (error<1){
            return 0;
        }
        double deltaTime =timer.seconds();
        double derivative = (deltaTime > 0) ? (error - lastError) / deltaTime : 0;

        lastError = error;
        timer.reset();
        return (error * p) + (derivative *d );
    }
}



*/