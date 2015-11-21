/*
 * producerSocket.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */


/**
 * The following class maintains the production unit of only Socket 
 * 
 * @author Dhruv Gala
 */

public class producerSocket extends ProConSys implements Runnable {
	
	private int speed = 700;
	private int MIN = 7;
	
	
	/**
	 * 
	 * @param sharedInventory
	 */
	public producerSocket(int[] sharedInventory){
	}
	
	
	/**
	 * The actual producer of Socket units.
	 * It checks if the inventory of base has reached its max capacity --> stop production and wait
	 * 
	 */
	public void produce(){
		synchronized(sharedInventory){
			if(sharedInventory[1]<=MAX-MIN){
				sharedInventory[1]+=7;
				System.out.println("Stock of Socket produced: "+sharedInventory[1]);
				sharedInventory.notifyAll();
			}
			else{
				System.out.println("Socket producer waiting...");
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
