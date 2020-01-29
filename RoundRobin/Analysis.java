public class Analysis 
{
	long turnaroundTime;
	long waitingTime;
	long startTime;
	int pid;
	long arrival;
	long burst=0;
	long originalBurst;
	long endTime;
	int contextSwitch = 0;
	
	public Analysis(int processID, long arrivalTime) {
		this.pid = processID;
		this.arrival = arrivalTime; 
		this.originalBurst = burst;
	}
	public String getProcessInfo() {
		return "pid= " + pid + "\narrival time= " + arrival + "\nContext Switches: " + contextSwitch + "\nburst time remaining= " + burst;
	}
	
	public void update(int value) 
	{   
		if (burst <= value) 
		{
			burst = 0;
		}
		else if(burst > value) 
		{
			burst = burst - value;
		}
	}
	public void setBurst(long time) 
	{
		burst=burst + time;
	}
	public long getBurst() 
	{
		return burst;
	}
	
	public void setStartTime() {
		startTime = System.currentTimeMillis();
	}
	
	public long getStartTime() 
	{
		return startTime;
	}
	
	public void setEndTime() 
	{
		endTime = System.currentTimeMillis();
	}
	
	public long getEndTime() 
	{
		return endTime;
	}
	public void calculateTurnaround() 
	{
		turnaroundTime = endTime - arrival;
	}
	public void calculateWaitingTime() 
	{
		waitingTime = turnaroundTime - burst;
	}
	public int getPid() 
	{
		int pid1 = pid;
		return pid1;
	}
	
	public long avgbt(Analysis[] a)
	{
		long avgbt = 0;
		for(int i=0;i<a.length;i++) 
		{
			avgbt+=a[i].burst;
		}
		
		return avgbt/a.length;
	}
	
	
	public long avgwt(Analysis[] a)
	{
		long avgwt = 0;
		for(int i=0;i<a.length;i++) 
		{
			avgwt+=a[i].waitingTime;
		}
		
		return avgwt/a.length;
	}
	
	public long avgtr(Analysis[] a)
	{
		long avgtr = 0;
		for(int i=0;i<a.length;i++) 
		{
			avgtr+=a[i].turnaroundTime;
		}
		
		return avgtr/a.length;
	}
	
	public void analysis(Analysis[] a) 
	{
		for(int i=0;i<a.length;i++) 
		{
			System.out.println("Start Time : "+a[i].getStartTime()+" End Time :"+a[i].getEndTime()+" Turn Around Time : "+a[i].turnaroundTime+" Burst Time : "+a[i].getBurst());
		}
	}
}