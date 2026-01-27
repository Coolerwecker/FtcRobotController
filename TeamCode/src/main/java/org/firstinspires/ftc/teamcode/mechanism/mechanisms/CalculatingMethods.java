package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.Pose;

import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;


public class CalculatingMethods {

    MotorInitialize motorInitialize=new MotorInitialize();


    public Pose CalculateCamPos(AprilTagDetection detection, Pose AprilTagPos){

            double Turretangle=TurretAngle();
            double realAngle=Turretangle+follower.getPose().getHeading();

            double cos = Math.cos(realAngle);
            double sin = Math.sin(realAngle);

            double camPosX = detection.ftcPose.x * cos - detection.ftcPose.y * sin;
            double camPosY = detection.ftcPose.x * sin + detection.ftcPose.y * cos;
            double globalX=AprilTagPos.getX()-camPosX-Math.cos(realAngle)*6.29921;
            double globalY=AprilTagPos.getY()- camPosY-Math.sin(realAngle)*6.29921;

            return new Pose(globalX, globalY, follower.getPose().getHeading());
        }
        public double TurretAngle (){
        double GearRatio=102.0/50.0;
        double Angle= motorInitialize.getTurretPos()/(1425.1/(2*Math.PI)*GearRatio);
        return Angle;
    }
    public double CalculateTurretGoalAngle(Pose AprilTagPos){
        double GoalAngle=Math.atan2(AprilTagPos.getY()-follower.getPose().getY(),AprilTagPos.getX()-follower.getPose().getX());
                GoalAngle=GoalAngle-follower.getPose().getHeading();
        return GoalAngle;
    }


    }

