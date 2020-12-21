package system.staff;

import system.IReportsManagement;
import system.IStaffManagement;
import system.dal.IReportsDB;
import system.dal.IStaffDB;
import system.dal.ITasksDB;
import system.tasks.Task;

import java.util.Collection;
import java.util.HashMap;
import java.util.Vector;

public abstract class AbstractEmployee implements IEmployee {
    protected int employeeId;
    protected String name;
    protected int level;

    protected IStaffDB sdb;
    protected ITasksDB tdb;
    protected IReportsDB rdb;
    protected IReportsManagement rm;
    protected IStaffManagement sm;

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
    public Vector<Task> getCommittedTasks() {
        return this.committedTasks;
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
    public void removeSubordinate(int subordinateId) {
        this.subordinates.remove(subordinateId);
    }

    @Override
    public Collection<Task> getTasks() {
        return this.myTasks.values();
    }

    @Override
    public Vector<Task> getAllTasks() {
        return this.allTasks;
    }
}
