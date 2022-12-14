package topic3.quanly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import topic3.user.ThanhVien;

public class QuanLyThanhVien {

    private List<ThanhVien> dsThanhVien = new ArrayList<>();

    public List<ThanhVien> getDsThanhVien() {
        return dsThanhVien;
    }

    public void setDsThanhVien(List<ThanhVien> dsThanhVien) {
        this.dsThanhVien = dsThanhVien;
    }

    public void themTV(ThanhVien... dsThanhVien) {
        this.dsThanhVien.addAll(Arrays.asList(dsThanhVien));
    }

    public void xoaTV(ThanhVien... dsThanhVien) {
        this.dsThanhVien.removeAll(Arrays.asList(dsThanhVien));
    }

    public void thongKe(ThanhVien thanhVien) {
        thanhVien.hienThiThongTin();
        thanhVien.getThanhTich().hienThiThongTin();
    }

    public void hienThiDS() {
        this.dsThanhVien.forEach(thanhVien -> thanhVien.hienThiThongTin());
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
