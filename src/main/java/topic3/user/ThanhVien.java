package topic3.user;

import static topic3.CauHinh.c;
import static topic3.CauHinh.f;
import static topic3.CauHinh.sc;

import java.text.ParseException;
import java.util.Date;
import java.util.Objects;

public class ThanhVien {

    private static int dem = 0;
    private final int maThanhVien;
    private Date ngayGiaNhap;
    private String hoTen;
    private String queQuan;
    private String gioiTinh;
    private Date ngaySinh;

    {
        ngayGiaNhap = c.getTime();
        maThanhVien = ++dem;
    }

    public ThanhVien() {
    }

    public ThanhVien(String hoTen, String queQuan, String gioiTinh, Date ngaySinh) {
        this.hoTen = hoTen;
        this.queQuan = queQuan;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
    }

    public static int getDem() {
        return dem;
    }

    public int getMaThanhVien() {
        return maThanhVien;
    }

    public Date getNgayGiaNhap() {
        return ngayGiaNhap;
    }

    public void setNgayGiaNhap(Date ngayGiaNhap) {
        this.ngayGiaNhap = ngayGiaNhap;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public void nhapThongTin() throws ParseException {
        System.out.print("- Ho ten: ");
        this.hoTen = sc.nextLine();
        System.out.print("- Que quan: ");
        this.queQuan = sc.nextLine();
        System.out.print("- Gioi tinh: ");
        this.gioiTinh = sc.nextLine();
        System.out.print("- Ngay sinh: ");
        this.ngaySinh = f.parse(sc.nextLine());
    }

    public void hienThiThongTin() {
        System.out.printf("\n== THONG TIN THANH VIEN %d ==\n", maThanhVien);
//        System.out.println(this);
        System.out.printf("- Ho ten: %s\n", hoTen);
        System.out.printf("- Que quan: %s\n", queQuan);
        System.out.printf("- Gioi tinh: %s\n", gioiTinh);
        System.out.printf("- Ngay sinh: %s\n", f.format(ngaySinh));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ThanhVien thanhVien = (ThanhVien) o;
        return maThanhVien == thanhVien.maThanhVien;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maThanhVien);
    }

    public String toString() {
        return String.format("- Ho ten: %s\n- Que quan: %s\n- Gioi tinh: %s\n- Ngay sinh: %s", hoTen, queQuan, gioiTinh,
            f.format(ngaySinh));
    }
}
