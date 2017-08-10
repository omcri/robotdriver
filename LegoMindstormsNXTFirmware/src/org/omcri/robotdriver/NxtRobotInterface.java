/**
 * 
 */
package org.omcri.robotdriver;

/**
 * @author gdherbom
 *
 */
public interface NxtRobotInterface {

	public void moveForward(final int duration);
	
	public void moveBackward(final int duration);
	
	public void turnLeft(final int angle);
	
	public void turnRight(final int angle);
	
	public int getUltraSonic();
	
	public void stop();
}
