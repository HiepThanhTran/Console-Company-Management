package topic3.cauhoi;

import static topic3.CauHinh.sc;

import java.util.ArrayList;
import java.util.List;

public class PhuongAn {

    private String noiDung;
    private boolean dapAn;
    private String giaiThich;

    public PhuongAn() {
    }

    public PhuongAn(String noiDung, boolean dapAn, String giaiThich) {
        this.noiDung = noiDung;
        this.dapAn = dapAn;
        this.giaiThich = giaiThich;
    }

    public static List<PhuongAn> nhapDSPhuongAn(int soLuong) {
        List<PhuongAn> dsPhuongAn = new ArrayList<>();
        for (int i = 0; i < soLuong; i++) {
            System.out.printf("\n== NHAP THONG TIN PHUONG AN THU %d ==\n", i + 1);
            PhuongAn phuongAn = new PhuongAn();
            phuongAn.nhapThongTin();
            dsPhuongAn.add(phuongAn);
        }
        return dsPhuongAn;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public boolean isDapAn() {
        return dapAn;
    }

    public void setDapAn(boolean dapAn) {
        this.dapAn = dapAn;
    }

    public String getGiaiThich() {
        return giaiThich;
    }

    public void setGiaiThich(String giaiThich) {
        this.giaiThich = giaiThich;
    }

    public void nhapThongTin() {
        System.out.print("- Noi dung: ");
        this.noiDung = sc.nextLine();
        System.out.print("- Dap an dung (0: Dung, 1: Sai): ");
        String dapAn = sc.nextLine();
        this.dapAn = dapAn.equals("0") || dapAn.equalsIgnoreCase("DUNG");
        System.out.print("- Giai thich: ");
        this.giaiThich = sc.nextLine();
    }

    @Override
    public String toString() {
        return noiDung;
    }
}