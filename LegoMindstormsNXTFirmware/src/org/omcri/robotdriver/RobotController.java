/**
 * 
 */
package org.omcri.robotdriver;

/**
 * @author gdherbom
 *
 */

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.List;

import lejos.nxt.Button;
import lejos.nxt.LCD;
import lejos.nxt.comm.*;

/**
 * Main controller that manages the communication and the interactions with the client. 
 * @author gdherbom
 *
 */
public class RobotController {
	BluetoothCommunication bluetoothConnection = new BluetoothCommunication();
	// RobotAction
	
	DataInputStream dis;
	DataOutputStream dos;
	NXTConnection connection;
	Thread connectionThread;
	boolean endingProgramm = false;
	
	public RobotController() throws Exception {
		connectionThread = new Thread(){
			@Override
			public void run() {
				while (!endingProgramm) {
					try {
						LCD.drawString("Hi, I'm ClapTrap.", 0, 0);
						
						connection = bluetoothConnection.waitBluetoothConnexion();
	
						// The received message reader
						dis = connection.openDataInputStream();
						dos = connection.openDataOutputStream();
						applyCommand();
						
						LCD.clear();
					} catch (Exception e) {
					}
				}
			}
		};
		
		connectionThread.start();
		
		while(!endingProgramm){
			if(Button.waitForAnyPress() == Button.ID_ESCAPE){
				endingProgramm = true;
			}
		}
		return;
	}
	
	
	/**
	 * return an acknowledgment to the client
	 */
	private void serverAck(Integer value) throws IOException {
		dos.writeInt(value);
		dos.flush();
	}

	
	/**
	 * Commands protocol:
	 * 0 : error
	 * 2 : stop 
	 * 1 : move forward
	 * 3 : turn left
	 * 4 : turn right 
	 * 5 : move backward
	 * 
	 * @throws IOException
	 */
	private void applyCommand() throws IOException {
		int command = 0;
		int commandParameter = 1;

		while (!endingProgramm) {
			LCD.clear();
			LCD.drawString("Command waiting...", 0, 1);
			command = 0;
			commandParameter = 1000;
			
			try {
				command = dis.readInt();
				commandParameter = dis.readInt();
			} catch (Exception e) {
				return;
			}
			
			Integer ackValue = 1; // TODO: see the acknowledgement method
			if (command != 0) {
				switch (command) {
				case 1:
					Movements.moveForward(commandParameter);
					break;
					
				case 2:
					Movements.stop();
					return;

				case 3:
					// TODO: see to be linked with the confusion between angle and duration for the rotation of robogator
					if (commandParameter == 1000) {
						commandParameter = 45;
					}
					Movements.turnLeft(commandParameter);
					break;
				
				case 4:
					// TODO: see to be linked with the confusion between angle and duration for the rotation of robogator
					if (commandParameter == 1000) {
						commandParameter = 45;
					}
					Movements.turnRight(commandParameter);
					break;
				
				case 5:
					Movements.moveBackward(commandParameter);
					break;
				
				case 6:
					Movements.stop();
					break;
					
				case 7:
					// TODO: why this? 
					if (commandParameter == 1000) {
						commandParameter = 45;
					}
					Movements.openMouth(commandParameter);
					break;
					
				case 8:
					// TODO: why this? 
					if (commandParameter == 1000) {
						commandParameter = 45;
					}
					Movements.closeMouth(commandParameter);
					break;
					
				case 9:
					ackValue = Movements.getUltraSonic();
					break;
					
				default:
					return;
				}
				serverAck(ackValue);
			}
		}
	}
}
