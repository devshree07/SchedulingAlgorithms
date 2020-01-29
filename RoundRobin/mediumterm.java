import java.util.*;//Including necessary libraries
public class mediumterm extends Thread //Extending the thread class for implementing run function 
{
	//Declaring linked lists for the ready queue and blocked queue
	LinkedList<Process> ready_queue;
	LinkedList<Process> block_queue;
	MainGUI g;
	//Parameterized constructor of the medium term class
	public mediumterm(LinkedList<Process> block_queue,LinkedList<Process> ready_queue,MainGUI g) 
	{
		//Allocating the value of ready and blocked queue by "this" referencing
		this.block_queue = block_queue;
		this.ready_queue = ready_queue; 
		this.g = g;
	}
	//Implementing the run method 
	public void run() 
	{
		while(true) 
		{
			synchronized(this) //Using "synchronized" keyword to enable synchronization among multiple threads
			{
				if(block_queue.size()!=0) //Condition to check whether block queue is empty or not
				{
					int i=(int)(Math.random()*((10-5)+1))+5;//Generating random number between 5-10
					System.out.println("\t\t\t\t\t\t i:"+i);
					if(i==10) //If the number generated is 10 then add it to ready queue from the blocked queue
					{
						Process t = (Process) block_queue.removeFirst();//Popping the first value from blocked queue
						g.SetData("event occurs", t.pid, 2);
						ready_queue.addFirst(t); //Adding it to ready queue
						System.out.println("\n");
						System.out.println(ready_queue.peekFirst()+" Added to Ready Queue From Block Queue"); //Printing the top value of Ready queue
						System.out.println("\n");
					}
				}
				notify(); //Notifying the other threads to start 
				try 
				{
					Thread.sleep(1000); 
				} 
				catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
