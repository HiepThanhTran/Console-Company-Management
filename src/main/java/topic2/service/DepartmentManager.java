package topic2.service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import topic2.behavior.JoinDepartment;
import topic2.entity.other.Department;
import topic2.entity.people.Employee;
import topic2.ui.Factory;

public class DepartmentManager {

    private List<Department> departmentList = new LinkedList<>();
    private List<JoinDepartment> joinDepartments = new LinkedList<>();

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public List<JoinDepartment> getJoinDepartments() {
        return joinDepartments;
    }

    public void setJoinDepartments(List<JoinDepartment> joinDepartments) {
        this.joinDepartments = joinDepartments;
    }

    public void add(Department department) {
        this.departmentList.add(department);
    }

    public void add(JoinDepartment joinDepartment) {
        this.joinDepartments.add(joinDepartment);
    }

    public void remove(Department department) {
        this.departmentList.remove(department);
    }

    public void remove(JoinDepartment joinDepartment) {
        this.joinDepartments.remove(joinDepartment);
    }

    /**
     * Xóa danh sách tham gia phòng ban
     *
     * @param employee Nhân viên
     */
    public void removeAll(Employee employee) {
        this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getEmployee().equals(employee))
            .forEach(joinDepartment -> this.remove(joinDepartment));
    }

    /**
     * Xóa danh sách tham gia phòng ban
     *
     * @param department Phòng ban
     */
    public void removeAll(Department department) {
        this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getDepartment().equals(department))
            .forEach(joinDepartment -> this.remove(joinDepartment));
    }

    /**
     * Hiển thị danh sách phòng ban
     */
    public void showList() {
        this.departmentList.forEach(department -> {
            Factory.printLine(120, "~");
            department.showInfo();
        });
    }

    /**
     * Lấy danh sách phòng ban của nhân viên 'employee'
     *
     * @param employee Nhân viên
     * @return Danh sách phòng ban của nhân viên 'employee'
     */
    public List<Department> getList(Employee employee) {
        return this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getEmployee().equals(employee))
            .map(joinDepartment -> joinDepartment.getDepartment()).collect(Collectors.toList());
    }

    /**
     * Tìm kiếm phòng ban theo tên phòng ban
     *
     * @param name Tên phòng ban
     * @return
     */
    public Department search(String name) {
        return this.departmentList.stream().filter(department -> department.getName().equals(name)).findFirst()
            .orElseThrow(() -> new NullPointerException("\n== Không tìm thấy phòng ban ==\n"));
    }
}
