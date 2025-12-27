package org.firstinspires.ftc.teamcode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;
@TeleOp
public class ServoTest extends OpMode {
    ServoInitialize servoInitialize= new ServoInitialize();
    double ServoPos1=1;
    double ServoPos2=-1;

    double CRServoSpeed=1;

    public void init(){
        servoInitialize.init(hardwareMap);
    }

    public void loop() {
        if (gamepad1.a){
            servoInitialize.setServo1Postion(ServoPos1);
        }
        else if (gamepad1.b){
            servoInitialize.setServo1Postion(ServoPos2);
        }
        if (gamepad1.x){
            servoInitialize.setServoTurretPower(CRServoSpeed);
        }
        else if (gamepad1.y){
            servoInitialize.setServoTurretPower(0);
        }



    }
}
