package system.staff;

import system.ReportsManagement;
import system.databases.ReportsDB;
import system.databases.StaffDB;
import system.databases.TasksDB;
import system.databases.TimeDB;
import system.tasks.Task;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public abstract class AbstractEmployee implements IEmployee {
    protected int employeeId;
    protected String name;
    protected int level;

    protected HashMap<Integer, Employee> subordinates = new HashMap<>();

    protected HashMap<Integer, Task> myTasks = new HashMap<>();

    protected Vector<Task> committedTasks = new Vector<>();

    protected Vector<Task> allTasks = new Vector<>();

    @Override
    public void commitTask(int taskId, String comment, long time) throws Exception {
        if(!this.myTasks.containsKey(taskId))
            throw new Exception("No such task in this employee's task list");
        this.myTasks.get(taskId).commit(comment, time);
        if(!this.committedTasks.contains(this.myTasks.get(taskId)))
            this.committedTasks.add(this.myTasks.get(taskId));
    }

    @Override
    public void finishTask(int taskId, long time) throws Exception {
        if(!this.myTasks.containsKey(taskId))
            throw new Exception("No such task in this employee's task list");
        this.myTasks.get(taskId).finish(time);
        if(!this.committedTasks.contains(this.myTasks.get(taskId)))
            this.committedTasks.add(this.myTasks.get(taskId));
    }

    @Override
    public void fillDailyReport(String filling) throws Exception {
        ReportsDB.getInstance().getDailyReportByDay
                (TimeDB.getInstance().getDate().get(Calendar.YEAR),
                TimeDB.getInstance().getDate().get(Calendar.MONTH) + 1,
                TimeDB.getInstance().getDate().get(Calendar.DAY_OF_MONTH)).fillBody(this.employeeId, filling);
    }

    @Override
    public void fillSprintReport(String filling) throws Exception {
        ReportsDB.getInstance().getCurrentSprint().fillSprintReport(this.employeeId, filling);
    }

    @Override
    public Vector<Integer> getResolvedTasksToday() {
        return ReportsDB.getInstance().getResolvedTasksToday();
    }

    @Override
    public Vector<Task> getCommittedTasks() {
        return this.committedTasks;
    }

    @Override
    public void takeTask(int taskId) throws Exception {
        this.myTasks.put(taskId, TasksDB.getInstance().getTaskById(taskId));
        this.allTasks.add(TasksDB.getInstance().getTaskById(taskId));
    }

    @Override
    public void removeTask(int taskId) {
        this.myTasks.remove(taskId);
    }

    @Override
    public int getId() {
        return this.employeeId;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getLevel() {
        return this.level;
    }

    @Override
    public Collection<Employee> getSubordinates() {
        return this.subordinates.values();
    }

    @Override
    public void addSubordinate(int subordinateId) throws Exception {
        if(subordinateId == 0)
            throw new Exception("Team-lead cannot be subordinate");

        var sdb = StaffDB.getInstance();

        this.subordinates.put(subordinateId, sdb.getEmployeeById(subordinateId));
        sdb.getEmployeeById(subordinateId).changeManager(this);
    }

    @Override
    public void removeSubordinate(int subordinateId) {
        this.subordinates.remove(subordinateId);
    }

    @Override
    public Collection<Task> getTasks() {
        return this.myTasks.values();
    }

    @Override
    public Collection<Task> getSprintTasks() throws Exception {
        return ReportsManagement.getInstance().getSprintTasks(this.employeeId);
    }

    @Override
    public Vector<Task> getAllTasks() {
        return this.allTasks;
    }

    @Override
    public Collection<Task> getSubordinatesSprintTasks() throws Exception {
        if(this.subordinates.isEmpty())
            throw new Exception("This employee has no subordinates");
        return ReportsManagement.getInstance().getTasksOfUsersSubordinates(this.employeeId);
    }
}
