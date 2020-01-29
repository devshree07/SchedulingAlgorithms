import java.util.*; //Including necessary libraries
public class Application //Class containing the main function
{
	@SuppressWarnings("resource")
	public static void main(String[] args) throws InterruptedException 
	{
		//Object creation for Analysis and GUI classes
		Analysis[] analytics = new Analysis[10];
		MainGUI main_gui = new MainGUI();
		GUIQueue gui_queue = new GUIQueue();
		analysisGUI a_gui = new analysisGUI();
		//Initializing and Declaring the scanner class for taking the user input from console
		Scanner in = new Scanner(System.in);
		
		//Taking input for time slice/quanta
		System.out.println("Enter the time quantum:");	
		int time_slice = in.nextInt();
		
		//Initialization and Declaration for 3 Linked lists(one for each new,ready and blocked queue)
		LinkedList<Process> ready_queue =new LinkedList<>();
		LinkedList<Process> block_queue =new LinkedList<>();
		LinkedList<Process> new_queue =new LinkedList<>();
		
		//Setting the GUI window visible
		//window.frame.setVisible(true);
		
		//Object creation and memory allocation for  Process Creation ,Execution, long term and medium term classes
		Execution execute = new Execution(time_slice,analytics,main_gui,gui_queue,a_gui);
		//In all the below listed classes the parameterized constructor implements the run function of Thread class by extending it
		ProcessCreation pc = new ProcessCreation(block_queue,ready_queue,new_queue,execute,analytics,main_gui);
		longterm lt = new longterm(block_queue,ready_queue,new_queue,execute,main_gui,gui_queue);
		mediumterm mt = new mediumterm(block_queue,ready_queue,main_gui);
		
		//Starting the threads for process creation,long term scheduler and medium term scheduler
		pc.start();
		lt.start();
		mt.start();
		//Joining the threads one by one for synchronized execution 
		pc.join();
		lt.join();
		mt.join();
	}
}
