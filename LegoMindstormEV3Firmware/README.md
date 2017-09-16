# robotdriver: Lego Mindstorms EV3
Drivers for robots used in OMCRI project.

## Build and install the EV3 application

The application is built with LeJOS framework (http://www.lejos.org/) tools.

This project use maven to build it :

<b>mvn clean install -DskipTests</b>

## Deploy the application on Lejos EV3 :
Connect your compute to EV3 (using wifi or Bluetooth PAN) and copy the jar file to programs directory :

Using scp command :

scp -oKexAlgorithms=+diffie-hellman-group1-sha1 LegoMindstormEV3Firmware-1.0.jar root@10.0.1.1:/home/lejos/programs/