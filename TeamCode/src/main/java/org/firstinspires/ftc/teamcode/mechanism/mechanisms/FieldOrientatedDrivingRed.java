package org.firstinspires.ftc.teamcode.mechanism.mechanisms;

import static org.firstinspires.ftc.teamcode.pedroPathing.Tuning.follower;

public class FieldOrientatedDrivingRed {
    public double[] setFieldOrientatedDrivingRed(double forward,double strafe){
    double theta=Math.atan2(forward,strafe);
    double r=Math.hypot(forward,strafe);
    theta=theta-follower.getHeading()-Math.PI;
    double newForward=r*Math.sin(theta);
    double newStrafe=r*Math.cos(theta);
    return new double[]{newForward,newStrafe};

}}
