package topic3.quanly;

import java.util.ArrayList;
import java.util.List;
import topic3.cauhoi.DanhMuc;

public class QuanLyDanhMuc {

    private final List<DanhMuc> dsDanhMuc = new ArrayList<>();

    public static void hienThiDS(List<DanhMuc> dsDanhMuc) {
        dsDanhMuc.forEach(danhMuc -> System.out.printf("\n%s\n", danhMuc));
    }

    public List<DanhMuc> getDsDanhMuc() {
        return dsDanhMuc;
    }

    public void themDanhMuc(DanhMuc danhMuc) {
        this.dsDanhMuc.add(danhMuc);
    }

    public void xoaDanhMuc(DanhMuc danhMuc) {
        this.dsDanhMuc.remove(danhMuc);
    }

    public DanhMuc traCuu(String tenDanhMuc) {
        return this.dsDanhMuc.stream().filter(danhMuc -> danhMuc.getTenDanhMuc().equals(tenDanhMuc)).findFirst().orElse(null);
    }
}
