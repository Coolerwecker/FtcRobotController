/*package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

import com.pedropathing.follower.Follower;
import com.pedropathing.geometry.BezierCurve;
import com.pedropathing.geometry.BezierLine;
import com.pedropathing.geometry.Pose;
import com.pedropathing.paths.Path;
import com.pedropathing.paths.PathChain;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.mechanism.mechanisms.AprilTagWebcam;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.CalculatingMethods;
import org.firstinspires.ftc.teamcode.mechanism.mechanisms.DataStorage;
import org.firstinspires.ftc.teamcode.pedroPathing.Constants;

@Autonomous
public class AutonomousCloseRed extends OpMode {
    public ElapsedTime stateTimer = new ElapsedTime();
    AprilTagWebcam aprilTagWebcam = new AprilTagWebcam();
    DataStorage dataStorage = new DataStorage();
    CalculatingMethods calculatingMethods=new CalculatingMethods();
    public boolean read=false;
    public enum States{
        ReadAprilTag,
        DrivetoShoot,
        Shoot,
        CollectBalls,
        DrivetoShoot2,
        Shoot2,
        OpenGate,
        DriveShootPose,
        CollectBalls2,
        DrivetoShoot3,
        Shoot3,
        CollectBalls3,
        DrivetoShoot4,
        Shoot4,

    }
    boolean PathStarted=false;
    PathChain DrivetoShoot1;
    PathChain DrivetoShoot2;
    PathChain DrivetoShoot3;
    PathChain DrivetoShoot4;
    PathChain DriveShootPos;
    PathChain CollectBalls1;
    PathChain CollectBalls2;
    PathChain CollectBalls3;
    PathChain OpenGate;
    Pose ShootPos=new Pose(); //AddShootPose
    Pose StartPos=new Pose(); //AddShootPose
    Pose BallPos1=new Pose();//
    Pose BallPos2=new Pose();//
    Pose BallPos3=new Pose();
    Pose GatePos=new Pose();
    Pose ControlPoint1=new Pose();
    Pose ControlPoint2=new Pose();
    Pose ControlPoint3=new Pose();
    public States currentState=States.ReadAprilTag;

    @Override
    public void init() {
        Constants.createFollower(hardwareMap);
        follower.setStartingPose(StartPos);
        DrivetoShoot1=follower.pathBuilder()
                .addPath(new BezierLine(StartPos,ShootPos))
                .setLinearHeadingInterpolation()
                .build();
        DrivetoShoot2=follower.pathBuilder()
                .addPath(new BezierLine(BallPos1,ShootPos))
                .setLinearHeadingInterpolation()
                .build();
        DrivetoShoot3=follower.pathBuilder()
                .addPath(new BezierLine(BallPos2,ShootPos))
                .setLinearHeadingInterpolation()
                .build();
        DrivetoShoot4=follower.pathBuilder()
                .addPath(new BezierLine(BallPos3,ShootPos))
                .setLinearHeadingInterpolation()
                .build();
        DriveShootPos=follower.pathBuilder()
                .addPath(new BezierLine(GatePos,ShootPos))
                .setLinearHeadingInterpolation()
                .build();
        CollectBalls1=follower.pathBuilder()
                .addPath(new BezierCurve(ShootPos,ControlPoint1,BallPos1))
                .setLinearHeadingInterpolation()
                .build();
        CollectBalls2=follower.pathBuilder()
                .addPath(new BezierCurve(ShootPos,ControlPoint2,BallPos2))
                .setLinearHeadingInterpolation()
                .build();
        CollectBalls3=follower.pathBuilder()
                .addPath(new BezierCurve(ShootPos,ControlPoint3,BallPos3))
                .setLinearHeadingInterpolation()
                .build();
        OpenGate=follower.pathBuilder()
                .addPath(new BezierLine(ShootPos, GatePos))
                .setLinearHeadingInterpolation()
                .build();

        stateTimer.reset();

    }

    @Override
    public void loop() {
        follower.update();
        if (follower.getVelocity().getMagnitude()<0.1){
            follower.setPose(calculatingMethods.CalculateCamPos(aprilTagWebcam.getLastDetection(),dataStorage.RedGoal));
        }
        switch (currentState){
            case ReadAprilTag:

                if (aprilTagWebcam.getLastDetectedId(<21&&aprilTagWebcam.getLastDetectedId()<24){
                    dataStorage.setAprilTagId(aprilTagWebcam.getLastDetectedId());
                    read=true;
                }
                if (read||stateTimer.seconds()>2){
                    currentState=States.DrivetoShoot;
                    stateTimer.reset();
                }
                break;
            case DrivetoShoot:
                if(!PathStarted){
                    follower.followPath(DrivetoShoot1,true);
                    PathStarted=true;
                }
                if (!follower.isBusy()||stateTimer.seconds()>2){
                    stateTimer.reset();
                    currentState= States.Shoot;
                    PathStarted=false;
                }
                break;
            case Shoot:
                //add shooting logic
                currentState=States.CollectBalls;
                break;
            case CollectBalls:
                if(!PathStarted){
                    follower.followPath(CollectBalls1,true);
                    PathStarted=true;
                }
                if (!follower.isBusy()||stateTimer.seconds()>2){
                    stateTimer.reset();
                    currentState= States.DrivetoShoot2;
                    PathStarted=false;
                }
                break;
            case DrivetoShoot2:
                if(!PathStarted){
                    follower.followPath(DrivetoShoot2,true);
                    PathStarted=true;
                }
                if (PathStarted&&(!follower.isBusy() || stateTimer.seconds() > 2)){
                    stateTimer.reset();
                    currentState= States.Shoot2;
                    PathStarted=false;
                }
            case Shoot2:
                //add shoot logic
                currentState=States.OpenGate;
                break;
            case OpenGate:
                if(!PathStarted){
                    follower.followPath(OpenGate,true);
                    PathStarted=true;
                }
                if (!follower.isBusy()||stateTimer.seconds()>2){
                    stateTimer.reset();

                    if (stateTimer.seconds()>4){
                        currentState= States.DriveShootPose;
                        PathStarted=false;
                    }}
            case DriveShootPose:
                if(!PathStarted){
                    follower.followPath(DriveShootPos,true);
                    PathStarted=true;
                }
                if (PathStarted&&(!follower.isBusy() || stateTimer.seconds() > 2)){
                    stateTimer.reset();
                    currentState= States.CollectBalls2;
                    PathStarted=false;
                }
            case CollectBalls2:
                if(!PathStarted){
                    follower.followPath(CollectBalls2,true);
                    PathStarted=true;
                }
                if (PathStarted&&(!follower.isBusy() || stateTimer.seconds() > 2)){
                    stateTimer.reset();
                    currentState= States.Shoot3;
                    PathStarted=false;
                }
            case Shoot3:
                //add shoot logic
                currentState=States.CollectBalls3;
                break;
            case CollectBalls3:
                if(!PathStarted){
                    follower.followPath(CollectBalls3,true);
                    PathStarted=true;
                }
                if (PathStarted&&(!follower.isBusy() || stateTimer.seconds() > 2)){
                    stateTimer.reset();
                    currentState= States.DrivetoShoot4;
                    PathStarted=false;
                }
            case DrivetoShoot4:
                if(!PathStarted){
                    follower.followPath(DrivetoShoot4,true);
                    PathStarted=true;
                }
                if (PathStarted&&(!follower.isBusy() || stateTimer.seconds() > 2)){
                    stateTimer.reset();
                    currentState= States.Shoot4;
                    PathStarted=false;
                }
            case Shoot4:
                //add shoot logic
                break;






        }

    }
}
*/