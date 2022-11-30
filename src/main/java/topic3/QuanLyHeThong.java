package topic3;

import java.util.ArrayList;
import java.util.List;

public class QuanLyHeThong {

    private List<LuyenTap> dsLuyenTap = new ArrayList<>();

    public List<LuyenTap> getDsLuyenTap() {
        return dsLuyenTap;
    }

    public void setDsLuyenTap(List<LuyenTap> dsLuyenTap) {
        this.dsLuyenTap = dsLuyenTap;
    }

    public void themLuyenTap(LuyenTap luyenTap) {
        this.dsLuyenTap.add(luyenTap);
    }

    public void xoaLuyenTap(LuyenTap luyenTap) {
        this.dsLuyenTap.remove(luyenTap);
    }

    public void thongKe(LuyenTap luyenTap) {
        System.out.println("\n== THONG TIN THANH VIEN ==");
        System.out.println(luyenTap.getThanhVien());
        System.out.println(luyenTap.getThanhTich().formula());
        System.out.println(luyenTap.getThanhTich());
    }

    public LuyenTap traCuu(ThanhVien tv) {
        return this.dsLuyenTap.stream().filter(luyenTap -> luyenTap.getThanhVien().equals(tv)).findFirst().orElse(null);
    }
}
