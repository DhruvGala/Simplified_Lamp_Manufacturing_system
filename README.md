# Simplified_Lamp_Manufacturing_system
A multi-threaded system to simulate the lamp manufacturing process by different materials manufacturing, using Java.

The following system simulates a simplified lamp manufacturing system. 

Raw materials producers: Screw, Base, Stamd, Socket, LightBulb. 
Each of these producers have an array to store its produced items. The size of the array is determined by the user.
All producers can produce as many items that could be stored in theri respective arrays.
At a given time the Screw producer produces 4 screws, the Base producer produces 2 bases, the Stand producer produces 4 stands, the Socket producer produces 7 sockets and the LightBulb producer produces 4 loght bulbs.
Two consumers consume all the items independently in order to contruct a lamp.
The consumer stops constructing a lamp if the needed materials is not available and continue to construct when the supply allows to construct a lamp.

Here, 'busy waiting' is not used instead, concepts like notify(), notifyAll() and wait() are used for proper functioning of the threads.

A producer can only start if there is space to store te produced items, and a consumer can only start if there is enough material present to asscemble a lamp.
