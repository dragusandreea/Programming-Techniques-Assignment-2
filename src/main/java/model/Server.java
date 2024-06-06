package model;

import java.util.NoSuchElementException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

public class Server implements Runnable, Comparable<Server>{
     private BlockingQueue <Task> tasks;
     private AtomicInteger waitingPeriod = new AtomicInteger(0) ; //cat se asteapta la coada
     boolean open=false;

    private AtomicInteger avgWaitingPeriod = new AtomicInteger(0);
    private AtomicInteger avgServiceTime = new AtomicInteger(0);
    private AtomicInteger avgTasksNumber = new AtomicInteger(0);
    private AtomicInteger avgTasksServiceNumber = new AtomicInteger(0);

    private int ServerId;
    private static int index;

    public Server(int n) {
        this.tasks = new ArrayBlockingQueue<Task>(n) ;
        index++;
        this.ServerId = index;
        this.open = false;
    }

    @Override
    public void run() {
         //System.out.println("Ok")
        this.setOpen(true);
        while(true)
         {   //System.out.println("Ok2")  ;
             if(!tasks.isEmpty() && tasks.element() != null)
             {   // System.out.println("Ok3")  ;
                 try {
                     Task task = tasks.element();
                     Thread.sleep(1000);
                     if(task.getServiceTime() > 0)
                     {
                         task.setServiceTime(task.getServiceTime() - 1);
                         //System.out.println(task.getServiceTime() - 1);
                         this.waitingPeriod.addAndGet(-1);
                        // this.avgWaitingPeriod.addAndGet(-1);

                         if(task.getServiceTime() == 0)
                         {
                             //scot din coada
                             try
                             {
                                 tasks.remove();
                                 //avgTasksNumber.addAndGet(-1);
                             }
                             catch(NoSuchElementException e)
                             {
                                 System.out.println(e.getMessage()) ;
                             }
                             /*
                             if(tasks.isEmpty() )
                             {
                                 this.open = false;
                                 break;
                             }
                             */
                         }
                     }

                 } catch (Exception e) {
                     e.printStackTrace();
                 }

             }
         }

    }

    public void addTask (Task newTask)
    {
        tasks.add(newTask);

        this.open = true;
        this.avgTasksNumber.addAndGet(1);
        this.avgTasksServiceNumber.addAndGet(1);
        this.avgWaitingPeriod.addAndGet(waitingPeriod.get());
        this.waitingPeriod.addAndGet(newTask.getServiceTime());
        this.avgServiceTime.addAndGet(newTask.getServiceTime());

         //run();
        //System.out.println("addTask");
    }

    public float getAverageWaitingPeriod()
    {
       // System.out.println("avgWaitingPeriod=" + avgWaitingPeriod.get() + " avgTaskNumber"+ avgTasksNumber + );
        if( avgWaitingPeriod.get() != 0 &&  avgTasksNumber.get() != 0)
        {
            return ((float) avgWaitingPeriod.get()) / avgTasksNumber.get();
        }
        else
        {
            return 0;
        }

    }

    public float getAverageServiceTime()
    {
        if( avgServiceTime.get() != 0 &&  avgTasksServiceNumber.get() != 0)
        {
            return ((float) avgServiceTime.get()) / avgTasksServiceNumber.get();
        }
        else
        {
            return 0;
        }
    }

    @Override
    public String toString()
    {
        String result;
        if(this.isOpen() == false)
        {  //tasks.size() == 0
            result = "closed queue";
        }
        else
        {
            result = tasks.toString();
        }

        return result;
    }



    public BlockingQueue<Task> getTasks() {
        return tasks;
    }

    public void setTasks(BlockingQueue<Task> tasks) {
        this.tasks = tasks;
    }

    public AtomicInteger getWaitingPeriod() {
        return waitingPeriod;
    }

    public void setWaitingPeriod(AtomicInteger waitingPeriod) {
        this.waitingPeriod = waitingPeriod;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public AtomicInteger getAvgWaitingPeriod() {
        return avgWaitingPeriod;
    }

    public void setAvgWaitingPeriod(AtomicInteger avgWaitingPeriod) {
        this.avgWaitingPeriod = avgWaitingPeriod;
    }

    public AtomicInteger getAvgTasksNumber() {
        return avgTasksNumber;
    }

    public void setAvgTasksNumber(AtomicInteger avgTasksNumber) {
        this.avgTasksNumber = avgTasksNumber;
    }

    public int getServerId() {
        return ServerId;
    }

    public void setServerId(int serverId) {
        ServerId = serverId;
    }

    public static int getIndex() {
        return index;
    }

    public static void setIndex(int index) {
        Server.index = index;
    }

    @Override
    public int compareTo(Server o) {
        if( this.getWaitingPeriod().get() < o.getWaitingPeriod().get() )
            return -1;
        else
        {
            if( this.getWaitingPeriod().get() > o.getWaitingPeriod().get() )
                return 1;
            else
                return 0;
            

        }

    }
}
