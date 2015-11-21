/*
 * ProConSys.java
 * 
 * Version : 1.0 Date : 11-01-2014
 * 
 * Revisions : $Log Initial version$
 */

import java.util.Scanner;


/**
 * prototype of lamp production via simple producer-consumer problem.
 * Using Threads
 * 
 * @author Dhruv Gala
 */
public class ProConSys {

	static int counter1 = 0;
	static int counter2 = 0;
	static int MAX ;
	static int sharedInventory[] = new int[5];
	
	/**
	 * 
	 * @param args
	 */
	public static void main(String args[]){
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the maximum capacity of the Inventory:");
		MAX = sc.nextInt();
		sc.close();
		
		
		Thread p_base = new Thread(new producerBase(sharedInventory));
		Thread p_socket = new Thread(new producerSocket(sharedInventory));
		Thread p_stand = new Thread(new producerStand(sharedInventory));
		Thread p_screw = new Thread(new producerScrew(sharedInventory));
		Thread p_lightbulb = new Thread(new producerLightBulb(sharedInventory));
		Thread consumer1 = new Thread(new Consumer(1,sharedInventory));
		Thread consumer2 = new Thread(new Consumer(2,sharedInventory));
		
		p_base.start();
		p_socket.start();
		p_stand.start();
		p_screw.start();
		p_lightbulb.start();
		consumer1.start();
		consumer2.start();
		 
	}
}
