package topic2.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import topic2.behavior.JoinDepartment;
import topic2.entity.Department;
import topic2.entity.Employee;

public class JoinDepartmentManager {

    private List<JoinDepartment> joinDepartments = new ArrayList<>();

    public List<JoinDepartment> getJoinDepartments() {
        return joinDepartments;
    }

    public void setJoinDepartments(List<JoinDepartment> joinDepartments) {
        this.joinDepartments = joinDepartments;
    }

    public void add(JoinDepartment joinDepartment) {
        this.joinDepartments.add(joinDepartment);
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
        List<JoinDepartment> temp = this.joinDepartments.stream()
            .filter(joinDepartment -> joinDepartment.getEmployee().equals(employee))
            .collect(Collectors.toList());
        temp.forEach(joinDepartment -> this.remove(joinDepartment));
    }

    /**
     * Xóa danh sách tham gia phòng ban
     *
     * @param department Phòng ban
     */
    public void removeAll(Department department) {
        List<JoinDepartment> temp = this.joinDepartments.stream()
            .filter(joinDepartment -> joinDepartment.getDepartment().equals(department))
            .collect(Collectors.toList());
        temp.forEach(joinDepartment -> this.remove(joinDepartment));
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

    public JoinDepartment search(Employee employee) {
        return this.joinDepartments.stream().filter(joinDepartment -> joinDepartment.getEmployee().equals(employee)).findFirst()
            .orElseThrow(() -> new NullPointerException("\n== Không tìm thấy nhân viên ==\n"));
    }
}
