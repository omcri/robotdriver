package org.omcri.robotdriver;

import lejos.hardware.motor.Motor;

/**
 * Created by christophe on 14/08/2017.
 */
public abstract class AbstractEV3Robot implements IRobot {

    public static int moving = 0;

    public abstract void moveForward(int duration, int speed);

    public abstract void moveBackward(int duration, int speed);

    public abstract void turnLeft(final int duration, int speed);

    public abstract void turnRight(final int duration, int speed);

    public void stop() {
        Motor.A.stop();
        Motor.B.stop();
        Motor.C.stop();
        Motor.D.stop();
    }

    public boolean isMoving() {
        if (moving == 0) {
            return false;
        } else {
            return true;
        }
    }


//    public int getUltraSonic(){
//        EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S4);
//        sonic.getDistanceMode().sampleSize();
//        return sonic.getDistance();
//    }


}
