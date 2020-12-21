package system.dal;

import system.tasks.Task;

import java.util.Collection;
import java.util.HashMap;

public class TasksDB implements ITasksDB {
    private static HashMap<Integer, Task> tasks = new HashMap<>();
    private static HashMap<Long, Integer> createTime = new HashMap<>();
    private static HashMap<Long, Integer> lastChangeTime = new HashMap<>();

    @Override
    public void setLastChangeTime(long time, int taskId) {
        lastChangeTime.put(time, taskId);
    }

    @Override
    public void removeLastChangeTime(long time) {
        lastChangeTime.remove(time);
    }

    @Override
    public Task getTaskById(int id) throws Exception {
        if(!tasks.containsKey(id))
            throw new Exception("Task with given ID doesn't exist");
        return tasks.get(id);
    }

    @Override
    public void addTask(int newId, String name, String description, Task task, long time) {
        tasks.put(newId, task);
        createTime.put(time, newId);
    }

    @Override
    public Collection<Long> getLastChangeIds() {
        return lastChangeTime.keySet();
    }

    @Override
    public int getLastChangeTime(long time) {
        return lastChangeTime.get(time);
    }

    @Override
    public Collection<Long> getCreateTimeIds() {
        return createTime.keySet();
    }

    @Override
    public int getCreateTime(long time) {
        return createTime.get(time);
    }
}
