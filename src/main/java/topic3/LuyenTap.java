package topic3;

import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.sc;

import java.util.ArrayList;
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

    public void luyenTap(String type) throws ClassNotFoundException {
        CauHoi cauHoi = quanLyCauHoi.randomCauHoi(type);
        System.out.println("\n== THONG TIN CAU HOI ==");
        System.out.println(cauHoi);
        int soCauDung = traLoiCauHoi(cauHoi.getDsCauHoi());
        this.thanhTich.setSoLanLam(this.thanhTich.getSoLanLam() + 1);
        this.thanhTich.themDiem(soCauDung * (10 / cauHoi.getDsCauHoi().size()));
    }

    public void luyenTap(int soLuong) {
        List<MultipleChoice> dsCauHoi = quanLyCauHoi.randomCauHoi(soLuong);
        int soCauDung = traLoiCauHoi(dsCauHoi);
        this.thanhTich.setSoLanLam(this.thanhTich.getSoLanLam() + 1);
        this.thanhTich.themDiem(soCauDung * (10 / soLuong));
    }

    private int traLoiCauHoi(List<MultipleChoice> dsCauHoi) {
        List<Character> dsDapAn = new ArrayList<>();
        int soCauDung = 0;
        for (int i = 0; i < dsCauHoi.size(); i++) {
            System.out.printf("\n== CAU HOI THU %d ==\n", i + 1);
            System.out.println(dsCauHoi.get(i));
            System.out.println(dsCauHoi.get(i).formula());
            System.out.print("- Chon dap an: ");
            dsDapAn.add(sc.next().toUpperCase().charAt(0));
        }
        for (int i = 0; i < dsCauHoi.size(); i++) {
            System.out.printf("\n== CAU HOI THU %d ==\n", i + 1);
            MultipleChoice cauHoi = dsCauHoi.get(i);
            int index = (dsDapAn.get(i)) - 'A';
            boolean dapAn = index >= 0 && index < cauHoi.getDsPhuongAn().size() && cauHoi.getDsPhuongAn().get(index).isDapAn();
            System.out.println(cauHoi.getNoiDung() + " - " + (dapAn ? "Đúng" : "Sai"));
            if (dapAn) {
                soCauDung++;
            }
        }
        return soCauDung;
    }
}
