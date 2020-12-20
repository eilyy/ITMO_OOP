package system.tasks;

import system.StaffManagement;
import system.TasksManagement;
import system.staff.IEmployee;

import java.util.HashMap;

public class Task {
    private int taskId;
    private String name, description, log = "===== LOG =====\n";
    private IEmployee doer;
    private long lastChangeTime = -1;

    private Status status;

    private HashMap<Long, String> changeLog = new HashMap<>();

    StaffManagement sm = new StaffManagement();

    public Task(int taskId, String name, String description) {
        this.taskId = taskId;
        this.name = name;
        this.description = description;
    }

    public Status getStatus() {
        return this.status;
    }

    public void assignDoer(IEmployee employee, long time) {
        this.doer = employee;
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

    public void finish(long time) {
        this.doer.removeTask(this.taskId);
        this.log += "[" + time + "] THE TASK IS NOW RESOLVED\n";
        this.changeLog.put(time, "[THE TASK IS NOW RESOLVED]");
        this.status = Status.RESOLVED;
    }

    public String getLog() {
        return this.log + "===============";
    }

    private void renewDB(long time) {
        if(this.lastChangeTime != -1)
            new TasksManagement().removeLastChangeTime(this.lastChangeTime);
        new TasksManagement().setLastChangeTime(time, this.taskId);
        this.lastChangeTime = time;
    }
}
