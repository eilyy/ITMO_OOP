package system.staff;

import system.TasksManagement;
import system.databases.StaffDB;
import system.databases.TasksDB;
import system.tasks.Task;

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

    @Override
    public void commitTask(int taskId, String comment, long time) throws Exception {
        if(!this.myTasks.containsKey(taskId))
            throw new Exception("No such task in this employee's task list");
        this.myTasks.get(taskId).commit(comment, time);
        this.committedTasks.add(this.myTasks.get(taskId));
    }

    @Override
    public Vector<Task> getCommittedTasks() {
        return this.committedTasks;
    }

    @Override
    public void takeTask(int taskId) throws Exception {
        this.myTasks.put(taskId, new TasksDB().getTaskById(taskId));
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

        var sdb = new StaffDB();

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
}
