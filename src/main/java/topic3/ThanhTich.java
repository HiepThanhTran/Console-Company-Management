package topic3;

import java.util.ArrayList;
import java.util.List;

public class ThanhTich {

    private int soLanLam;
    private List<Double> dsDiem;

    public ThanhTich() {
        this.soLanLam = 0;
        this.dsDiem = new ArrayList<>();
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

    public void themDiem(double diem) {
        this.dsDiem.add(diem);
    }

    public double getDiemTrungBinh() {
        return this.dsDiem.stream().mapToDouble(diem -> diem).average().getAsDouble();
    }

    public String formula() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.dsDiem.size(); i++) {
            sb.append(String.format("\t- Diem lan thu %d: %.2f\n", i + 1, this.dsDiem.get(i)));
        }
        return sb.toString();
    }

    public String toString() {
        return String.format("- So lan lam: %d\n- Danh sach diem cac lan lam: %s\n- Diem trung binh: %.2f", soLanLam,
            this.formula(), this.getDiemTrungBinh());
    }
}
