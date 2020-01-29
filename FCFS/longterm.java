import java.util.*;//Including necessary libraries

public class longterm extends Thread//Extending the thread class for implementing run function 
{
	//Declaring linked lists for the ready queue and blocked queue
	LinkedList<Process> ready_queue ;
	LinkedList<Process> block_queue ;
	
	//Reference variables for execution and GUI classes
	Execution p;
	MainGUI g;
	GUIQueue gui_queue;
	//Declaring linked list for the new queue
	LinkedList<Process> new_queue;
	ListIterator<Process> iterator;
	//Parameterized constructor of the long term class
	public longterm(LinkedList<Process> block_queue,LinkedList<Process> ready_queue, LinkedList<Process> new_queue,Execution p,MainGUI g,GUIQueue gui_queue) 
	{
		this.ready_queue = ready_queue;
		this.block_queue = block_queue;
		this.new_queue = new_queue;
		this.p = p;
		this.g=g;
		this.gui_queue = gui_queue;
		iterator = new_queue.listIterator(); 
	}
	//Implementing the run method 
	public void run() 
	{
		//Reference variable for Process class
		Process t1=null;
		while(true) 
		{
			synchronized(this) //Using "synchronized" keyword to enable synchronization among multiple threads
			{
				if(new_queue.size()==0) //If the new queue size is 0 then make thread sleep
				{
					try 
					{
						Thread.sleep(1000);
					} catch (InterruptedException e) 
					{
						e.printStackTrace();
					}
				}
				else 
				{
					//Printing the new queue and its size
					System.out.println("Size of new process :" +new_queue.size());
					System.out.print("\n");
					for(int i=0;i<new_queue.size();i++) {
						gui_queue.SetData("", i, 0);
					}
					for(int i=0;i<new_queue.size();i++) {
						gui_queue.SetData("Process "+i, i, 0);
					}
					
					System.out.println("New Queue:"+new_queue);
					//If the ready queue size is less than 6 then remove the first process
					if(ready_queue.size()+1<6) 
					{
						t1=(Process) new_queue.removeFirst();
						//Change the status to ready queue and add the process to the ready queue
						g.SetData("ready queue", t1.pid, 2);
						t1.start();
						ready_queue.add(t1);
						System.out.print("\n");
						//Displaying the top value in the ready queue
						System.out.println(ready_queue.peekLast()+"Added To The Ready Queue");
						System.out.print("\n");
					}
				}
				//Notifying all the other threads after above operations
				try 
				{
					notify();
					Thread.sleep(1000);
				} catch (InterruptedException e) 
				{
					e.printStackTrace();
				}
			}
		}
	}
}
