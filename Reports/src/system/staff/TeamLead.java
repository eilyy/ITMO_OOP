package system.staff;

import system.databases.ReportsDB;
import system.databases.TimeDB;
import system.reports.Sprint;

public class TeamLead extends AbstractEmployee {
    public TeamLead(String name) {
        this.employeeId = 0;
        this.name = name;
        this.level = 0;
    }

    public boolean isSprintReportDone(int year, int month, int day, int hour, int min) throws Exception {
        for(Sprint i : ReportsDB.getInstance().getSprints()) {
            if(i.getFinishTime() == TimeDB.getInstance().makeTimeStamp(year, month, day, hour, min))
                return i.isActive();
        }
        throw new Exception("There is no such sprint report with given finish time");
    }
}
