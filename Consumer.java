/*
 * Consumer.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */


/**
 * The following class is maintains the consumer part of the producer-consumer problem.
 * 
 * @author Dhruv Gala
 */

public class Consumer extends ProConSys implements Runnable{

	private int ID;
	
	
	/**
	 * 
	 * @param sharedInventory
	 */
	public Consumer(int ID, int[] sharedInventory){
		this.ID = ID;
	}
	
	
	/**
	 * This method checks if the inventory is the empty
	 * 
	 * @return
	 */
	public boolean stockIsEmpty(){
		for(int i=0;i<5;i++){
			if(sharedInventory[i]==0)
				return true;
		}
		
		return false;
	}
	
	
	/**
	 * This method check if the stock is full then initiates the consumption and final production
	 * of the lamp product.
	 * 
	 * @return
	 */
	public boolean stockReady(){
		if(sharedInventory[0]<1)//base
			return false;
		if(sharedInventory[1]<3)//socket
			return false;
		if(sharedInventory[2]<1)//stand
			return false;
		if(sharedInventory[3]<4)//screw
			return false;
		if(sharedInventory[4]<3)//light bulb
			return false;
		
		return true;
	}
	
	/**
	 * This method makes the actual consumption and outputs the number of lamps produced.
	 * Each consumer produces different number of lamps.
	 */
	public void consume(){
		synchronized(sharedInventory){
			if(stockReady()){
				sharedInventory[0]-=1;
				sharedInventory[1]-=3;
				sharedInventory[2]-=1;
				sharedInventory[3]-=4;
				sharedInventory[4]-=3;
				
				if(ID==1)
					System.out.println("Consumer no:"+ID+" --> "+(++counter1)+" Lamps produced so far");
				else if(ID==2)
					System.out.println("Consumer no:"+ID+" --> "+(++counter2)+" Lamps produced so far");
				
				System.out.println("=====Inventory Update=====");
				System.out.println("Base:"+sharedInventory[0]+", Socket: "+sharedInventory[1]+", Stand: "+sharedInventory[2]+
						", Screw: "+sharedInventory[3]+", LightBulb: "+sharedInventory[4]);
				
				System.out.println();
				sharedInventory.notifyAll();
				
			} else
				try {
					sharedInventory.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
		}
	}


	/**
	 * The method checks for stock if empty and then calls the consume method.
	 * 
	 */
	@Override
	public void run() {
		while(true){
			if(!stockIsEmpty()){
				consume();
			}
			else{
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
