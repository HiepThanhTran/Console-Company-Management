package project_management.service;

import static project_management.ui.Factory.MAX_EMPLOYEE;
import static project_management.ui.Factory.MAX_PROJECT;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import project_management.behavior.JoinProject;
import project_management.entity.Employee;
import project_management.entity.Project;
import project_management.exception.AmountException;

public class JoinProjectManager {

    private List<JoinProject> joinProjects = new ArrayList<>();

    public List<JoinProject> getJoinProjects() {
        return joinProjects;
    }

    public void setJoinProjects(List<JoinProject> joinProjects) {
        this.joinProjects = joinProjects;
    }

    public void add(JoinProject joinProject) throws AmountException {
        if (this.getAmount(joinProject.getEmployee()) >= MAX_PROJECT) {
            throw new AmountException("\n\t+----- Nhân viên chỉ được tham gia tối đa 3 dự án -----+");
        }
        if (this.getAmount(joinProject.getProject()) >= MAX_EMPLOYEE) {
            throw new AmountException("\n\t+----- Dự án chỉ được có tối đa 10 thành viên -----+");
        }
        this.joinProjects.add(joinProject);
    }

    public void remove(JoinProject joinProject) {
        this.joinProjects.remove(joinProject);
    }

    /**
     * Xóa danh sách tham gia dự án
     *
     * @param project Dự án
     */
    public void removeAll(Project project) {
        List<JoinProject> temp = this.joinProjects.stream().filter(joinProject -> joinProject.getProject().equals(project))
            .collect(Collectors.toList());
        temp.forEach(joinProject -> this.remove(joinProject));
    }

    /**
     * Xóa danh sách tham gia dự án
     *
     * @param employee Nhân viên
     */
    public void removeAll(Employee employee) {
        List<JoinProject> temp = this.joinProjects.stream().filter(joinProject -> joinProject.getEmployee().equals(employee))
            .collect(Collectors.toList());
        temp.forEach(joinProject -> this.remove(joinProject));
    }

    /**
     * Kiểm tra nhân viên có thuộc dự án này hay không
     *
     * @param project  Dự án
     * @param employee Nhân viên
     * @return
     */
    public boolean check(Project project, Employee employee) {
        return this.joinProjects.stream()
            .anyMatch(joinProject -> joinProject.getProject().equals(project) && joinProject.getEmployee().equals(employee));
    }

    /**
     * @param project
     * @return Số lượng nhân viên của dự án
     */
    public long getAmount(Project project) {
        return this.joinProjects.stream().filter(joinProject -> joinProject.getProject().equals(project)).count();
    }

    /**
     * @param employee
     * @return Số lượng dự án nhân viên đã tham gia
     */
    public long getAmount(Employee employee) {
        return this.joinProjects.stream().filter(joinProject -> joinProject.getEmployee().equals(employee)).count();
    }

    /**
     * Lấy danh sách nhân viên của dự án 'project'
     *
     * @param project Dự án
     * @return Danh sách nhân viên của dự án 'project'
     */
    public List<Employee> getList(Project project) {
        return this.joinProjects.stream().filter(joinProject -> joinProject.getProject().equals(project))
            .map(joinProject -> joinProject.getEmployee()).collect(Collectors.toList());
    }

    /**
     * Lấy danh sách dự án của nhân viên 'employee' đang thực hiện
     *
     * @param employee Nhân viên
     * @return Danh sánh dự án của nhân viên 'employee' đang thực hiện
     */
    public List<Project> getList(Employee employee) {
        return this.joinProjects.stream().filter(joinProject -> joinProject.getEmployee().equals(employee))
            .map(joinProject -> joinProject.getProject()).collect(Collectors.toList());
    }
}