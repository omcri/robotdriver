package org.omcri.robotdriver;

import lejos.hardware.motor.Motor;

/**
 * Created by christophe on 14/08/2017.
 */
public class TrackerRobot extends AbstractEV3Robot {

    /**
     * For forwarding the robot, we used Motor A and B connection.
     *
     * @param duration
     * @param speed    number of complete rotations per seconds.
     */
    @Override
    public void moveForward(final int duration, final int speed) {
        if (speed == 0) {
            stop();
        }
        Thread t = new Thread() {
            public void run() {
                try {
                    moving++;
                    Motor.A.setSpeed(speed);
                    Motor.B.setSpeed(speed);
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


    /**
     * For backwarding the robot we used Motor A and B connection.
     *
     * @param duration
     * @param speed
     */
    @Override
    public void moveBackward(final int duration, final int speed) {
        if (speed == 0) {
            stop();
        }
        Thread t = new Thread() {
            public void run() {
                try {
                    moving++;
                    Motor.A.setSpeed(speed);
                    Motor.B.setSpeed(speed);
                    Motor.A.forward();
                    Motor.B.forward();

                    Thread.sleep(duration);
                    moving--;

                    if (moving == 0) {
                        Motor.A.stop();
                        Motor.B.stop();
                    }
                } catch (InterruptedException e) {
                    // e.printStackTrace();
                }
            }
        };
        t.start();
    }

    /**
     * To turn left, this method use motor B.
     *
     * @param duration
     * @param speed
     */
    @Override
    public void turnLeft(final int duration, final int speed) {
        Thread t = new Thread() {
            public void run() {
                try {
                    moving++;
                    Motor.B.setSpeed(speed);
                    Motor.B.backward();
                    Thread.sleep(duration);
                    moving--;
                    if (moving == 0) {
                        Motor.B.stop();
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        t.start();
    }

    /**
     * Turn tracker on right using only motor A command.
     * @param duration
     * @param speed
     */
    @Override
    public void turnRight(final int duration, final int speed) {
        Thread t = new Thread() {
            public void run() {
                try {
                    moving++;
                    Motor.A.setSpeed(speed);
                    Motor.A.backward();
                    Thread.sleep(duration);
                    moving--;
                    if (moving == 0) {
                        Motor.B.stop();
                    }
                } catch (InterruptedException ex) {
                }
            }
        };
        t.start();
    }

    @Override
    public void stop() {
        super.stop();
        moving = 0;
    }

    @Override
    public boolean isMoving() {
        return super.isMoving();
    }

}
