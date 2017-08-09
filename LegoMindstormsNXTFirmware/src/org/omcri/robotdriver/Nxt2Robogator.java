/**
 * 
 */
package org.omcri.robotdriver;

import lejos.nxt.Motor;

/**
 * @author gdherbom
 *
 */
public class Nxt2Robogator extends NxtRobot {
	
	protected static final int SPEED = 600;
	public static int moving = 0;

	
	@Override
	public void moveForward(int duration) {
		Thread t = new Thread() {
			public void run() {
				try {
					moving ++;
					Motor.C.setSpeed(SPEED);
					Motor.B.setSpeed(SPEED);
					Motor.C.backward();
					Motor.B.backward();

					Thread.sleep(duration);
					moving--;

					if (moving == 0) {
						Motor.C.stop();
						Motor.B.stop();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	@Override
	public void moveBackward(int duration) {
		Thread t = new Thread() {
			public void run() {
				try {
					moving++;
					Motor.C.setSpeed(SPEED);
					Motor.B.setSpeed(SPEED);
					Motor.C.forward();
					Motor.B.forward();

					Thread.sleep(duration);
					moving--;

					if (moving == 0) {
						Motor.B.stop();
						Motor.C.stop();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}


	@Override
	public void turnLeft(int angle) {
		long duration = angle * 10; 

		Thread t = new Thread() {
			public void run() {
				try {
					moving++;
					Motor.C.setSpeed(SPEED);
					Motor.C.backward();
					Motor.B.setSpeed(SPEED);
					Motor.B.forward();

					Thread.sleep(duration);

					moving--;

					if (moving == 0) {
						Motor.C.stop();
						Motor.B.stop();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}

	@Override
	public void turnRight(int angle) {
		// TODO: review the computation of duration. Initially, rotation was made via a duration and not an angle. 
		long duration = angle * 10;
		
		Thread t = new Thread() {
			public void run() {
				try {
					moving++;
					Motor.B.setSpeed(SPEED);
					Motor.B.backward();
					Motor.C.setSpeed(SPEED);
					Motor.C.forward();

					Thread.sleep(duration);

					moving--;

					if (moving == 0) {
						Motor.C.stop();
						Motor.B.stop();
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		};
		t.start();
	}
	
	public void openMouth(final int angle){
		Thread t = new Thread() {
			public void run() {
				Motor.A.rotate(45);
			}
		};
		t.start();
	}
	
	public void closeMouth(final int angle){
		Thread t = new Thread() {
			public void run() {
				Motor.A.rotate(-45);
			}
		};
		t.start();
	}
}
