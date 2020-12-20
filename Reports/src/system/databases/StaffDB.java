package system.databases;

import system.staff.Employee;
import system.staff.TeamLead;

import java.util.Collection;
import java.util.HashMap;

public class StaffDB {
    private static HashMap<Integer, Employee> employees = new HashMap<>();
    private static TeamLead teamLead;

    public Employee getEmployeeById(int id) throws Exception {
        if(!employees.containsKey(id))
            throw new Exception("Employee with given ID doesn't exist");
        return employees.get(id);
    }

    public TeamLead getTeamLead() {
        return teamLead;
    }

    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    public Collection<Integer> getEmployeesIds() {
        return employees.keySet();
    }

    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId ,employee);
    }

    public void setTeamLead(TeamLead teamLead) {
        StaffDB.teamLead = teamLead;
    }
}
