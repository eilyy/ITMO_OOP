package system;

import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public interface ITasksManagement {
    void setLastChangeTime(long time, int taskId);

    void removeLastChangeTime(long time);

    Task getTaskById(int id) throws Exception;

    void newTask(String name, String description, long time) throws Exception;

    void assignTaskDoer(int taskId, int employeeId, long time) throws Exception;

    Collection<Task> getTasksByDoer(int employeeId) throws Exception;

    Vector<Task> getCommittedEmployeesTasks(int employeeId) throws Exception;

    Task getTaskByLastChangeTime(long time) throws Exception;

    Task getTaskByInitTime(long time) throws Exception;

    Collection<Task> getSubordinatesTasks(int managerId) throws Exception;
}
