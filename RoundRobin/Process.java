import java.util.*;//Including necessary libraries
public class Process extends Thread//Extending the thread class for implementing run function 
{
	//Declaration of variables for process counts , process type and id
	int count;
	int pid;
	String processType;
	
	// Declaration for 3 Linked lists(one for each new,ready and blocked queue)
	LinkedList<Process> ready_queue;
	LinkedList<Process> block_queue;
	LinkedList<Process> new_queue;
	
	//Reference variable for Execution class
	Execution p;
	
	//Parameterized constructor for Process class
	public Process(int pid,String ptype, int count, LinkedList<Process> block_queue,LinkedList<Process> ready_queue, LinkedList<Process> new_queue,Execution p)
	{
		//Allocating the values of variables and queues using "this" referencing
		this.pid = pid;
		this.processType=ptype;
		this.count = count;
		this.ready_queue = ready_queue;
		this.block_queue = block_queue;
		this.new_queue = new_queue;
		this.p = p;
	}
	//Implementing the run method 
	public void run() 
	{
		try 
		{
			//Calling the execute function of Execution class
			p.execute(ready_queue,block_queue,new_queue);
			
		} catch (InterruptedException e) 
		{
			e.printStackTrace();
		}
	}
}
