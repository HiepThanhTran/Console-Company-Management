package topic2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import topic2.behavior.JoinDepartment;
import topic2.entity.other.Department;
import topic2.entity.people.Employee;
import topic2.ui.Factory;

public final class DepartmentManager {

    private static DepartmentManager INSTANCE;
    private List<Department> departmentList = new ArrayList<>();
    private List<JoinDepartment> joinDepartments = new ArrayList<>();

    private DepartmentManager() {
    }

    public static DepartmentManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new DepartmentManager();
        }
        return INSTANCE;
    }

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
     * Hiển thị danh sách phòng ban
     */
    public void showList() {
        this.departmentList.forEach(department -> {
            Factory.printLine(120, "~");
            department.showInfo();
        });
    }

    /**
     * Lấy danh sách nhân viên của phòng ban 'department'
     *
     * @param department Phòng ban
     * @return Danh sách nhân viên của phòng ban 'department'
     */
    public List<JoinDepartment> getList(Department department) {
        return this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getDepartment().equals(department))
            .collect(Collectors.toList());
    }

    /**
     * Lấy danh sách phòng ban của nhân viên 'employee'
     *
     * @param employee Nhân viên
     * @return Danh sách phòng ban của nhân viên 'employee'
     */
    public List<JoinDepartment> getList(Employee employee) {
        return this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getEmployee().equals(employee))
            .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm phòng ban theo tên phòng ban
     *
     * @param name Tên phòng ban
     * @return
     */
    public Department search(String name) {
        return this.departmentList.stream().filter(department -> department.getName().equals(name)).findFirst().orElse(null);
    }
}
