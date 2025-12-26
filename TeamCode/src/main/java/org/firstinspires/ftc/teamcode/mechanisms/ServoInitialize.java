package org.firstinspires.ftc.teamcode.mechanisms;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

public class ServoInitialize {
   public Servo servo_1;
   public Servo servo_2;
   public Servo servo_3;
   public CRServo servo_turret;

    public void init(HardwareMap hwMap){
        servo_1 = hwMap.get(Servo.class,"servo_1");
        servo_2=hwMap.get(Servo.class, "servo_2");
        servo_3= hwMap.get(Servo.class, "servo_3");
        servo_turret= hwMap.get(CRServo.class, "servo_turret")

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
