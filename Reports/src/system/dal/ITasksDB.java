package system.dal;

import system.tasks.Task;

import java.util.Collection;

public interface ITasksDB {
    void setLastChangeTime(long time, int taskId);

    void removeLastChangeTime(long time);

    Task getTaskById(int id) throws Exception;

    void addTask(int newId, String name, String description, Task task, long time);

    Collection<Long> getLastChangeIds();

    int getLastChangeTime(long time);

    Collection<Long> getCreateTimeIds();

    int getCreateTime(long time);
}
