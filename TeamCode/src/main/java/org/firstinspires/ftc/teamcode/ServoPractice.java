package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;


import org.firstinspires.ftc.teamcode.mechanisms.ServoInitialize;

@TeleOp
public class ServoPractice extends OpMode {
    ServoInitialize initialize = new ServoInitialize();

    public void init(){
        initialize.init(hardwareMap);
    }

    @Override
    public void loop() {
        if(gamepad1.a){
            initialize.setPusher(1);
        }
        if(gamepad1.b){
            initialize.setPusher(-1);
    }
        if(gamepad1.x){
            initialize.setLoader(1);
        }
        if(gamepad1.y){
            initialize.setLoader(-1);
        }
}
}
