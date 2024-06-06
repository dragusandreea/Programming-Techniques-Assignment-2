package controller;

import model.Scheduler;
import model.Server;
import model.Task;

import javax.swing.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;

import java.io.File;
import java.io.IOException;

public class SimulationManager implements Runnable{
    public int numberOfClients;
    private int numberOfQueues;
    private int simulationInterval;
    private int minArrival;
    private int maxArrival;
    private int minService;
    private int maxService;
    private JTextArea textArea;
    private ArrayList<Task> tasks;
    Scheduler scheduler;

    private boolean normalMode = false;
    private boolean test1Mode = false;
    private boolean test2Mode = false;
    private boolean test3Mode = true;

    File file1;
    File file2;
    File file3;

    BufferedWriter bufferedWriter1;
    BufferedWriter bufferedWriter2;
    BufferedWriter bufferedWriter3;

    FileWriter myWriter1;
    FileWriter myWriter2;
    FileWriter myWriter3;

    float avgWaitingTime = 0;
    float avgServiceTime = 0;

    int nrTasks = 0;
    int max = 0;
    int peakHour = 0;

    public SimulationManager(int numberOfClients, int numberOfQueues, int simulationInterval, int minArrival, int maxArrival, int minService, int maxService, JTextArea textArea) {
        this.numberOfClients = numberOfClients;
        this.numberOfQueues = numberOfQueues;
        this.simulationInterval = simulationInterval;
        this.minArrival = minArrival;
        this.maxArrival = maxArrival;
        this.minService = minService;
        this.maxService = maxService;
        this.textArea = textArea;
        tasks = new ArrayList<>();

        file1 = new File("test1.txt");
        file2 = new File("test2.txt");
        file3 = new File("test3.txt");

        try {
            myWriter1 = new FileWriter(file1,true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        bufferedWriter1 = new BufferedWriter(myWriter1);

        try {
            myWriter2 = new FileWriter(file2,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bufferedWriter2 = new BufferedWriter(myWriter2);

        try {
            myWriter3 = new FileWriter(file3,true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        bufferedWriter3 = new BufferedWriter(myWriter3);


        generate();
        scheduler = new Scheduler(numberOfQueues, numberOfClients);

        for(Thread t: scheduler.getServersThreads())
        {
            t.start();
        }

    }

    public void generate()
    {
        for(int i=0; i<numberOfClients;i++)
        {
            int service = minService +(int) ( Math.random() * (maxService - minService));
            int arrival = minArrival + (int)( Math.random()  * (maxArrival - minArrival));
            Task generatedTask = new Task(arrival,service);
            //System.out.println("minService = "+ minService + " maxService"+ maxService+ " service="+service);
            tasks.add(generatedTask);
        }

        Collections.sort(tasks);
      // System.out.println(tasks);

    }
    @Override
    public void run()
    {
        String result2 = "";
        //generate();
     int time = 0;
     //Scheduler scheduler = new Scheduler(numberOfQueues, numberOfClients);
      //verific daca mai e vreun server deschis
        boolean opened = false;
        for(Server s: scheduler.getServers())
        {
            if(s.getTasks().size() != 0)
            {
                opened = true;
            }


        }
       //float avgWait =  scheduler.GetAverageWaitingPeriod();
     while((time < simulationInterval && !tasks.isEmpty()) || (time < simulationInterval && opened) ) {
         while (!tasks.isEmpty() && tasks.get(0).getArrivalTime() == time) {
             scheduler.dispatchTask(tasks.get(0));
             tasks.remove(0);
         }

         //afisare
         String result = "";
         result += "\nTime:" + time + "\n";
         result += "Queues:";
         for (Task task : tasks) {
             result += task;
         }
         result+="\n";
         result += scheduler.toString();
         //System.out.println("Salut");
         if(normalMode == true)
         {
             textArea.append(result);
         }
         else
         {
             if(test1Mode == true)
             {

                 try
                 {
                        bufferedWriter1.write(result);
                 }
                 catch(IOException e)
                 {
                     System.out.println(e.getMessage());
                 }

             }

             if(test2Mode == true)
             {
                 try
                 {
                     bufferedWriter2.write(result);
                 }
                 catch(IOException e)
                 {
                     System.out.println(e.getMessage());
                 }
             }

             if(test3Mode == true)
             {
                 try
                 {
                     bufferedWriter3.write(result);
                 }
                 catch(IOException e)
                 {
                     System.out.println(e.getMessage());
                 }
             }

         }
         //peakHour
         for(Server s: scheduler.getServers())
         {
             nrTasks += s.getTasks().size();
         }
         if(nrTasks > max)
         {
             max = nrTasks;
             peakHour = time;
         }
         nrTasks = 0;
         time++;
         try {
             Thread.sleep(1000);
         } catch (Exception e) {
             System.out.println(e.getMessage());
         }
         opened = false;

         for(Server s: scheduler.getServers())
         {
             if(s.getTasks().peek() != null)
             {
                 opened = true;
             }

             avgWaitingTime += s.getAverageWaitingPeriod();
             avgServiceTime += s.getAverageServiceTime();

         }
         if(avgWaitingTime != 0) {
            // System.out.println("avgWaitingtime="+ avgWaitingTime + "/"+ "numberOfQueues="+numberOfQueues);
             avgWaitingTime = avgWaitingTime / numberOfQueues;
         }
         if(avgServiceTime != 0)
         {
             avgServiceTime = avgServiceTime / numberOfQueues;
         }
     }
        //System.out.println("avgWaitingTime="+avgWaitingTime);
        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(2);
        result2+="\navgWaitingTime="+df.format(avgWaitingTime);
        result2+="\navgServiceTime="+df.format(avgServiceTime);
        result2+="\npeakHour="+peakHour;

        if(normalMode == true)
        {
            textArea.append(result2);
        }

        if(test1Mode == true)
        {

            try
            {
                bufferedWriter1.write(result2);
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }

        }

        if(test2Mode == true)
        {
            try
            {
                bufferedWriter2.write(result2);
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }

        if(test3Mode == true)
        {
            try
            {
                bufferedWriter3.write(result2);
            }
            catch(IOException e)
            {
                System.out.println(e.getMessage());
            }
        }
        //textArea.append("\navgWaitingTime="+avgWaitingTime);
        //textArea.append("\navgServiceTime="+avgServiceTime);
        //textArea.append("\npeakHour="+peakHour);

     // wait+=avgWait;
    // textArea.append(wait);

        try
        {
            if(test1Mode == true)
            bufferedWriter1.close();

            if(test2Mode == true)
            bufferedWriter2.close();

            if(test3Mode == true)
            bufferedWriter3.close();
        }
        catch(IOException e)
        {
            System.out.println(e.getMessage());
        }
     scheduler.stop();

    }
}
