package frc.robot.cameras;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;

public class Cameras extends Thread {

    CameraServer camServer;
    UsbCamera usbCamera;

    static Cameras instance = new Cameras();

    private Cameras() {
        camServer = CameraServer.getInstance();

        usbCamera = camServer.startAutomaticCapture("cameras", 0);
    }

    public static Cameras getInstance() {
        if (instance == null) {
            instance = new Cameras();
        }
        return instance;
    }

    public void startCameras() {
        camServer.getServer().setSource(usbCamera);
    }

    public void run() {
        while(true) {}
    }
}