package topic3;

import static topic3.CauHinh.quanLyCauHoi;

import java.util.List;

public class LuyenTap {

    private ThanhTich thanhTich;
    private ThanhVien thanhVien;

    public LuyenTap() {
    }

    public LuyenTap(ThanhTich thanhTich, ThanhVien thanhVien) {
        this.thanhTich = thanhTich;
        this.thanhVien = thanhVien;
    }

    public ThanhTich getThanhTich() {
        return thanhTich;
    }

    public void setThanhTich(ThanhTich thanhTich) {
        this.thanhTich = thanhTich;
    }

    public ThanhVien getThanhVien() {
        return thanhVien;
    }

    public void setThanhVien(ThanhVien thanhVien) {
        this.thanhVien = thanhVien;
    }

    public void luyenTap(String path) throws ClassNotFoundException {
        CauHoi cauHoi = quanLyCauHoi.randomCauHoi(path);
    }

    public void luyenTap(String path, int soLuong) throws ClassNotFoundException {
        List<CauHoi> ds = quanLyCauHoi.randomCauHoi(path, soLuong);
        for (int i = 0; i < ds.size(); i++) {
            System.out.println(ds.get(i));
        }
    }
}
