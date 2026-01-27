package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.YawPitchRollAngles;

public class ImuInitialize {
private IMU imu;

public void init(){
    RevHubOrientationOnRobot RevOrientation= new RevHubOrientationOnRobot(
            RevHubOrientationOnRobot.LogoFacingDirection.RIGHT,
            RevHubOrientationOnRobot.UsbFacingDirection.UP);
}
    YawPitchRollAngles orientation = imu.getRobotYawPitchRollAngles();

    double RobotHeading = orientation.getYaw(AngleUnit.DEGREES);


}
