package model;

import java.util.ArrayList;

public class ShortestStrategy implements Strategy {

    @Override
    public int addTask(ArrayList<Server> serverList, Task t) {

        int minQueue = Integer.MAX_VALUE;
        int idMinQueue = 0;

        for(Server server: serverList)
        {
            if(server.getTasks().size() < minQueue)
            {
                idMinQueue = server.getServerId();
            }
        }

        for(Server server: serverList)
        {
            if(server.getServerId() == idMinQueue)
            {
                server.addTask(t);
                break;
            }
        }
        return 0;
    }
}
