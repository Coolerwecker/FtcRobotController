package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;
import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;
@TeleOp(name = "Omniwheel_6M_CRServo")
public class OmniwheelControl extends OpMode {
    // Instantiate your mechanism handlers
    MotorInitialize motorControl = new MotorInitialize();
    ServoInitialize servoControl = new ServoInitialize();
    // Constants for Speed/Power
    private final double FLYWHEEL_SPEED = 1.0;
    private final double INTAKE_SPEED = 1.0;
    private final double SERVO_SPEED = 0.5; // Reduced speed for better CR servo control
    @Override
    public void init() {
        motorControl.init(hardwareMap);
        servoControl.init(hardwareMap);
        telemetry.addData("Status", "Hardware Initialized and Ready");
    }
    @Override
    public void loop() {
// --- 1. Omniwheel Drive Kinematics (All 4 Motors) ---
        double y = -gamepad1.left_stick_y; // Forward/Backward
        double x = gamepad1.left_stick_x; // Strafe Left/Right
        double rotation = gamepad1.right_stick_x; // Rotation
// Calculate power for each wheel
        double frontLeftPower = y + x + rotation;
        double backLeftPower = y - x + rotation;
        double frontRightPower = y - x - rotation;
        double backRightPower = y + x - rotation;
// Apply power (Normalization is recommended but often handled by the system)
        motorControl.setFrontLeftPower(frontLeftPower);
        motorControl.setBackLeftPower(backLeftPower);
        motorControl.setFrontRightPower(frontRightPower);
        motorControl.setBackRightPower(backRightPower);
// --- 2. Flywheel Control (Should turn when L2 is pressed) ---
        if (gamepad1.left_trigger > 0.1) { // L2 is left_trigger
            motorControl.setFlywheelPower(FLYWHEEL_SPEED);
        } else {
            motorControl.setFlywheelPower(0.0);
        }
// --- 3. Intake Control (Should turn when R2 is pressed) ---
        if (gamepad1.right_trigger > 0.1) { // R2 is right_trigger
            motorControl.setIntakePower(INTAKE_SPEED);
        } else {
            motorControl.setIntakePower(0.0);
        }
// --- 4. CR Servo Control (Continuous Turning on button press) ---
// Pusher Servo Control (Using A/B buttons for movement)
        double pusherSpeed = 0.0;
        if (gamepad1.a) {
            pusherSpeed = SERVO_SPEED;
        } else if (gamepad1.b) {
            pusherSpeed = -SERVO_SPEED;
        }
        servoControl.setPusherPower(pusherSpeed);
// Test Servo Control (Using Dpad Up/Down, separate from A/B)
        double testSpeed = 0.0;
// Turn counter-clockwise when D-pad Up is clicked (Set power to negative to achieve CCW)
        if (gamepad1.dpad_up) {
            testSpeed = -SERVO_SPEED; // Negative power for counter-clockwise
        } else if (gamepad1.dpad_down) {
            testSpeed = SERVO_SPEED; // Positive power for return/clockwise
        }
// CRITICAL FIX: Calling the correct setTestPower method
        servoControl.setTestPower(testSpeed);
// Loader Servo Control (Using X/Y buttons for movement)
        double loaderSpeed = 0.0;
        if (gamepad1.x) {
            loaderSpeed = SERVO_SPEED;
        } else if (gamepad1.y) {
            loaderSpeed = -SERVO_SPEED;
        }
// setLoaderPower applies the inversion internally.
        servoControl.setLoaderPower(loaderSpeed);
// --- 5. Telemetry ---
        telemetry.addData("Flywheel Power", motorControl.getFlywheelPower());
        telemetry.addData("Intake Power", motorControl.getIntakePower());
        telemetry.addData("Pusher Speed", pusherSpeed);
        telemetry.addData("Loader Speed (Sent)", loaderSpeed);
        telemetry.addData("Loader Speed (Actual)", servoControl.getLoaderPower()); // Check inverted value
        telemetry.update();
    }
}
