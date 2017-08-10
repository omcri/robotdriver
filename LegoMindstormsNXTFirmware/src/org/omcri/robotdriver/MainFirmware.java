/**
 * 
 */
package org.omcri.robotdriver;

//import java.io.IOException;

/**
 * @author gdherbom
 *
 */
public class MainFirmware {

	/**
	 * @param args
	 */
	public static void main(String[] args)  throws Exception {
		NxtRobotInterface robot = new Nxt2RaceCar();
		
		RobotController robotController = new RobotController(robot);
		
		if (args[1] != null)
		{
			System.out.println("argument : " + args[1]);
		}
	}

}
