package org.omcri.robotdriver;

import lejos.hardware.Button;
import lejos.hardware.lcd.LCD;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

/**
 * Created by christophe on 14/08/2017.
 */
public class RobotController {

    TCPCommunication tcpComm = new TCPCommunication();
    // Create the robot
    // TODO: add a method to choose between the different robots
    TrackerRobot trackerRobot;


    DataInputStream dis;
    DataOutputStream dos;
    EV3Connection connection;
    Thread connectionThread;
    boolean endingProgram = false;

    IRobot robot;


    public RobotController(IRobot iRobot) throws IOException {
        robot = iRobot;
    }

    public void start() throws IOException {
        // Launch connection thread and wait for a connection.
        connectionThread = new Thread(){
            @Override
            public void run() {
                while (!endingProgram) {
                    try {
                        if (robot instanceof TrackerRobot) {
                            LCD.drawString("Hi, I'm EV3 Tracker", 0, 0);
                        }
                        // Wait for a tcp connection...
                        connection = tcpComm.waitTCPConnexion();

                        // The received message reader
                        applyCommand();

                        LCD.clear();
                    } catch (Exception e) {
                        try {
                            connection.disconnect();
                        } catch (IOException ex) {
                        }
                    }
                }
            }
        };

        connectionThread.start();

        while(!endingProgram){
            if(Button.waitForAnyPress() == Button.ID_ESCAPE) {
                endingProgram = true;
                if (connection != null) {
                    connection.disconnect();
                }
            }
        }
    }



    /**
     * Commands protocol:
     * 0 : error
     * 1 : move forward
     * 2 : stop
     * 3 : turn left
     * 4 : turn right
     * 5 : move backward
     *
     * @throws IOException
     */
    private void applyCommand() throws IOException {
        int command;
        int commandParameter = 1;
        // TODO : manage speed.
        int speed = 600;
        while (!endingProgram) {
            LCD.clear();
            LCD.drawString("Command waiting...", 0, 1);

            try {
                command = connection.readTCPCommand();
                commandParameter = connection.readTCPCommand();
            } catch (Exception e) {
                return;
            }

            System.out.println("command=" + command + "commandParameter" + commandParameter);

            Integer ackValue = 1; // TODO: see the acknowledgement method
            if (command != 0) {
                switch (command) {
                    case 1:
                        robot.moveForward(commandParameter, speed);
                        break;

                    case 2:
                        robot.stop();
                        return;

                    case 3:
                        robot.turnLeft(commandParameter, 600);
                        break;

                    case 4:
                        robot.turnRight(commandParameter, 600);
                        break;

                    case 5:
                        robot.moveBackward(commandParameter, speed);
                        break;

                    case 6:
                        robot.stop();
                        break;

                    case 7:
                        break;

                    case 8:
                        break;

                    case 9:
                        // ackValue = robot.getUltraSonic();
                        break;

                    default:
                        return;
                }
                LCD.clear();
                connection.writeToClient(ackValue);
            }
        }
    }

}
