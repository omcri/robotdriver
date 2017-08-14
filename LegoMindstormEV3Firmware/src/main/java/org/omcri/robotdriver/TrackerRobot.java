package org.omcri.robotdriver;

import lejos.hardware.motor.Motor;

/**
 * Created by christophe on 14/08/2017.
 */
public class TrackerRobot extends AbstractEV3Robot {

    protected static final int SPEED = 600; // TODO: check the possibility to change the SPEED via command

    public static int moving = 0; // TODO: why this variable?


    @Override
    public void moveForward(final int duration) {
        Thread t = new Thread() {
            public void run() {
                try {
                    moving ++;
                    Motor.A.setSpeed(SPEED);
                    Motor.B.setSpeed(SPEED);
                    Motor.A.backward();
                    Motor.B.backward();

                    Thread.sleep(duration);
                    moving--;

                    if (moving == 0) {
                        Motor.A.stop();
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
    public void moveBackward(final int duration) {
        Thread t = new Thread() {
            public void run() {
                try {
                    moving ++;
                    Motor.A.setSpeed(SPEED);
                    Motor.B.setSpeed(SPEED);
                    Motor.A.forward();
                    Motor.B.forward();

                    Thread.sleep(duration);
                    moving--;

                    if (moving == 0) {
                        Motor.A.stop();
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
    public void turnLeft(final int angle) {
        Thread t = new Thread() {
            public void run() {
                Motor.A.rotate(angle);
            }
        };
        t.start();
    }

    @Override
    public void turnRight(final int angle) {
        Thread t = new Thread() {
            public void run() {
                Motor.B.rotate(angle);
            }
        };
        t.start();
    }

}
