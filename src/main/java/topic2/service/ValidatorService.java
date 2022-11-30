package topic2.service;

import static topic2.ui.Factory.GREGORIANCALENDAR;

import java.util.Date;
import topic2.exception.BirthDayException;
import topic2.exception.EmailException;
import topic2.exception.FullNameException;
import topic2.exception.GenderException;

public class ValidatorService {

    private ValidatorService() {
    }

    /**
     * Kiểm tra tính hợp lệ của ngày sinh
     *
     * @param birthDay Ngày sinh
     * @throws BirthDayException
     */
    public static void checkBirthDay(Date birthDay) throws BirthDayException {
        if (birthDay.after(GREGORIANCALENDAR.getTime())) {
            throw new BirthDayException("\n** NGÀY SINH KHÔNG HỢP LỆ **\n");
        }
    }

    /**
     * Kiểm tra tính hợp lệ của Email
     *
     * @param email Email
     * @throws EmailException
     */
    public static void checkEmail(String email) throws EmailException {
        if (!email.matches("^[\\w]+[\\w\\-_\\.]+@(\\w+\\.)+\\w+$")) {
            throw new EmailException("\n** EMAIL KHÔNG HỢP LỆ **\n");
        }
    }

    /**
     * Kiểm tra tính hợp lệ của Họ tên
     *
     * @param fullName Họ tên
     * @throws FullNameException
     */
    public static void checkFullName(String fullName) throws FullNameException {
        if (!fullName.matches("^([\\p{L}]{1}[\\p{L}]+\\s{1})+[\\p{L}]{1}[\\p{L}]+$")) {
            throw new FullNameException("\n** HỌ TÊN KHÔNG HỢP LỆ **\n");
        }
    }

    /**
     * Kiểm tra tính hợp lệ của Giới tính
     *
     * @param gender
     * @throws GenderException
     */
    public static void checkGender(String gender) throws GenderException {
        if (!gender.matches("[0|1]")) {
            throw new GenderException("\n** GIỚI TÍNH KHÔNG HỢP LỆ **\n");
        }
    }
}
