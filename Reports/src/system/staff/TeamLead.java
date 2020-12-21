package system.staff;

import system.IReportsManagement;
import system.IStaffManagement;
import system.ReportsManagement;
import system.dal.*;
import system.TimeHelper;
import system.reports.Sprint;
import system.tasks.Task;

import java.util.Calendar;
import java.util.Collection;
import java.util.Vector;

public class TeamLead extends AbstractEmployee {

    public TeamLead(String name, IStaffDB staffDB, ITasksDB tasksDB, IReportsDB reportsDB, IReportsManagement reportsManagement, IStaffManagement staffManagement) {
        this.employeeId = 0;
        this.name = name;
        this.level = 0;

        this.sdb = staffDB;
        this.tdb = tasksDB;
        this.rdb = reportsDB;
        this.rm = reportsManagement;
        this.sm = staffManagement;
    }

    @Override
    public void addSubordinate(int subordinateId) throws Exception {
        if(subordinateId == 0)
            throw new Exception("Team-lead cannot be subordinate");

        this.subordinates.put(subordinateId, sdb.getEmployeeById(subordinateId));
        sdb.getEmployeeById(subordinateId).changeManager(this);
    }

    @Override
    public Vector<Integer> getResolvedTasksToday() {
        return rdb.getResolvedTasksToday();
    }

    @Override
    public Collection<Task> getSprintTasks() throws Exception {
        return rm.getSprintTasks(this.employeeId);
    }

    @Override
    public void fillDailyReport(String filling) throws Exception {
        rdb.getDailyReportByDay
                (TimeHelper.getInstance().getDate().get(Calendar.YEAR),
                        TimeHelper.getInstance().getDate().get(Calendar.MONTH) + 1,
                        TimeHelper.getInstance().getDate().get(Calendar.DAY_OF_MONTH)).fillBody(this.employeeId, filling);
    }

    @Override
    public void fillSprintReport(String filling) throws Exception {
        rdb.getCurrentSprint().fillSprintReport(this.employeeId, filling);
    }

    @Override
    public Collection<Task> getSubordinatesSprintTasks() throws Exception {
        if(this.subordinates.isEmpty())
            throw new Exception("This employee has no subordinates");
        return rm.getTasksOfUsersSubordinates(this.employeeId);
    }

    @Override
    public void takeTask(int taskId) throws Exception {
        this.myTasks.put(taskId, tdb.getTaskById(taskId));
        this.allTasks.add(tdb.getTaskById(taskId));
    }

    public boolean isSprintReportDone(int year, int month, int day, int hour, int min) throws Exception {
        for(Sprint i : rdb.getSprints()) {
            if(i.getFinishTime() == TimeHelper.getInstance().makeTimeStamp(year, month, day, hour, min))
                return i.isActive();
        }
        throw new Exception("There is no such sprint report with given finish time");
    }
}
