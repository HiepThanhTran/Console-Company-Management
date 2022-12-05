package topic2.service;

import static topic2.ui.Factory.MAX_EMPLOYEE;
import static topic2.ui.Factory.MAX_PROJECT;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import topic2.behavior.JoinProject;
import topic2.entity.Employee;
import topic2.entity.Project;
import topic2.exception.AmountException;
import topic2.ui.Factory;

public class ProjectManager {

    private List<Project> projectList = new ArrayList<>();
    private List<JoinProject> joinProjects = new ArrayList<>();

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public List<JoinProject> getJoinProjects() {
        return joinProjects;
    }

    public void setJoinProjects(List<JoinProject> joinProjects) {
        this.joinProjects = joinProjects;
    }

    public void add(Project project) {
        this.projectList.add(project);
    }

    public void add(JoinProject joinProject) throws AmountException {
        if (this.getAmount(joinProject.getEmployee()) >= MAX_PROJECT) {
            throw new AmountException("\n== Nhân viên có thể tham gia tối đa 3 dự án ==\n");
        }
        if (this.getAmount(joinProject.getProject()) >= MAX_EMPLOYEE) {
            throw new AmountException("\n== Dự án có thể có tối đa 10 thành viên ==\n");
        }
        this.joinProjects.add(joinProject);
    }

    public void remove(Project project) {
        this.projectList.remove(project);
    }

    public void remove(JoinProject joinProject) {
        this.joinProjects.remove(joinProject);
    }

    /**
     * Xóa danh sách tham gia dự án
     *
     * @param project Dự án
     */
    public void removeAll(Project project) { // size = 9
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
     * Kiểm tra dự án này có phải dự án nhân viên đang thực hiện hay không<br> Kiểm tra nhân viên có thuộc dự án này hay không
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
     * Sắp xếp danh sách dự án theo kinh phí đầu tư
     */
    public void sort() {
        Collections.sort(projectList);
//        this.projectList.sort((p1, p2) -> p1.getCost() > p2.getCost() ? 1 : (p1.getCost() < p2.getCost() ? -1 : 0));
    }

    /**
     * Hiển thị danh sách dự án
     */
    public void showList() {
        this.projectList.forEach(project -> {
            Factory.printLine(120, "~");
            project.showInfo();
        });
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

    /**
     * Tìm kiếm dự án theo tên dự án hoặc mã dự án
     *
     * @param key Tên dự án hoặc mã dự án
     * @return
     */
    public Project search(String key) {
        return this.projectList.stream()
            .filter(project -> project.getProjectName().equals(key) || project.getProjectId().equals(key))
            .findFirst().orElseThrow(() -> new NullPointerException("\n== Không tìm thấy dự án ==\n"));
    }

    /**
     * Tìm kiếm dự án theo ngày bắt đầu dự án
     *
     * @param startDate Ngày bắt đầu dự án
     * @return
     */
    public List<Project> search(Date startDate) {
        return this.projectList.stream().filter(project -> project.getStartDate().equals(startDate)).collect(Collectors.toList());
    }
}
