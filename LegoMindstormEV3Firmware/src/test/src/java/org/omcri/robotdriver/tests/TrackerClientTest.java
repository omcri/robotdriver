package org.omcri.robotdriver.tests;
import junit.framework.Assert;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * Created by christophe on 14/08/2017.
 */
public class TrackerClientTest {

    private String ipRobot = "10.0.1.1";
    private int robotPort = 1111;


    private Socket socket = null;
    private DataOutputStream dos = null;
    private DataInputStream dis = null;

    @Test
    public void connectionAndMoveTest() {
        connectToRobot();
        System.out.println("Connected !!!");

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
        }
        // Lauch a move.
        // move_forward(2500);

        turnLeft(1500);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
        }
        stop();
        turnRight(1500);
        try {
            Thread.sleep(1500);
        } catch (InterruptedException ex) {
        }
        stop();
        disconnectFromRobot();
    }

    private void connectToRobot() {
        try {
            socket = new Socket(ipRobot, robotPort);
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            System.out.println("Impossible to connect : " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     *
     */
    private void disconnectFromRobot() {
        try {
            if (dos != null) {
                dos.writeInt(2);
                dos.flush();
                closeInputAndOutput();
            } else {
                throw new IOException("already disconnected !");
            }
        } catch (IOException ex) {
            System.out.println("Impossible to disconnect : " + ex.getMessage());
            ex.printStackTrace();
        }

    }

    private void closeInputAndOutput() {

        if (dos != null && dis != null && socket != null) {
        try {
            dos.close();
            dis.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Impossible to close stream : " + ex.getMessage());
            ex.printStackTrace();
        }} else {
            System.out.println("Warning dos or dis or socket is null !!!");
        }


    }

    public void turnLeft(int angle) {
        try {
            dos.writeInt(3);
            dos.flush();
            dos.writeInt(angle);
            dos.flush();
            get_acknowledgement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void turnRight(int angle) {
        try {
            dos.writeInt(4);
            dos.flush();
            dos.writeInt(angle);
            dos.flush();
            get_acknowledgement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move_forward(int duration) {
        try {
            dos.writeInt(1);
            dos.flush();
            dos.writeInt(duration);
            dos.flush();
            get_acknowledgement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void move_backward(int duration) {
        try {
            dos.writeInt(5);
            dos.flush();
            dos.writeInt(duration);
            dos.flush();
            get_acknowledgement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stop() {
        try {
            dos.writeInt(6);
            dos.flush();
            dos.writeInt(0);
            dos.flush();
            get_acknowledgement();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private Integer get_acknowledgement() throws IOException{
        try {
            int command = dis.readInt();

            System.out.println("Command successful.");
            return command;
        } catch (IOException e) {
            failed_acknowledgment();
        }
        return null;
    }

    private void failed_acknowledgment() throws IOException{
        System.out.println("TCP connection lost");
        disconnectFromRobot();
    }

}
