package org.omcri.robotdriver;

/**
 * Created by christophe on 14/08/2017.
 */
public interface IRobot {

    public void moveForward(int duration, int speed);

    public void moveBackward(int duration, int speed);

    public void turnLeft(final int duration, int speed);

    public void turnRight(final int duration, final int speed);


    public void stop();


}
