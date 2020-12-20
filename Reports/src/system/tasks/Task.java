package system.tasks;

import system.TasksManagement;
import system.databases.ReportsDB;
import system.staff.IEmployee;

import java.util.HashMap;

public class Task {
    private int taskId;
    private String name, description, log = "===== LOG =====\n";
    private IEmployee doer;
    private long lastChangeTime = -1;

    private Status status;

    private HashMap<Long, String> changeLog = new HashMap<>();

    public Task(int taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
        this.status = Status.OPEN;
    }

    public Status getStatus() {
        return this.status;
    }

    public void assignDoer(IEmployee employee, long time) throws Exception {
        this.doer = employee;
        this.doer.takeTask(this.taskId);
        this.log += "[" + time + "] NEW DOER ASSIGNED WITH ID=" + employee.getId() + "\n";
        this.changeLog.put(time, "[NEW DOER ASSIGNED WITH ID=" + employee.getId() + "]");
        this.renewDB(time);
        this.status = Status.ACTIVE;
    }

    public void removeDoer() {
        this.doer.removeTask(this.taskId);
    }

    public void commit(String commit, long time) {
        this.log += "[" + time + "] " + commit + "\n";
        this.changeLog.put(time, "[NEW COMMIT]" + commit);
        this.renewDB(time);
    }

    public void finish(long time) throws Exception {
        if(this.status == Status.RESOLVED)
            throw new Exception("This task is already resolved");
        this.doer.removeTask(this.taskId);
        this.log += "[" + time + "] THE TASK IS NOW RESOLVED\n";
        this.changeLog.put(time, "[THE TASK IS NOW RESOLVED]");
        this.status = Status.RESOLVED;
        ReportsDB.getInstance().newResolvedTask(this.taskId);
    }

    public String getLog() {
        return this.log + "===============";
    }

    public IEmployee getDoer() {
        return this.doer;
    }

    private void renewDB(long time) {
        if(this.lastChangeTime != -1)
            TasksManagement.getInstance().removeLastChangeTime(this.lastChangeTime);
        TasksManagement.getInstance().setLastChangeTime(time, this.taskId);
        this.lastChangeTime = time;
    }
}
