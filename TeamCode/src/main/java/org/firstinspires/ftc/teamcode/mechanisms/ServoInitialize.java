
package org.firstinspires.ftc.teamcode.mechanisms;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
public class ServoInitialize {
    private CRServo pusher;
    private CRServo loader;
    private CRServo test; // New servo field
    public void init (HardwareMap hwMap){
        try {
// Mapping servos as CRServo objects
            pusher = hwMap.get(CRServo.class,"pusher_servo");
            loader = hwMap.get(CRServo.class,"loader_servo");
            test = hwMap.get(CRServo.class,"test_servo"); // New servo mapping
// Set initial power to zero
            pusher.setPower(0.0);
            loader.setPower(0.0);
            test.setPower(0.0); // New servo init
        } catch (Exception e) {
            pusher = null;
            loader = null;
            test = null;
            System.out.println("CRServo initialization failed: " + e.getMessage());
        }
    }
    // --- Pusher Control (Normal Direction) ---
    public void setPusherPower(double power){
        if (pusher != null) {
            power = Math.max(-1.0, Math.min(1.0, power));
            pusher.setPower(power);
        }
    }
    // --- Loader Control (INVERTED Direction) ---
    public void setLoaderPower(double power){
        if (loader != null) {
// CRITICAL INVERSION: Multiplies power by -1
            double invertedPower = -power;
            invertedPower = Math.max(-1.0, Math.min(1.0, invertedPower));
            loader.setPower(invertedPower);
        }
    }
    // --- NEW: Test Servo Control (Normal Direction) ---
    public void setTestPower(double power){
        if (test != null) {
            power = Math.max(-1.0, Math.min(1.0, power));
            test.setPower(power);
        }
    }
    // Getters for telemetry
    public double getPusherPower() { return (pusher != null) ? pusher.getPower() : 0.0; }
    public double getLoaderPower() { return (loader != null) ? loader.getPower() : 0.0; }
    // Add getter for Test servo
    public double getTestPower() { return (test != null) ? test.getPower() : 0.0; }
}