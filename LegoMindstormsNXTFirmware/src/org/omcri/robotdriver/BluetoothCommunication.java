/**
 * 
 */
package org.omcri.robotdriver;

import lejos.nxt.LCD;
import lejos.nxt.comm.Bluetooth;
import lejos.nxt.comm.NXTConnection;

/**
 * @author gdherbom
 *
 */
public class BluetoothCommunication {

	NXTConnection waitBluetoothConnexion() {
		LCD.drawString("I'm waiting for", 0, 2);
		LCD.drawString("a Bluetooth", 0, 3);
		LCD.drawString("connection", 0, 4);
		return Bluetooth.waitForConnection();
		//connection = Bluetooth.waitForConnection(2000, NXTConnection.RAW);
	}
	
}
