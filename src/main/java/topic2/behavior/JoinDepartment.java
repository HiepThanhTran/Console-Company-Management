package topic2.behavior;

import topic2.entity.Department;
import topic2.entity.Employee;

public class JoinDepartment {

    private Employee employee;
    private Department department;

    public JoinDepartment(Employee employee, Department department) {
        this.employee = employee;
        this.department = department;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
