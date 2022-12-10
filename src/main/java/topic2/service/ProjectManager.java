package topic2.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import topic2.entity.Project;
import topic2.ui.Factory;

public class ProjectManager {

    private List<Project> projectList = new ArrayList<>();

    public List<Project> getProjectList() {
        return projectList;
    }

    public void setProjectList(List<Project> projectList) {
        this.projectList = projectList;
    }

    public void add(Project project) {
        this.projectList.add(project);
    }

    public void remove(Project project) {
        this.projectList.remove(project);
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
        Factory.projectMenuHeader();
        this.projectList.forEach(project -> {
            System.out.println(project);
            Factory.printLine(157, "-");
        });
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
