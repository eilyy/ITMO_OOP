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
    public void finishTask(int taskId, long time) throws Exception;
    public void fillDailyReport(String filling) throws Exception;
    public void fillSprintReport(String filling) throws Exception;
    public Collection<Task> getSprintTasks() throws Exception;
    public Collection<Task> getSubordinatesSprintTasks() throws Exception;
    public Vector<Integer> getResolvedTasksToday();
    public Collection<Task> getTasks();
    public Vector<Task> getCommittedTasks();
    public Vector<Task> getAllTasks();
}
