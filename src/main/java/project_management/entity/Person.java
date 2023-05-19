package project_management.entity;

import static project_management.ui.Factory.GREGORIANCALENDAR;
import static project_management.ui.Factory.SCANNER;
import static project_management.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Person {

    protected String name;
    protected String gender;
    protected Date dob;

    public Person() {
    }

    public Person(String name, String gender, Date dob) {
        this.name = name;
        this.gender = gender;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public int getAge() {
        GregorianCalendar birthDay = new GregorianCalendar();
        birthDay.setTime(this.dob);
        int age = GREGORIANCALENDAR.get(Calendar.YEAR) - birthDay.get(Calendar.YEAR);
        if (GREGORIANCALENDAR.get(Calendar.MONTH) < birthDay.get(Calendar.MONTH)) {
            age--;
        } else if (GREGORIANCALENDAR.get(Calendar.MONTH) == birthDay.get(Calendar.MONTH)
            && GREGORIANCALENDAR.get(Calendar.DAY_OF_MONTH) < birthDay.get(Calendar.DAY_OF_MONTH)) {
            age--;
        }
        return age;
    }

    public void setInfo() throws ParseException {
        System.out.print("- Họ tên: ");
        this.name = SCANNER.nextLine();
        System.out.print("- Giới tính: ");
        this.gender = SCANNER.nextLine();
        System.out.print("- Ngày sinh: ");
        this.dob = SIMPLEDATEFORMAT.parse(SCANNER.nextLine());
    }

    public String toString() {
        return String.format("| %-30s | %-9s | %-10s |", name, gender, SIMPLEDATEFORMAT.format(dob));
    }
}