/*
 * producerStand.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */


/**
 * The following class maintains the production unit of only Stands. 
 * 
 * @author Dhruv Gala
 */

public class producerStand extends ProConSys implements Runnable {
	
	private int speed = 600;
	private int MIN = 4;
	
	
	/**
	 * 
	 * @param sharedInventory
	 */
	public producerStand(int[] sharedInventory){
	}
	
	
	/**
	 * The actual producer of Stand units.
	 * It checks if the inventory of base has reached its max capacity --> stop production and wait
	 * 
	 */
	public void produce(){
		synchronized(sharedInventory){
			if(sharedInventory[2]<=MAX-MIN){
				sharedInventory[2]+=4;
				System.out.println("Stock of Stand produced: "+sharedInventory[2]);
				sharedInventory.notifyAll();
			}
			else{
				System.out.println("Stand producer waiting...");
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
