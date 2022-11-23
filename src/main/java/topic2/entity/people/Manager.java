package topic2.entity.people;

import static topic2.ui.Factory.GREGORIANCALENDAR;
import static topic2.ui.Factory.MAX_MANAGER_ROOM;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import topic2.entity.other.Department;
import topic2.exception.AmountException;

public class Manager extends Employee {

    private Date takeOfficeDate;
    private List<Department> managerRooms = new ArrayList<>();

    {
        takeOfficeDate = GREGORIANCALENDAR.getTime();
        id = String.format("M-%05d", EMPLOYEE_AMOUNT);
    }

    public Manager() {
    }

    public Manager(Employee employee) {
        super(employee.getName(), employee.getGender(), employee.getDob(), employee.getEmail());
    }

    public Manager(String name, String gender, Date dob, String email, Date takeOfficeDate) {
        super(name, gender, dob, email);
        this.takeOfficeDate = takeOfficeDate;
    }

    public Manager(String name, String gender, String dob, String email, Date takeOfficeDate) throws ParseException {
        this(name, gender, SIMPLEDATEFORMAT.parse(dob), email, takeOfficeDate);
    }

    public Manager(String name, String gender, Date dob, String id, String email, Date takeOfficeDate) {
        super(name, gender, dob, id, email);
        this.takeOfficeDate = takeOfficeDate;
    }

    public Manager(String name, String gender, String dob, String id, String email, Date takeOfficeDate) throws ParseException {
        this(name, gender, SIMPLEDATEFORMAT.parse(dob), id, email, takeOfficeDate);
    }

    public Date getTakeOfficeDate() {
        return takeOfficeDate;
    }

    public List<Department> getManagerRooms() {
        return managerRooms;
    }

    public void setManagerRooms(List<Department> managerRooms) {
        this.managerRooms = managerRooms;
    }

    public void addManagerRoom(Department department) throws AmountException {
        if (this.managerRooms.size() >= MAX_MANAGER_ROOM) {
            throw new AmountException("\n== Chỉ có thể quản lý tối đa 2 phòng ban ==\n");
        }
        this.managerRooms.add(department);
    }

    public void removeManagerRoom(Department department) {
        this.managerRooms.remove(department);
    }

    public String toString() {
        return String.format("%s\n- Ngày nhậm chức: %s", super.toString(), SIMPLEDATEFORMAT.format(takeOfficeDate));
    }
}
