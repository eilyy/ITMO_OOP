package system.staff;

import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public interface IEmployee {
    public void addSubordinate(int subordinateId) throws Exception;
    public void removeSubordinate(int subordinateId);
    public Collection<Employee> getSubordinates();
    public int getId();
    public String getName();
    public int getLevel();
    public void takeTask(int taskId) throws Exception;
    public void removeTask(int taskId);
    public void commitTask(int taskId, String comment, long time) throws Exception;
    public Collection<Task> getTasks();
    public Vector<Task> getCommittedTasks();
}
