package system.dal;

import system.staff.Employee;
import system.staff.TeamLead;

import java.util.Collection;
import java.util.HashMap;

public class StaffDB implements IStaffDB {
    private HashMap<Integer, Employee> employees = new HashMap<>();
    private TeamLead teamLead;

    @Override
    public Employee getEmployeeById(int id) throws Exception {
        if(!employees.containsKey(id))
            throw new Exception("Employee with given ID doesn't exist");
        return employees.get(id);
    }

    @Override
    public TeamLead getTeamLead() throws Exception {
        return teamLead;
    }

    @Override
    public Collection<Employee> getEmployees() {
        return employees.values();
    }

    @Override
    public Collection<Integer> getEmployeesIds() {
        return employees.keySet();
    }

    @Override
    public void addEmployee(int employeeId, Employee employee) {
        employees.put(employeeId ,employee);
    }

    @Override
    public void setTeamLead(TeamLead teamLead) {
        this.teamLead = teamLead;
    }
}
