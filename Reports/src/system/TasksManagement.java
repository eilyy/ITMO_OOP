package system;

import system.databases.ReportsDB;
import system.databases.StaffDB;
import system.databases.TasksDB;
import system.staff.Employee;
import system.staff.IEmployee;
import system.tasks.Status;
import system.tasks.Task;

import java.util.Collection;
import java.util.Vector;

public class TasksManagement {
    private static TasksManagement instance;

    private static StaffDB sdb = StaffDB.getInstance();
    private static TasksDB tdb = TasksDB.getInstance();
    private static ReportsDB rdb = ReportsDB.getInstance();

    private static int taskId = 0;

    public static TasksManagement getInstance() {
        if(instance == null)
            instance = new TasksManagement();
        return instance;
    }

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

    public void newTask(String name, String description, long time) throws Exception {
        int newId = this.generateTaskId();

        tdb.addTask(newId, name, description, time);

        if(rdb.onSprint())
            rdb.getCurrentSprint().addTask(newId);
    }

    public void assignTaskDoer(int taskId, int employeeId, long time) throws Exception {
        if(employeeId == 0 && sdb.getTeamLead() == null)
            throw new Exception("There is no team-lead in this system");

        Task task = tdb.getTaskById(taskId);
        IEmployee doer = (employeeId == 0) ? sdb.getTeamLead() : sdb.getEmployeeById(employeeId);

        if(task.getStatus() != Status.RESOLVED) {
            if(task.getStatus() == Status.ACTIVE)
                task.removeDoer();
            task.assignDoer(doer, time);
        }
        else
            throw new Exception("This task is unavailable to do");
    }

    public Collection<Task> getTasksByDoer(int employeeId) throws Exception {
        return (employeeId == 0) ? sdb.getTeamLead().getAllTasks() : sdb.getEmployeeById(employeeId).getAllTasks();
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
        var so = (managerId == 0) ? sdb.getTeamLead().getSubordinates() : sdb.getEmployeeById(managerId).getSubordinates();
        if(so.isEmpty())
            throw new Exception("This employee has no subordinates");
        Collection<Task> result = new Vector<>();
        for(Employee i : so) {
            result.addAll(i.getAllTasks());
        }
        return result;
    }
}
