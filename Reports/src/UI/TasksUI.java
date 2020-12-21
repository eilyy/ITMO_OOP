package UI;

import system.ITasksManagement;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class TasksUI {
    private ITasksManagement tm;

    public TasksUI(ITasksManagement tasksManagement) {
        tm = tasksManagement;
    }

    public Task getTaskById(int id) throws Exception {
        return tm.getTaskById(id);
    }

    public void newTask(String name, String description, long time) throws Exception {
        tm.newTask(name, description, time);
    }

    public void assignTaskDoer(int taskId, int employeeId, long time) throws Exception {
        tm.assignTaskDoer(taskId, employeeId, time);
    }

    public Collection<Task> getTasksByDoer(int employeeId) throws Exception {
        return tm.getTasksByDoer(employeeId);
    }

    public Vector<Task> getCommittedEmployeesTasks(int employeeId) throws Exception {
        return tm.getCommittedEmployeesTasks(employeeId);
    }

    public Task getTaskByLastChangeTime(long time) throws Exception {
        return tm.getTaskByLastChangeTime(time);
    }

    public Task getTaskByInitTime(long time) throws Exception {
        return tm.getTaskByInitTime(time);
    }

    public Collection<Task> getSubordinatesTasks(int managerId) throws Exception {
        return tm.getSubordinatesTasks(managerId);
    }

    public ITasksManagement getTm() {
        return tm;
    }
}
