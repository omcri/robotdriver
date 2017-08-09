/**
 * 
 */
package org.omcri.robotdriver;

import lejos.nxt.Motor;
import lejos.nxt.SensorPort;
import lejos.nxt.UltrasonicSensor;

/**
 * @author gdherbom
 *
 */
public abstract class NxtRobot {
	
	public abstract void moveForward(final int duration);
	
	public abstract void moveBackward(final int duration);
	
	public abstract void turnLeft(final int angle);
	
	public abstract void turnRight(final int angle);
	
	public void stop() {
		Motor.A.stop();
		Motor.B.stop();
		Motor.C.stop();
	}
	
	public static int getUltraSonic(){
		UltrasonicSensor sonic = new UltrasonicSensor(SensorPort.S4);
		return sonic.getDistance();
	}

}
