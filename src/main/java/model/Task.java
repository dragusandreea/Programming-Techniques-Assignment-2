package model;

public class Task implements Comparable<Task>{
    private int id;
    private int arrivalTime;
    private int serviceTime;
    private static int index;

    public Task(int arrivalTime, int serviceTime) {

        index++;
        this.id = index;
        this.arrivalTime = arrivalTime;
        this.serviceTime = serviceTime;

    }

    public String toString()
    {
        return "(id:"+id+",a:"+arrivalTime+",s:"+serviceTime+")";
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(int arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public int getServiceTime() {
        return serviceTime;
    }

    public synchronized void  setServiceTime(int serviceTime) {
        this.serviceTime = serviceTime;
    }

    @Override
    public int compareTo(Task o) {
        if(this.getArrivalTime() < o.getArrivalTime())
        {
            return -1;
        }
        else
        {
            if(this.getArrivalTime() == o.getArrivalTime())
                return 0;
            else
                return 1;
        }
    }
}
