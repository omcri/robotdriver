# robotdriver: Lego Mindstorms NXT2
Drivers for robots used in OMCRI project.

## Build and install the NXJ application

The application is built with LeJOS framework (http://www.lejos.org/) tools.
We use Eclipse to construct the binary.

First of all, check that your Eclipse environment is correctly configured. You can
follow the isntructions referenced here: http://www.lejos.org/nxt/nxj/tutorial/Preliminaries/UsingEclipse.htm
Make sure that you have installed the LeJOS Eclipse plugin and that you have
defined the NXJ_HOME field in the Eclipse menu: Window -> Preference, "leJOS NXJ" part.

To build the project and install the application on the NXT terminal:
- switch on the NXT and connect it though USB to the computer
- right click on the LegoMindstormsNXTFirmware project and select Run As LeJOS NXT Program. In the Eclipse console, you may read this message:

```
Linking ...
Program has been linked successfully
Uploading ...
leJOS NXJ> Upload successful in 9384 milliseconds
program has been uploaded
```

## Running the application

On the NXT, go to the Files menu, and choose the MainFirmware.nxj file, then select "Execute program". The application waits a connection via Bluetooth to interact with the robot.

See the omcri cloudrobotics project documentation to know how to control the robot with the OMCRI extension.

TODO: put a link
