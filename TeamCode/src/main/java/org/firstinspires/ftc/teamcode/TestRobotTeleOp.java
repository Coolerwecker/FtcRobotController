

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime; // We still use this timer

import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;
import org.firstinspires.ftc.teamcode.mechanisms.ServoInitializeOriginal;

@TeleOp
public class TestRobotTeleOp extends OpMode {
    MotorInitialize motorInitialize = new MotorInitialize();
    ServoInitializeOriginal servoInitialize = new ServoInitializeOriginal();

    // --- UPDATED --- Pusher Servo positions
    public static final double PUSHER_HOME_POSITION = 0.2; // Retracted
    public static final double PUSHER_PUSH_POSITION = 0.8; // Pushing

    // --- NEW --- Loader Servo positions
    // ❗ You MUST tune these values!


    // --- NEW --- Time constants for the cycle
    public static final double PUSH_TIME_SECONDS = 0.5;   // How long pusher stays out (0.5s)

    // --- UPDATED --- Timer and State Machine
    private ElapsedTime cycleTimer = new ElapsedTime();

    // --- UPDATED --- Our state machine now has 4 states
    private enum ShootingCycleState {
        IDLE,    // Waiting for button
        PUSHING, // Pusher servo is out

    }

    private ShootingCycleState currentState = ShootingCycleState.IDLE;
    private boolean aButtonPreviouslyPressed = false;


    @Override
    public void init() {
        motorInitialize.init(hardwareMap);
        servoInitialize.init(hardwareMap);

        // --- UPDATED --- Set BOTH servos to home on init
        servoInitialize.setPusher(PUSHER_HOME_POSITION);


        // Start in IDLE state
        currentState = ShootingCycleState.IDLE;
    }

    @Override
    public void loop() {

        // ... (Your drive code 1, 2, 3, and 4 is all here) ...
        // 1. Get Joystick Inputs
        double forward = -gamepad1.left_stick_y;
        double strafe  = gamepad1.left_stick_x;
        double turn    = gamepad1.right_stick_x;

        // 2. Calculate Raw Motor Powers
        double frontLeftPower = forward + strafe + turn;
        double backLeftPower  = forward - strafe + turn;
        double frontRightPower = forward - strafe - turn;
        double backRightPower = forward + strafe - turn;
        double flywheelPower=1;

        // 3. Power Scaling
        double max;
        max = Math.abs(frontLeftPower);
        max = Math.max(max, Math.abs(backLeftPower));
        max = Math.max(max, Math.abs(frontRightPower));
        max = Math.max(max, Math.abs(backRightPower));
        if (max > 1.0) {
            frontLeftPower /= max;
            backLeftPower  /= max;
            frontRightPower /= max;
            backRightPower /= max;
        }

        // 4. Apply Final Powers
        motorInitialize.setLeft_forward_drive(0.25*frontLeftPower);
        motorInitialize.setLeft_backward_drive(0.25*backLeftPower);
        motorInitialize.setRight_forward_drive(0.25*frontRightPower);
        motorInitialize.setRight_backward_drive(0.25*backRightPower);
        motorInitialize.setflywheel_drive(flywheelPower);

        // --- UPDATED ---
        // 5. Full Shooting & Loading State Machine Logic

        boolean aButtonPressed = gamepad1.a;

        switch (currentState) {
            case IDLE:
                // If the 'a' button is pressed (and we're ready)
                if (aButtonPressed && !aButtonPreviouslyPressed) {
                    // 1. Shoot!
                    servoInitialize.setPusher(PUSHER_PUSH_POSITION);
                    // 2. Reset timer for this step
                    cycleTimer.reset();
                    // 3. Go to the next state
                    currentState = ShootingCycleState.PUSHING;
                }
                break;

            case PUSHING:
                // Wait for the pusher to be out for PUSH_TIME_SECONDS
                if (cycleTimer.seconds() >= PUSH_TIME_SECONDS) {
                    // 1. Retract pusher
                    servoInitialize.setPusher(PUSHER_HOME_POSITION);
                    // 2. Reset timer for the cooldown
                    cycleTimer.reset();
                    // 3. Go to cooldown state

                }
                break;



        // This is still crucial for detecting a *new* button press

        } // End of loop()
} // End of class
 }