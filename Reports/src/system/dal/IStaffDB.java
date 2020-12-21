package system.dal;

import system.staff.Employee;
import system.staff.TeamLead;

import java.util.Collection;

public interface IStaffDB {
    Employee getEmployeeById(int id) throws Exception;

    TeamLead getTeamLead() throws Exception;

    Collection<Employee> getEmployees();

    Collection<Integer> getEmployeesIds();

    void addEmployee(int employeeId, Employee employee);

    void setTeamLead(TeamLead teamLead);
}
