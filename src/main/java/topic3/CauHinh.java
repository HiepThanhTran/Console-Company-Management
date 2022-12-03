package topic3;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;
import java.util.Scanner;
import topic3.quanly.QuanLyCauHoi;
import topic3.quanly.QuanLyDanhMuc;
import topic3.quanly.QuanLyHeThongLT;
import topic3.quanly.QuanLyThanhVien;

public class CauHinh {

    public static final QuanLyCauHoi quanLyCauHoi = new QuanLyCauHoi();
    public static final QuanLyDanhMuc quanLyDanhMuc = new QuanLyDanhMuc();
    public static final QuanLyThanhVien quanLyThanhVien = new QuanLyThanhVien();
    public static final QuanLyHeThongLT quanLyHeThongLT = new QuanLyHeThongLT();

    public static final SimpleDateFormat f = new SimpleDateFormat("dd/MM/yyyy");
    public static final GregorianCalendar c = new GregorianCalendar();
    public static final Scanner sc = new Scanner(System.in);
}
