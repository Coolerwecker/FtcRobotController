package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import com.pedropathing.geometry.Pose;

public class DataStorage {
    public double RobotHeading;
    public double TurretHeading;
    public Pose FieldPos;
    public int AprilTagId;
    public Pose BlueGoal;
    public Pose RedGoal;

    public void setAprilTagId(int aprilTagId) {
        AprilTagId = aprilTagId;
    }

    public void setFieldPos(Pose fieldPos) {
        FieldPos = fieldPos;
    }

    public void setRobotHeading(double robotHeading) {
        RobotHeading = robotHeading;
    }

    public void setTurretHeading(double turretHeading) {
        TurretHeading = turretHeading;
    }
}
