package project_management.behavior;

import project_management.entity.Employee;
import project_management.entity.Project;

public class JoinProject {

    private Project project;
    private Employee employee;

    public JoinProject(Project project, Employee employee) {
        this.project = project;
        this.employee = employee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
