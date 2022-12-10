package topic2.service;

import java.util.ArrayList;
import java.util.List;
import topic2.entity.Department;
import topic2.ui.Factory;

public class DepartmentManager {

    private List<Department> departmentList = new ArrayList<>();

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void add(Department department) {
        this.departmentList.add(department);
    }

    public void remove(Department department) {
        this.departmentList.remove(department);
    }

    /**
     * Hiển thị danh sách phòng ban
     */
    public void showList() {
        this.departmentList.forEach(department -> {
            department.showInfo();
            Factory.printLine(180, "~");
        });
    }

    /**
     * Tìm kiếm phòng ban theo tên phòng ban
     *
     * @param name Tên phòng ban
     * @return
     */
    public Department search(String name) {
        return this.departmentList.stream().filter(department -> department.getDepartmentName().equals(name)).findFirst()
            .orElseThrow(() -> new NullPointerException("\n== Không tìm thấy phòng ban ==\n"));
    }
}
