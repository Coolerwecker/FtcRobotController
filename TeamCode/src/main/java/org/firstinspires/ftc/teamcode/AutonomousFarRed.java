package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;

import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanism.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.CalculatingMethods;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.DataStorage;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;
public class AutonomousFarRed {
    public ElapsedTime stateTimer = new ElapsedTime();
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();
    DataStorage dataStorage = new DataStorage();
    CalculatingMethods calculatingMethods=new CalculatingMethods();
    public enum States {
        ReadAprilTag,
        CollectBalls,
        Drive_to_Shoot,
        Shoot,
    }

    PathChain DrivetoBalls;
    PathChain DrivetoShoot;
    boolean PathStarted;
    public AutonomousFarBlue.States currentState = AutonomousFarBlue.States.ReadAprilTag;
    public void init() {
        Constants.createFollower(hardwareMap);
        follower.setStartingPose(new Pose(62.85714285714286,13.396825396825408,Math.toRadians(180)));
        Pose  startPose=new Pose(62.85714285714286,13.396825396825408,Math.toRadians(180));
        Pose BallPose=new Pose(62.85714285714286,13.396825396825408,Math.toRadians(180));
        DrivetoBalls = follower.pathBuilder()
                .addPath(new BezierLine(startPose, BallPose))
                .setLinearHeadingInterpolation(startPose.getHeading(),BallPose.getHeading() )
                .build();
        DrivetoShoot=follower.pathBuilder()
                .addPath(new BezierLine(BallPose,startPose))
                .setLinearHeadingInterpolation(BallPose.getHeading(),BallPose.getHeading())
                .build();

    }


    public void loop(){
        follower.update();
        switch (currentState) {
            case ReadAprilTag:
                //turret.setTargetAngle(Math.toRadians(90));
                boolean read = false;
                if (aprilTagWebcam.getLastDetectedId() > 20 && aprilTagWebcam.getLastDetectedId() < 24) {
                    dataStorage.setAprilTagId(aprilTagWebcam.getLastDetectedId());
                    read = true;


                }
                if (stateTimer.seconds() > 2 || read) {
                    //turret.setTargetAngle(CalculatingMethods.CalculateTurretGoalAngel(RedGoal);
                    stateTimer.reset();
                    currentState = AutonomousFarBlue.States.Shoot;

                }
                break;

            case Shoot:
                if (Math.abs(calculatingMethods.CalculateTurretGoalAngle(dataStorage.RedGoal)-calculatingMethods.TurretAngle())<Math.toRadians(3)|| stateTimer.seconds()>1){

                    //add shooting logic
                }
                //if finished
                currentState= AutonomousFarBlue.States.CollectBalls;
                break;
            case CollectBalls:
                if(!PathStarted){
                    follower.followPath(DrivetoBalls,true);
                    PathStarted=true;
                }
                if (!follower.isBusy()){
                    currentState= AutonomousFarBlue.States.Drive_to_Shoot;
                    PathStarted=false;
                }

                break;
            case Drive_to_Shoot:
                if(!PathStarted){

                    follower.followPath(DrivetoShoot,true);
                    PathStarted=true;
                }
                if (!follower.isBusy()){
                    PathStarted=false;
                    currentState= AutonomousFarBlue.States.Shoot;
                }
                break;
        }

    }
}
