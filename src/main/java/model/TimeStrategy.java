package model;

import java.util.Collections;
import java.util.ArrayList;

public class TimeStrategy implements Strategy{

    @Override
    public int addTask(ArrayList<Server> serverList, Task task) {

        Collections.sort(serverList);
        serverList.get(0).setOpen(true);
        serverList.get(0).addTask(task);

        //System.out.println(serverList);
        if(serverList.get(0).isOpen() == true)
            return 1;
        else
            return 0;
    }
}
