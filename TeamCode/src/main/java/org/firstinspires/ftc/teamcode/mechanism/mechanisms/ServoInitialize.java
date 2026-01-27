package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoInitialize {
   public Servo servo_1;
   public Servo servo_2;
   public Servo servo_3;
   public CRServo servo_turret;

    public void init(HardwareMap hwMap){
        servo_1 = hwMap.get(Servo.class,"left");
        servo_2=hwMap.get(Servo.class, "back");
        servo_3= hwMap.get(Servo.class, "right");
        servo_turret= hwMap.get(CRServo.class, "");

    }

    public void setServo1Postion(double servoPostion){
        servo_1.setPosition(servoPostion);
    }

    public void setServo2Position(double servoPosition) {
        servo_2.setPosition(servoPosition);
    }
    public void setServo3Position(double servoPosition){
        servo_3.setPosition(servoPosition);
    }
    public void setServoTurretPower(double power){
        servo_turret.setPower(power);
    }
}
