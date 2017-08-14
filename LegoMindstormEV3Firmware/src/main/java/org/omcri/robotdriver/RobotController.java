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
                        robot.moveForward(commandParameter);
                        break;

                    case 2:
                        robot.stop();
                        return;

                    case 3:
                        // TODO: see to be linked with the confusion between angle and duration for the rotation of robogator
                        if (commandParameter == 1000) {
                            commandParameter = 45;
                        }
                        robot.turnLeft(commandParameter);
                        break;

                    case 4:
                        // TODO: see to be linked with the confusion between angle and duration for the rotation of robogator
                        if (commandParameter == 1000) {
                            commandParameter = 45;
                        }
                        robot.turnRight(commandParameter);
                        break;

                    case 5:
                        robot.moveBackward(commandParameter);
                        break;

                    case 6:
                        robot.stop();
                        break;

                    case 7:
                        // TODO: why this?
                        if (commandParameter == 1000) {
                            commandParameter = 45;
                        }
                        //raceCarRobot.openMouth(commandParameter);
                        break;

                    case 8:
                        // TODO: why this?
                        if (commandParameter == 1000) {
                            commandParameter = 45;
                        }
                        //raceCarRobot.closeMouth(commandParameter);
                        break;

                    case 9:
                        // ackValue = robot.getUltraSonic();
                        break;

                    default:
                        return;
                }
                connection.writeToClient(ackValue);
            }
        }
    }

}
