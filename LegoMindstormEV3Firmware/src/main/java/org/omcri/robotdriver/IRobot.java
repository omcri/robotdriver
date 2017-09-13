package org.omcri.robotdriver;

/**
 * Created by christophe on 14/08/2017.
 */
public interface IRobot {

    public void moveForward(final int duration);

    public void moveBackward(final int duration);

    public void turnLeft(final int angle);

    public void turnRight(final int angle);

    public void stop();
}
