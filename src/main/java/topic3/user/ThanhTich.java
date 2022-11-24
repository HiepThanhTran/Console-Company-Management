package topic3.user;

import java.util.List;

public class ThanhTich {

    private int soLanLam;
    private double diemTB;
    private List<Double> diem;

    public ThanhTich() {
    }

    public ThanhTich(int soLanLam, double diemTB, List<Double> diem) {
        this.soLanLam = soLanLam;
        this.diemTB = diemTB;
        this.diem = diem;
    }

    public int getSoLanLam() {
        return soLanLam;
    }

    public void setSoLanLam(int soLanLam) {
        this.soLanLam = soLanLam;
    }

    public double getDiemTB() {
        return diemTB;
    }

    public void setDiemTB(double diemTB) {
        this.diemTB = diemTB;
    }

    public List<Double> getDiem() {
        return diem;
    }

    public void setDiem(List<Double> diem) {
        this.diem = diem;
    }

    public String toString() {
        return null;
    }
}
