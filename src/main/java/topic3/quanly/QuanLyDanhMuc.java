package topic3.quanly;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import topic3.cauhoi.DanhMuc;

public class QuanLyDanhMuc {

    private final List<DanhMuc> dsDanhMuc = new ArrayList<>();

    public List<DanhMuc> getDsDanhMuc() {
        return dsDanhMuc;
    }

    public void themDanhMuc(DanhMuc... dsDanhMuc) {
        this.dsDanhMuc.addAll(Arrays.asList(dsDanhMuc));
    }

    public void xoaDanhMuc(DanhMuc... dsDanhMuc) {
        this.dsDanhMuc.removeAll(Arrays.asList(dsDanhMuc));
    }

    public void hienThiDS() {
        this.dsDanhMuc.forEach(danhMuc -> danhMuc.hienThiThongTin());
    }

    public DanhMuc traCuu(String tenDanhMuc) {
        return this.dsDanhMuc.stream().filter(danhMuc -> danhMuc.getTenDanhMuc().equals(tenDanhMuc)).findFirst().orElse(null);
    }
}
