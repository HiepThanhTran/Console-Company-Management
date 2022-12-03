package topic2.behavior;

import java.util.UUID;
import topic2.entity.Employee;
import topic2.entity.Relative;

public class ProvideInsurance {

    private String insNumber;
    private Relative relative;
    private Employee employee;

    {
        insNumber = UUID.randomUUID().toString();
    }

    public ProvideInsurance(Relative relative, Employee employee) {
        this.relative = relative;
        this.employee = employee;
    }

    public ProvideInsurance(String insNumber, Relative relative, Employee employee) {
        this.insNumber = insNumber;
        this.relative = relative;
        this.employee = employee;
    }

    public String getInsNumber() {
        return insNumber;
    }

    public void setInsNumber(String insNumber) {
        this.insNumber = insNumber;
    }

    public Relative getRelative() {
        return relative;
    }

    public void setRelative(Relative relative) {
        this.relative = relative;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void showRelativeInfo() {
        relative.showInfo();
    }

    public void showEmployeeInfo() {
        employee.showInfo();
    }
}
