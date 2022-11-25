package topic2.entity.people;

import static topic2.ui.Factory.GREGORIANCALENDAR;
import static topic2.ui.Factory.SIMPLEDATEFORMAT;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public abstract class Person {

    protected String name;
    protected String gender; // 1 - Nam , 0 - Nữ
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
        } else if (GREGORIANCALENDAR.get(Calendar.MONTH) == birthDay.get(Calendar.MONTH)) {
            if (GREGORIANCALENDAR.get(Calendar.DAY_OF_MONTH) < birthDay.get(Calendar.DAY_OF_MONTH)) {
                age--;
            }
        }
        return age;
    }

    public abstract void setInfo() throws ParseException;

    public abstract void showInfo();

    public String toString() {
        String gender = this.gender.equals("1") ? "Nam" : "Nữ";
        return String.format("\n- Họ tên: %s\n- Giới tính: %s\n- Ngày sinh: %s", name, gender, SIMPLEDATEFORMAT.format(dob));
    }
}
