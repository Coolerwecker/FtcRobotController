package org.firstinspires.ftc.teamcode;



import static android.os.SystemClock.sleep;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;

@TeleOp
public class ServoRampUp extends OpMode {
    ServoInitialize servoInitialize=new ServoInitialize();
    double ServoPos=0;
    int SleepTime=0;
    public void init() {
        servoInitialize.init(hardwareMap);
    }


    public void loop() {
        if (gamepad1.a){
            ServoPos+=0.01;
        }
        else if(gamepad1.b) {
            ServoPos -= 0.01;
        }
                servoInitialize.setServo1Postion(ServoPos);

                sleep(SleepTime);
    }
}

