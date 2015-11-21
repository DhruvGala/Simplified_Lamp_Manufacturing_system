/*
 * producerBase.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */


/**
 * The following class maintains the production unit of only base. 
 * 
 * @author Dhruv Gala
 */

public class producerBase extends ProConSys implements Runnable {

	private int speed = 800;
	private int MIN = 2;
	
	
	/**
	 * 
	 * @param sharedInventory
	 */
	public producerBase(int[] sharedInventory){
	}
	
	
	/**
	 * The actual producer of base units.
	 * It checks if the inventory of base has reached its max capacity --> stop production and wait
	 * 
	 */
	public void produce(){
		synchronized(sharedInventory){
			if(sharedInventory[0]<=MAX-MIN){
				sharedInventory[0]+=2;
				
				System.out.println("Stock of Base produced: "+sharedInventory[0]);
				sharedInventory.notifyAll();
			}
			else{
				System.out.println("Base producer waiting...");
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
