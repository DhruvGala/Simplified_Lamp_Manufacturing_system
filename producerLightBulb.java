/*
 * producerLightBulb.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */


/**
 * The following class maintains the production unit of only Light bulbs. 
 * 
 * @author Dhruv Gala
 */
public class producerLightBulb extends ProConSys implements Runnable {

	private int speed = 560;
	private int MIN = 4;
	
	
	/**
	 * 
	 * @param sharedInventory
	 */
	public producerLightBulb(int[] sharedInventory){
	}
	
	
	/**
	 * The actual producer of light bulb units.
	 * It checks if the inventory of base has reached its max capacity --> stop production and wait
	 * 
	 */
	public void produce(){
		synchronized(sharedInventory){
			if(sharedInventory[4]<=MAX-MIN){
				sharedInventory[4]+=4;
				System.out.println("Stock of Light Bulb produced: "+sharedInventory[4]);
				sharedInventory.notifyAll();
			}
			else{
				System.out.println("Light Bulb producer waiting...");
				try {
					sharedInventory.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	
	/**
	 * The run method will execute according to the scheduler
	 * the sleep statement defines that the speed of each producer is different.
	 * 
	 */
	@Override
	public void run() {
		while(true){
			try {
				Thread.sleep(speed);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			produce();
		}
	}
}
