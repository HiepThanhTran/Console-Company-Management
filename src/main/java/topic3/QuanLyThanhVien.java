package topic3;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class QuanLyThanhVien {

    private List<ThanhVien> dsThanhVien = new ArrayList<>();

    public List<ThanhVien> getDsThanhVien() {
        return dsThanhVien;
    }

    public void setDsThanhVien(List<ThanhVien> dsThanhVien) {
        this.dsThanhVien = dsThanhVien;
    }

    public void themTV(ThanhVien tv) {
        this.dsThanhVien.add(tv);
    }

    public void xoaTV(ThanhVien tv) {
        this.dsThanhVien.remove(tv);
    }

    public void hienThiDS() {
        for (int i = 0; i < this.dsThanhVien.size(); i++) {
            System.out.printf("\n\t\t* Thanh vien thu %d *\n%s\n", i + 1, this.dsThanhVien.get(i));
        }
    }

    /**
     * Tra cứu học viên theo mã học viên
     *
     * @param maThanhVien Mã học viên
     * @return Học viên tương ứng
     */
    public ThanhVien traCuu(int maThanhVien) {
        return this.dsThanhVien.stream().filter(thanhVien -> thanhVien.getMaThanhVien() == maThanhVien).findFirst().orElse(null);
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
