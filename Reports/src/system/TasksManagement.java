package system;

import system.databases.StaffDB;
import system.databases.TasksDB;
import system.staff.Employee;
import system.tasks.Status;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class TasksManagement {

    private StaffDB sdb = new StaffDB();
    private TasksDB tdb = new TasksDB();

    private static int taskId = 0;

    private int generateTaskId() {
        taskId++;
        return taskId;
    }

    public void setLastChangeTime(long time, int taskId) {
        tdb.setLastChangeTime(time, taskId);
    }

    public void removeLastChangeTime(long time) {
        tdb.removeLastChangeTime(time);
    }

    public void newTask(String name, String description, long time) {
        int newId = this.generateTaskId();
        tdb.addTask(newId, name, description, time);
    }

    public void assignTaskDoer(int taskId, int employeeId, long time) throws Exception {
        Task task = tdb.getTaskById(taskId);

        if(task.getStatus() != Status.RESOLVED) {
            if(task.getStatus() == Status.ACTIVE)
                task.removeDoer();
            task.assignDoer(sdb.getEmployeeById(employeeId), time);
        }
        else
            throw new Exception("This task is not available to do");
    }

    public Collection<Task> getTasksByDoer(int employeeId) throws Exception {
        return sdb.getEmployeeById(employeeId).getTasks();
    }

    public Vector<Task> getCommittedEmployeesTasks(int employeeId) throws Exception {
        return sdb.getEmployeeById(employeeId).getCommittedTasks();
    }

    public Task getTaskByLastChangeTime(long time) throws Exception {
        if(!tdb.getLastChangeIds().contains(time))
            throw new Exception("No tasks with such last change time");
        return tdb.getTaskById(tdb.getLastChangeTime(time));
    }

    public Task getTaskByInitTime(long time) throws Exception {
        if(!tdb.getCreateTimeIds().contains(time))
            throw new Exception("No tasks with such creation time");
        return tdb.getTaskById(tdb.getCreateTime(time));
    }

    public Collection<Task> getSubordinatesTasks(int managerId) throws Exception {
        var so = sdb.getEmployeeById(managerId).getSubordinates();
        if(so.isEmpty())
            throw new Exception("This employee has no subordinates");
        Collection<Task> result = new Vector<>();
        for(Employee i : so) {
            result.addAll(i.getTasks());
        }
        return result;
    }
}
