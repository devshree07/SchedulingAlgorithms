# SchedulingAlgorithms
Implementation of Round-Robin and First Come First Serve Algorithms in Java

**Introduction**</br>
CPU scheduling is a mechanism which allows one process to use the CPU resources while the execution of other processes is on hold or in waiting state due to unavailability of any CPU resource. Thus, making full use of the CPU. Whenever the CPU becomes idle, the operating system must select one of the processes in the ​ready queue​ to be executed. The selection process is carried out by the short-term scheduler (or CPU scheduler). The scheduler selects from among the processes in memory that are ready to execute, and allocates the CPU to one of them.</br>
**First Come First Serve Algorithm**</br>
First in, first out (FIFO), also known as a first come, first served (FCFS), is the simplest scheduling algorithm. FIFO simply queues processes in the order that they arrive in the ready queue.In this, the process that comes first will be executed first and next process starts only after the previous gets fully executed.</br>
**Working Flow**</br>
1. First a thread of process creation runs and dynamically creates process according to the random number generated between 5 to 10. Each value generated between 8 to 10 will lead to process creation. This work is done independently by a thread which helps to portray a real-time situation of randomly creating processes.
2. Now, the new process is placed in a “new queue” which stores all the new generated processes.
3. The long term scheduler is now invoked as new processes join the new queue. Long term scheduler shifts a process from new queue and places them in ready queue for execution.
4. The execution is done in a Round-Robin fashion. Where every process is in execution for the time equal to time quantum specified by the user.
5. The process is assigned count at the beginning of creation which is decremented every time clock. This way when the count of the process is zero that means the process has completed its execution.
6. Processes that are label I/O bound are treated in a different manner. Here, we consider that when an I/O bound process is brought into execution, after it is served for the time quantum the process is blocked waiting for an I/O event to occur.
7. The medium term scheduler is than invoked when an event occurs and the process is then brought back to running state directly.
8. The occurrence of an event is also portrayed as a decision of random number generated. A random number between 5 to 10 is generated from which at the occurrence of number 10 a blocked process is shifted to running state.
9. At the end of execution of all processes an analysis of the processes executed is done and presented to the user.
**Round Robin Algorithm**</br>
Round Robin is a CPU Scheduling Algorithm where each process is assigned a fixed time slot in a cyclic way.
1. It is simple, easy to implement, and starvation-free as all processes get fair share of CPU.
2. One of the most commonly used techniques in CPU scheduling as a core.
3. It is preemptive as processes are assigned CPU only for a fixed slice of time at
most.
4. The disadvantage of it is more overhead of context switching</br></br>
