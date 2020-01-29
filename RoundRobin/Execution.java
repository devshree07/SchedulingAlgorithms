import java.util.*;//Including necessary libraries
public class Execution 
{
	//Declaring variable for quanta,quanta quanta_count and process id
	int quanta,pid,quanta_count = 0;
	long start_burst,end_burst;
	
	//Declaration of instances of various classes
	Analysis[] a;
	Analysis a1 = new Analysis(quanta,quanta);
	MainGUI g;
	GUIQueue gui_queue;
	analysisGUI a_gui;
	ListIterator<Process> iterator;
	//Calling the parameterized constructor of execution class 
	public Execution(int quanta,Analysis[] a,MainGUI g,GUIQueue gui_queue,analysisGUI a_gui) 
	{
		this.quanta = quanta;
		this.a = a;
		this.g = g;
		this.gui_queue = gui_queue;
		this.a_gui = a_gui;
		 
	}
	//Calling the execute function for process execution
	void execute(LinkedList<Process> ready_queue,LinkedList<Process> block_queue,LinkedList<Process> new_queue) throws InterruptedException 
	{
		iterator = ready_queue.listIterator();
		while(true) 
		{
			synchronized(this) //Using "synchronized" keyword to enable synchronization among multiple threads
			{
				if(ready_queue.size()==0) //Checking if the ready queue size is 0 or not, if it is then ask the process to wait
				{
					wait();
				}
				System.out.println("----------------------------------------------");
				Process t = (Process) ready_queue.removeFirst(); //Remove the first process from ready queue for execution
				pid = t.pid;
				//System call for burst time
				start_burst=System.currentTimeMillis();
				
				//Counting upto the time quanta
				while((t.count>0) && (quanta_count<quanta))
				{
					//Updating the status value 
					g.SetData("running", pid, 2);
					System.out.print("\n");
					System.out.println("Process "+pid+" is running:"+quanta_count);
					Thread.sleep(1000);
					//Decrementing the counter for every process
					t.count=t.count-1;
					//Printing the value of counts left
					System.out.println("Process counts left :"+t.count);
					//Updating the value of counts in GUI frame
					g.SetData(String.valueOf(t.count), pid, 3);
					//Incrementing counter values
					quanta_count++;
				}
				//System call for calculating end burst time
				end_burst=System.currentTimeMillis();
				//Setting the burst time
				a[pid].setBurst(end_burst-start_burst);
				quanta_count=0;
				//If the process counts are done then change the status to completed
				if(t.count==0) 
				{   
					System.out.println("Process "+pid+"Completed");
					//Setting end time and completion time
					a[pid].setEndTime();
					
					a[pid].calculateTurnaround();
					a[pid].calculateWaitingTime();
					
					g.SetData("completed", pid, 2);
					Thread.sleep(1000);
					//Setting data for GUI
					g.SetData("", pid, 0);
					g.SetData("", pid, 1);
					g.SetData("", pid, 2);
					g.SetData("", pid, 3);
					
					a_gui.SetData("Process "+pid, pid, 0);
					a_gui.SetData(""+t.processType, pid, 1);
					a_gui.SetData(""+a[pid].turnaroundTime/1000, pid, 2);
					a_gui.SetData(""+a[pid].burst/1000, pid, 3);
					a_gui.SetData(""+a[pid].waitingTime/1000, pid, 4);
				}
				//If the process type is I/O then add that process to block queue
				else if(t.processType=="I/O BOUND") 
				{
					System.out.println("Process "+pid+"Added to Block Queue");
					block_queue.addLast(t);
					//Update the status of process to "blocked" in GUI frame
					g.SetData("block queue", pid, 2);
				}
				else 
				{
					//If the process is processor bound then it enters the ready queue again
					System.out.println("Process "+pid+" enters queue again");
					ready_queue.addLast(t);
					//Update the status of process to "ready" in GUI frame
					g.SetData("ready queue", pid, 2);
				}
				
				System.out.print("\n");
				//Printing the ready and blocked queues
				System.out.println("Ready Queue:"+ready_queue);
				
				/*for(int i=0;i<ready_queue.size();i++) {
					arr[i]=ready_queue.listIterator(i).next().pid;
					System.out.println(arr[i]);
				}
				for(int i=0;i<ready_queue.size();i++) {
					gui_queue.SetData("Process "+arr[i], i, 1);
				}*/
				//gui_queue.queue(iterator, gui_queue);
				System.out.print("\n");
				System.out.println("Block Queue"+block_queue);
				
				Thread.sleep(1000);

				System.out.println("----------------------------------------------");
				//If there are no processes in new and ready queue then calculate the analysis
				if(ready_queue.size()==0 && new_queue.size()==0) 
				{
					long avgtr = a1.avgtr(a);
					long avgwt = a1.avgwt(a);
					long avgbt = a1.avgbt(a);
					System.out.println("Average Turn Around Time :"+avgtr/1000);
					System.out.println("Average Burst Time :"+avgbt/1000);
					System.out.println("Average Waiting Time :"+avgwt/1000);
					System.out.println(a.length);
					a_gui.SetData(""+avgtr/1000, 10, 2);
					a_gui.SetData(""+avgbt/1000, 10, 3);
					a_gui.SetData(""+avgwt/1000, 10, 4);
					a1.analysis(a);
					Thread.sleep(1000);
				}
				Thread.sleep(1000);
			}
		}
	}
}