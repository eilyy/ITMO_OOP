import UI.*;
import system.databases.TimeDB;

public class Main {
    public static void main(String[] args) {
        try {
            var sui = new StaffUI();
            var tui = new TasksUI();
            var tmui = new TimeUI();
            var rui = new ReportsUI();

            tmui.setDateNow(2020, 12, 20);

            System.out.println(TimeDB.getInstance().getDate().getTime());

            sui.newTeamLead("Ilya");
            sui.newEmployee("Misha", 0);
            sui.newEmployee("Vova", 0);
            sui.newEmployee("Denis", 0);
            sui.newEmployee("Maxim", 1);
            sui.newEmployee("Nastya", 1);
            sui.newEmployee("Alex", 2);

            var tlui = new TeamLeadUI();

            rui.startSprint(12, 30);

            rui.newDailyReport();

            tui.newTask("lab6", "reports lab oop", tmui.getTime(12, 50));
            tui.assignTaskDoer(1, 0, tmui.getTime(12, 53));
            tlui.commitTask(1, "initial commit", tmui.getTime(13, 29));

            tui.newTask("lab5", "bank lab oop", tmui.getTime(13, 32));
            tui.assignTaskDoer(2, 1, tmui.getTime(13, 32));

            var misha = new EmployeeUI(1);
            misha.finishTask(2, tmui.getTime(13, 35));

            System.out.println(tui.getTasksByDoer(0));

            System.out.println(tui.getTaskById(1).getStatus());
            System.out.println(tui.getTaskById(1).getLog());

            System.out.println(rui.getSprintTasks(0));
            System.out.println(rui.getSprintTasks(0));

            tlui.finishTask(1, tmui.getTime(13, 40));
            System.out.println(tui.getTaskById(1).getStatus());
            System.out.println(tui.getTaskById(1).getLog());

            System.out.println(sui.getHierarchy());

            sui.changeManagerForEmployee(6, 3);

            System.out.println(sui.getHierarchy());

            System.out.println(TimeDB.getInstance().getDate().getTime());

            tlui.fillDailyReport("123123");

            tlui.fillSprintReport("6479");

            System.out.println(TimeDB.getInstance().getDate().getTime());

            tmui.nextDay();

            System.out.println(TimeDB.getInstance().getDate().getTime());

            System.out.println(rui.getDailyReportBody(2020, 12, 20));
            System.out.println(rui.getCurrentSprintReportBody());

            System.out.println(rui.getSprintTasks(0));
            System.out.println(rui.getTasksOfUsersSubordinates(0));

            rui.finishSprint(13, 50);

            System.out.println(rui.getSprintByFinishTime(2020, 12, 21, 13, 50).getReport().getBody());
        } catch(Exception e) {
            System.err.println(e);
        }
    }
}