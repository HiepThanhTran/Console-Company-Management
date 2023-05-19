package project_management.service;

import static project_management.ui.Factory.DECIMALFORMAT;

import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;
import project_management.entity.Employee;
import project_management.entity.Manager;
import project_management.ui.Factory;

public class EmployeeManager {

    private List<Employee> employeeList = new ArrayList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public void add(Employee employee) {
        this.employeeList.add(employee);
    }

    public void remove(Employee employee) {
        this.employeeList.remove(employee);
    }

    /**
     * Thăng chức nhân viên
     *
     * @param employee Nhân viên
     */
    public Employee promoted(Employee employee) {
        if (!(employee instanceof Manager)) {
            Employee.decreaseEmployeeAmount(1);
            Manager manager = new Manager(employee);
            this.employeeList.remove(employee);
            this.employeeList.add(manager);
            manager.setId("M-" + employee.getId().substring(2));
            return manager;
        }
        return employee;
    }

    /**
     * Tính tiền lương cho các nhân viên
     */
    public void calculateSalaryOfList() {
        Factory.employeeMenuHeader();
        this.employeeList.forEach(employee -> {
            employee.setSalary(employee.calculateSalary());
            System.out.print(employee);
            System.out.printf("\t=> Lương: %s\n", DECIMALFORMAT.format(employee.getSalary()));
            Factory.printLine(140, "-");
        });
    }

    /**
     * Hiển thị danh sách nhân viên
     */
    public void showList() {
        Factory.employeeMenuHeader();
        this.employeeList.forEach(employee -> {
            System.out.println(employee);
            Factory.printLine(140, "-");
        });
    }

    /**
     * Tìm kiếm nhân viên theo mã nhân viên
     *
     * @param id
     * @return 1 Nhân viên hợp lệ
     */
    public Employee searchById(String id) {
        return this.employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst()
            .orElseThrow(() -> new NullPointerException("\n\t+----- Không tìm thấy nhân viên -----+"));
    }

    /**
     * Tìm kiếm nhân viên theo tên nhân viên
     *
     * @param name Tên nhân viên
     * @return Danh sách các nhân viên hợp lệ
     */
    public List<Employee> search(String name) {
        return this.employeeList.stream().filter(employee -> employee.getName().equals(name)).collect(Collectors.toList());
    }

    /**
     * Tìm kiếm nhân viên theo ngày sinh
     *
     * @param dob Ngày sinh
     * @return Danh sách các nhân viên hợp lệ
     */
    public List<Employee> search(Date dob) {
        return this.employeeList.stream().filter(employee -> employee.getDob().equals(dob)).collect(Collectors.toList());
    }

    /**
     * Tìm kiếm nhân viên theo độ tuổi cụ thể
     *
     * @param age Tuổi
     * @return Danh sách các nhân viên hợp lệ
     */
    public List<Employee> search(int age) {
        return this.employeeList.stream().filter(employee -> employee.getAge() == age).collect(Collectors.toList());
    }

    /**
     * Tìm kiếm nhân viên theo khoảng độ tuổi từ fromAge -> toAge
     *
     * @param fromAge
     * @param toAge
     * @return Danh sách các nhân viên hợp lệ
     */
    public List<Employee> search(int fromAge, int toAge) {
        if (fromAge > toAge) {
            throw new InputMismatchException("\n\t+----- Khoảng tuổi không hợp lệ -----+");
        }
        return this.employeeList.stream().filter(employee -> employee.getAge() >= fromAge && employee.getAge() <= toAge)
            .collect(Collectors.toList());
    }
}