package model;

import java.util.ArrayList;
import java.util.List;

public class Scheduler {
    private ArrayList<Server> servers;
    private int maxServersNumber;
    private int maxTasksPerServerNumber;
    private Strategy timeStrategy;
    private ArrayList<Thread> serversThreads;

    public Scheduler(int maxServersNumber, int maxTasksPerServerNumber)
    {
        this.maxServersNumber = maxServersNumber;
        this.maxTasksPerServerNumber = maxTasksPerServerNumber;

        this.servers = new ArrayList<Server>(maxServersNumber);

        this.serversThreads = new ArrayList<Thread>(maxServersNumber);

        for(int i = 0; i < maxServersNumber; i++)
        {
            Server server = new Server(maxTasksPerServerNumber);
            servers.add(server);

            Thread thread = new Thread(servers.get(i));
            serversThreads.add(thread);

        }

    }

    public void dispatchTask(Task task)
    {
        this.timeStrategy =  new TimeStrategy();

        int open = timeStrategy.addTask(servers,task);

        //if(open == 0)
        {
           // servers.get(0).setOpen(true);

        }
    }
    public float GetAverageWaitingPeriod()
    {   float sum = 0;

        for(Server server: servers)
        {
            sum+=server.getAverageWaitingPeriod();
        }
        return sum/servers.size();
    }

    @Override
    public String toString()
    {
        String result="";
        for(Server server: servers)
        {
            result+="Server_Id"+server.getServerId()+": "+server.toString()+"\n";
        }

        return result;
    }

    public void stop()
    {
        for(Server server: servers)
        {
            server.setOpen(false);
        }
    }

    public ArrayList<Server> getServers() {
        return servers;
    }

    public void setServers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    public int getMaxServersNumber() {
        return maxServersNumber;
    }

    public void setMaxServersNumber(int maxServersNumber) {
        this.maxServersNumber = maxServersNumber;
    }

    public int getMaxTasksPerServerNumber() {
        return maxTasksPerServerNumber;
    }

    public void setMaxTasksPerServerNumber(int maxTasksPerServerNumber) {
        this.maxTasksPerServerNumber = maxTasksPerServerNumber;
    }

    public Strategy getTimeStrategy() {
        return timeStrategy;
    }

    public void setTimeStrategy(TimeStrategy timeStrategy) {
        this.timeStrategy = timeStrategy;
    }

    public ArrayList<Thread> getServersThreads() {
        return serversThreads;
    }

    public void setServersThreads(ArrayList<Thread> serversThreads) {
        this.serversThreads = serversThreads;
    }
}
