package topic3.quanly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import topic3.user.LuyenTap;
import topic3.user.ThanhVien;

public class QuanLyHeThongLT {

    private List<LuyenTap> dsLuyenTap = new ArrayList<>();

    public List<LuyenTap> getDsLuyenTap() {
        return dsLuyenTap;
    }

    public void setDsLuyenTap(List<LuyenTap> dsLuyenTap) {
        this.dsLuyenTap = dsLuyenTap;
    }

    public void themLuyenTap(LuyenTap... dsLuyenTap) {
        this.dsLuyenTap.addAll(Arrays.asList(dsLuyenTap));
    }

    public void xoaLuyenTap(LuyenTap... dsLuyenTap) {
        this.dsLuyenTap.removeAll(Arrays.asList(dsLuyenTap));
    }

    public void thongKe(LuyenTap luyenTap) {
        luyenTap.getThanhVien().hienThiThongTin();
        luyenTap.getThanhTich().hienThiThongTin();
    }

    public LuyenTap traCuu(ThanhVien tv) {
        return this.dsLuyenTap.stream().filter(luyenTap -> luyenTap.getThanhVien().equals(tv)).findFirst().orElse(null);
    }
}
