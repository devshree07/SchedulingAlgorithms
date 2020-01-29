
import java.util.*;//Including necessary libraries
public class ProcessCreation extends Thread//Extending the thread class for implementing run function
{
	//Declaration and initialization of variables for number of processes and event generation 
	int pType=1,numOfProcess=0;
	
	//Declaring the reference variable for Execution class,Process class,Analysis and GUI class
	Execution execute;
	Process t = null;
	Analysis[] analytics;
	MainGUI main_gui;
	
	// Declaration for 3 Linked lists(one for each new,ready and blocked queue)
	LinkedList<Process> ready_queue;
	LinkedList<Process> block_queue;
	LinkedList<Process> new_queue;
	
	//Parameterized constructor for the Process Creation class
	public ProcessCreation(LinkedList<Process> block_queue,LinkedList<Process> ready_queue, LinkedList<Process> new_queue, Execution execute, Analysis[] analytics,MainGUI main_gui) 
	{
		//Allocating the values of variables and queues using "this" referencing
		this.ready_queue = ready_queue;
		this.block_queue = block_queue;
		this.new_queue = new_queue;
		this.execute = execute;
		this.analytics = analytics;
		this.main_gui = main_gui;
	}
	//Implementing the run method 
	public void run() 
	{
		while(true) 
		{
			synchronized(this)//Using "synchronized" keyword to enable synchronization among multiple threads
			{
				if(numOfProcess<10) //Processing all the processes
				{
					int i=(int)(Math.random()*((10-5)+1))+5;//Generating random number between 5-10
					System.out.println(i);
					pType=(int)(Math.random()*((10-5)+1))+5; //Generating random number between 5-10
					if(i>=8) //Checking if it is 8,9,10 for process creation
					{
						String processType;
						if(pType>=9) //Checking if it is 9 or 10 to give it an I/O label
						{
							processType="I/O BOUND";
						}
						else 
						{
							processType="Process BOUND";
						}
						
						System.out.println("\t\t\t\t\t\tNew Process");
						//Generating random number for process counts
						int processCounts=(int)(Math.random()*((10-5)+1))+5;
						//Initializing and calling the constructor for process class
						t=new Process(numOfProcess,processType,processCounts,block_queue,ready_queue,new_queue,execute);
						//Adding the new process at the last of new queue
						new_queue.addLast(t);
						//Calling the analysis constructor for the processes to calculate various scheduling criteria
						analytics[numOfProcess] = new Analysis(numOfProcess,System.currentTimeMillis());
						//Setting the start time
						analytics[numOfProcess].setStartTime();
						//Setting the values of id,type,process counts and status to display on GUI frame
						main_gui.SetData(String.valueOf(t.pid), numOfProcess, 0);
						main_gui.SetData(t.processType, numOfProcess, 1);
						main_gui.SetData("new queue", numOfProcess, 2);
						main_gui.SetData(String.valueOf(t.count), numOfProcess, 3);
						
						try 
						{
							Thread.sleep(1000);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
						//Jumping to next process
						numOfProcess++;
						System.out.println(new_queue.peekLast()+"This process is added to new queue");
					}
					//After new queue size is greater than or equal to 5,then other threads are notified
					while(new_queue.size()>=2)
					{
						notify();
						try 
						{
							Thread.sleep(1000);
						} catch (InterruptedException e) 
						{
							e.printStackTrace();
						}
					}
				}
				notify();
			}
		}
	}
}
