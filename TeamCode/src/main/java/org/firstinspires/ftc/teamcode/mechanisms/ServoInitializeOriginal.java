package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoInitializeOriginal {
    private Servo servo_left;
    private Servo servo_right;

    public void init(HardwareMap hwMap) {
        servo_left = hwMap.get(Servo.class, "servo_left");
        servo_right = hwMap.get(Servo.class, "servo_right");
    }

    public void setPusher(double position) {

        servo_left.setPosition(position);
    }

    public void setLoader(double position) {
        servo_right.setPosition(position);
    }


}

