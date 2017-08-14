package org.omcri.robotdriver;

import lejos.hardware.motor.Motor;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3UltrasonicSensor;

/**
 * Created by christophe on 14/08/2017.
 */
public abstract class AbstractEV3Robot implements IRobot {

    public abstract void moveForward(final int duration);

    public abstract void moveBackward(final int duration);

    public abstract void turnLeft(final int angle);

    public abstract void turnRight(final int angle);

    public void stop() {
        Motor.A.stop();
        Motor.B.stop();
        Motor.C.stop();
        Motor.D.stop();
    }

//    public int getUltraSonic(){
//        EV3UltrasonicSensor sonic = new EV3UltrasonicSensor(SensorPort.S4);
//        sonic.getDistanceMode().sampleSize();
//        return sonic.getDistance();
//    }


}
