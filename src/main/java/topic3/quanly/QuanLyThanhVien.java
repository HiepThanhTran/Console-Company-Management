package topic3.quanly;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import topic3.user.ThanhVien;

public class QuanLyThanhVien {

    private final List<ThanhVien> dsThanhVien = new ArrayList<>();

    public static void hienThiDS(List<ThanhVien> dsThanhVien) {
        for (int i = 0; i < dsThanhVien.size(); i++) {
            System.out.printf("\n\t\t* Thanh vien thu %d *\n%s\n", i + 1, dsThanhVien.get(i));
        }
    }

    public List<ThanhVien> getDsThanhVien() {
        return dsThanhVien;
    }

    public void themTV(ThanhVien tv) {
        this.dsThanhVien.add(tv);
    }

    public void xoaTV(int index) {
        this.dsThanhVien.remove(index);
    }

    public void capNhat(int index) throws ParseException {
        this.dsThanhVien.get(index).nhapThongTin();
    }

    /**
     * Tra cứu học viên theo họ tên, quê quán, hoặc giới tính
     *
     * @param tuKhoa Họ tên, quê quán, hoặc giới tính
     * @return Danh sách các học viên tra cứu được
     */
    public List<ThanhVien> traCuu(String tuKhoa) {
        return this.dsThanhVien.stream().filter(
            thanhVien -> thanhVien.getHoTen().equals(tuKhoa) || thanhVien.getQueQuan().equals(tuKhoa) || thanhVien.getGioiTinh()
                .equals(tuKhoa)).collect(Collectors.toList());
    }

    /**
     * Tra cứu học viên theo ngày sinh
     *
     * @param ngaySinh Ngày sinh
     * @return Danh sách các học viên tra cứu được
     */
    public List<ThanhVien> traCuu(Date ngaySinh) {
        return this.dsThanhVien.stream().filter(thanhVien -> thanhVien.getNgaySinh().equals(ngaySinh))
            .collect(Collectors.toList());
    }
}
