package topic2.service;

import static topic2.ui.Factory.DECIMALFORMAT;

import java.util.Date;
import java.util.InputMismatchException;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import topic2.behavior.ProvideInsurance;
import topic2.entity.Employee;
import topic2.entity.Manager;
import topic2.ui.Factory;

public class EmployeeManager {

    private List<Employee> employeeList = new LinkedList<>();
    private List<ProvideInsurance> relativeList = new LinkedList<>();

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public List<ProvideInsurance> getRelativeList() {
        return relativeList;
    }

    public void setRelativeList(List<ProvideInsurance> relativeList) {
        this.relativeList = relativeList;
    }

    public void add(Employee employee) {
        this.employeeList.add(employee);
    }

    public void add(ProvideInsurance relative) {
        this.relativeList.add(relative);
    }

    public void remove(Employee employee) {
        this.employeeList.remove(employee);
    }

    public void remove(ProvideInsurance relative) {
        this.relativeList.remove(relative);
    }

    /**
     * Thăng chức nhân viên
     *
     * @param employee Nhân viên
     */
    public Employee promote(Employee employee) {
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
        this.employeeList.forEach(employee -> {
            Factory.printLine(120, "~");
            employee.setSalary(employee.calculateSalary());
            employee.showInfo();
            System.out.printf("\n- Lương: %s\n", DECIMALFORMAT.format(employee.getSalary()));
        });
    }

    /**
     * Hiển thị danh sách nhân viên
     */
    public void showList() {
        this.employeeList.forEach(employee -> {
            Factory.printLine(120, "~");
            employee.showInfo();
        });
    }

    /**
     * Lấy danh sách nhân thân của nhân viên 'employee'
     *
     * @param employee Nhân viên
     * @return Danh sách nhân thân của nhân viên 'employee'
     */
    public List<ProvideInsurance> getList(Employee employee) {
        return this.relativeList.stream().filter(provideInsurance -> provideInsurance.getEmployee().equals(employee))
            .collect(Collectors.toList());
    }

    /**
     * Tìm kiếm nhân viên theo mã nhân viên
     *
     * @param id
     * @return 1 Nhân viên hợp lệ
     */
    public Employee searchById(String id) {
        return this.employeeList.stream().filter(employee -> employee.getId().equals(id)).findFirst()
            .orElseThrow(() -> new NullPointerException("\n== Không tìm thấy nhân viên ==\n"));
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
            throw new InputMismatchException("\n== Khoảng tuổi không hợp lệ ==\n");
        }
        return this.employeeList.stream().filter(employee -> employee.getAge() >= fromAge && employee.getAge() <= toAge)
            .collect(Collectors.toList());
    }
}
