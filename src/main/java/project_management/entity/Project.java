package project_management.entity;

import static project_management.ui.Factory.DECIMALFORMAT;
import static project_management.ui.Factory.MIN_EMPLOYEE;
import static project_management.ui.Factory.SCANNER;
import static project_management.ui.Factory.SIMPLEDATEFORMAT;
import static project_management.ui.Factory.joinProjectManger;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class Project implements Comparable<Project> {

    private static int PROJECT_AMOUNT = 0;
    private String projectId;
    private String projectName;
    private Date startDate;
    private Date endDate;
    private double cost;
    private Employee manager;

    {
        PROJECT_AMOUNT++;
    }

    public Project() {
    }

    public Project(Employee manager) {
        this.manager = manager;
    }

    public Project(String projectId, String projectName, Date startDate, Date endDate, double cost, Employee manager) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.cost = cost;
        this.manager = manager;
    }

    public static int getProjectAmount() {
        return PROJECT_AMOUNT;
    }

    public static void decreaseProjectAmount(int x) {
        PROJECT_AMOUNT -= x;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public void setInfo() throws ParseException {
        System.out.print("- Mã dự án: ");
        this.projectId = SCANNER.nextLine();
        System.out.print("- Tên dự án: ");
        this.projectName = SCANNER.nextLine();
        System.out.print("- Ngày bắt đầu dự án: ");
        this.startDate = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
        System.out.print("- Ngày kết thúc dự kiến: ");
        this.endDate = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
        System.out.print("- Kinh phí đầu tư: ");
        this.cost = Double.parseDouble(SCANNER.nextLine());
    }

    @Override
    public int compareTo(Project project) {
        return (int) (this.cost - project.getCost());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Project project = (Project) o;
        return projectId.equals(project.projectId) && projectName.equals(project.projectName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(projectId, projectName);
    }

    @Override
    public String toString() {
        String flag = (joinProjectManger.getAmount(this) < MIN_EMPLOYEE) ? "❌" : "✔";
        return String.format("| %-12s | %-30s | %-12s | %-13s | %-18s | %-40s | %-9s |", projectId, projectName,
            SIMPLEDATEFORMAT.format(startDate), SIMPLEDATEFORMAT.format(endDate), DECIMALFORMAT.format(cost),
            manager.getId() + " ~ " + manager.getName(), flag);
    }
}
