package project_management.entity;

import static project_management.ui.Factory.MAX_MANAGER_ROOM;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import project_management.exception.AmountException;

public class Manager extends Employee {

    private List<Department> departmentList = new ArrayList<>();

    {
        id = String.format("M-%05d", EMPLOYEE_AMOUNT);
    }

    public Manager() {
    }

    public Manager(Employee employee) {
        super(employee.getName(), employee.getGender(), employee.getDob(), employee.getEmail());
    }

    public Manager(String name, String gender, Date dob, String email) {
        super(name, gender, dob, email);
    }

    public Manager(String name, String gender, Date dob, String id, String email) {
        super(name, gender, dob, id, email);
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public void addDepartment(Department department) throws AmountException {
        if (this.departmentList.size() >= MAX_MANAGER_ROOM) {
            throw new AmountException("\n== Chỉ có thể quản lý tối đa 2 phòng ban ==\n");
        }
        this.departmentList.add(department);
    }

    public void removeDepartment(Department department) {
        this.departmentList.remove(department);
    }

    @Override
    public double getAllowance() {
        return 0;
    }

    @Override
    public double getCoefficient() {
        return Type.MANAGER.getCoefficient();
    }

    @Override
    public String toString() {
        return String.format("%s %-30s |", super.toString(), "");
    }
}