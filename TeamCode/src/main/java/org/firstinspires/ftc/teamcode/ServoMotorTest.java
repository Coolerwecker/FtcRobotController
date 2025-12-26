package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.MotorInitialize;
import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;

/**
 * FTC TeleOp example for a flywheel motor and a gradually controlled servo.
 */
@TeleOp
public class ServoMotorTest extends OpMode {
    ServoInitialize servoInitializePos=new ServoInitialize();
    MotorInitialize motorInitialize=new MotorInitialize();
    private double servoPosition=0;

    // --- Hardware Declarations ---
    public void init(){

        // The range for servo positions (usually 0.0 to 1.0)

         double FLYWHEEL_POWER = 0.75;
         servoInitializePos.init(hardwareMap);
         motorInitialize.init(hardwareMap);
        servoInitializePos.setPosition(servoPosition);


    }

    // --- Control Constants ---
    // The amount the servo position changes each loop when 'A' or 'B' is pressed.



    public void loop() {

        // --- Hardware Initialization ---
        // Map the motor and servo names as configured in the robot configuration file


        // Set motor direction (adjust if your motor spins the wrong way)


        // Set motor behavior when power is zero

        // Initialize servo position to a safe starting value


        // --- Status and Wait for Start ---




        // --- TeleOp Control Loop ---


            // 1. Flywheel Motor Control (Right Bumper)
            // The motor runs at a set power when the right bumper is held down.
            if (gamepad1.right_bumper) MotorInitialize.setFlywheelPower(0.75);
            else {
                MotorInitialize.setFlywheelPower(0.0);

            }

            // 2. Servo Gradual Control ('A' and 'B' Buttons)

            // Increase servo position (move toward 1.0) when 'A' is pressed
            if (gamepad1.a) {
                // Add the increment to the current position
                servoPosition += 0.01;
            }

            // Decrease servo position (move toward 0.0) when 'B' is pressed
            if (gamepad1.b) {
                // Subtract the increment from the current position
                servoPosition -= 0.01;
            }


            // Apply the new calculated position to the servo
            servoInitializePos.setPosition(servoPosition);

            // --- Telemetry Updates ---


            // Optional: Pause the loop briefly to free up processing power
            // If you find the servo movement too fast, increase this delay.

    }
    }

