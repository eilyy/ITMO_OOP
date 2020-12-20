package system.databases;

import system.tasks.Task;

import java.util.Collection;
import java.util.HashMap;

public class TasksDB {
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Long, Integer> createTime = new HashMap<>();
    private static HashMap<Long, Integer> lastChangeTime = new HashMap<>();

    public void setLastChangeTime(long time, int taskId) {
        lastChangeTime.put(time, taskId);
    }

    public void removeLastChangeTime(long time) {
        lastChangeTime.remove(time);
    }

    public Task getTaskById(int id) throws Exception {
        if(!tasks.containsKey(id))
            throw new Exception("Task with given ID doesn't exist");
        return tasks.get(id);
    }

    public void addTask(int newId, String name, String description, long time) {
        tasks.put(newId, new Task(newId, name, description));
        createTime.put(time, newId);
    }

    public Collection<Long> getLastChangeIds() {
        return lastChangeTime.keySet();
    }

    public int getLastChangeTime(long time) {
        return lastChangeTime.get(time);
    }

    public Collection<Long> getCreateTimeIds() {
        return createTime.keySet();
    }

    public int getCreateTime(long time) {
        return createTime.get(time);
    }
}
