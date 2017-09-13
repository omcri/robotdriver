package org.omcri.robotdriver;

import lejos.hardware.Bluetooth;
import lejos.hardware.lcd.LCD;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by christophe on 14/08/2017.
 */
public class TCPCommunication {

    public EV3Connection waitTCPConnexion() throws IOException {
            LCD.drawString("I'm waiting for", 0, 2);
            LCD.drawString("a TCP", 0, 3);
            LCD.drawString("connection", 0, 4);

            return this.waitForConnection();
            //connection = Bluetooth.waitForConnection(2000, NXTConnection.RAW)
    }

    public EV3Connection waitForConnection() throws IOException {

        ServerSocket serv = new ServerSocket(1111);
        Socket s = serv.accept(); //Wait for client to connect

        return new EV3Connection(serv, new DataInputStream(s.getInputStream()), new DataOutputStream(s.getOutputStream()));
    }

}

class EV3Connection {
    private DataInputStream dataIn;
    private DataOutputStream dataOut;
    private boolean connected = false;
    private ServerSocket socket;

    public EV3Connection(ServerSocket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        this.dataIn = dataInputStream;
        this.dataOut = dataOutputStream;
        this.socket = socket;
        connected = true;
    }

    public DataInputStream getDataIn() {
        return dataIn;
    }

    public DataOutputStream getDataOut() {
        return dataOut;
    }

    public boolean isConnected() {
        return connected;
    }

    public int readTCPCommand() throws IOException {
        if (dataIn == null) {
            throw new IOException("No command value sent on TCP !");
        }
        return dataIn.readInt();
    }

    /**
     * return an acknowledgment to the client
     */
    public void serverAck(Integer value) throws IOException {
        dataOut.writeInt(value);
        dataOut.flush();
    }

    public void writeToClient(Integer value) throws IOException {
        dataOut.writeInt(value);
        dataOut.flush();
    }

    public void disconnect() throws IOException {
        try {
            if (dataOut != null) {
                dataOut.writeInt(2);
                dataOut.flush();
                dataOut.close();

            } else {
                throw new IOException("already disconnected !");
            }
        } catch (IOException ex) {
            System.out.println("Impossible to disconnect : " + ex.getMessage());
            ex.printStackTrace();
        }
        try {
            dataIn.close();
        } catch (IOException ex) {
        }
        try {
            socket.close();
        } catch (IOException ex) {
        }
    }


}