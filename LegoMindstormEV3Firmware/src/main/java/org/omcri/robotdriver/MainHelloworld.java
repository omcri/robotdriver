package org.omcri.robotdriver;

import lejos.hardware.Battery;
import lejos.hardware.Sound;
import lejos.utility.Delay;

/**
 * Created by christophe on 14/08/2017.
 */
public class MainHelloworld {

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        String message = args.length > 0 ? args[0] : "Hello world!";

        System.out.println(message);

        Delay.msDelay(500);
        Sound.beep();

        System.out.println();
        System.out.println("Battery voltage:");
        System.out.println(Battery.getVoltage());

        Delay.msDelay(3000);
    }

}
