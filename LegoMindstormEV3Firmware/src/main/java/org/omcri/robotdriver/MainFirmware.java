package org.omcri.robotdriver;

import java.io.IOException;

/**
 * Created by christophe on 14/08/2017.
 */
public class MainFirmware {

    public static void main(String[] args) {

        IRobot robot = new TrackerRobot();

        try {
            RobotController robotController = new RobotController(robot);
            robotController.start();
        } catch (IOException ex) {
            System.out.println("IO Error : " + ex.getMessage());
        }

        // if (args[1] != null) {
        //     System.out.println("argument : " + args[1]);
        // }

    }
}
