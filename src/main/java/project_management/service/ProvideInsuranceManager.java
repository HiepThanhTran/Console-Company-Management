package project_management.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import project_management.behavior.ProvideInsurance;
import project_management.entity.Employee;

public class ProvideInsuranceManager {

    private List<ProvideInsurance> relativeList = new ArrayList<>();

    public List<ProvideInsurance> getRelativeList() {
        return relativeList;
    }

    public void setRelativeList(List<ProvideInsurance> relativeList) {
        this.relativeList = relativeList;
    }

    public void add(ProvideInsurance relative) {
        this.relativeList.add(relative);
    }

    public void remove(ProvideInsurance relative) {
        this.relativeList.remove(relative);
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
}
