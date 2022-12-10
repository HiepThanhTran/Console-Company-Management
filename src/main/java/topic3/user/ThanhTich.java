package topic3.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThanhTich {

    private int soLanLam;
    private List<Double> dsDiem = new ArrayList<>();

    public ThanhTich() {
    }

    public ThanhTich(int soLanLam, List<Double> dsDiem) {
        this.soLanLam = soLanLam;
        this.dsDiem = dsDiem;
    }

    public int getSoLanLam() {
        return soLanLam;
    }

    public void setSoLanLam(int soLanLam) {
        this.soLanLam = soLanLam;
    }

    public List<Double> getDsDiem() {
        return dsDiem;
    }

    public void setDsDiem(List<Double> dsDiem) {
        this.dsDiem = dsDiem;
    }

    public void themDiem(Double... dsDiem) {
        this.dsDiem.addAll(Arrays.asList(dsDiem));
    }

    public double getDiemTrungBinh() {
        return this.dsDiem.stream().mapToDouble(diem -> diem).average().getAsDouble();
    }

    public void hienThiThongTin() {
        System.out.printf("- So lan lam: %d\n", soLanLam);
        this.hienThiDsDiem();
        System.out.printf("- Diem trung binh: %.2f\n", this.getDiemTrungBinh());
    }

    public void hienThiDsDiem() {
        for (int i = 0; i < this.dsDiem.size(); i++) {
            System.out.printf("\t- Diem lan thu %d: %.2f\n", i + 1, this.dsDiem.get(i));
        }
    }

    public String toString() {
        return String.format("- So lan lam: %d\n- Diem trung binh: %.2f", soLanLam, this.getDiemTrungBinh());
    }
}
