package model;

import java.util.ArrayList;

public interface Strategy {
    public int addTask(ArrayList<Server> serverList, Task t);
}
