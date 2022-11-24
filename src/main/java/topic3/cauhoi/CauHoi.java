package topic3.cauhoi;

import static topic3.CauHinh.sc;

public abstract class CauHoi {

    private int mucDo;
    private String noiDung;
    private DanhMuc danhMuc;

    public CauHoi() {
    }

    public CauHoi(int mucDo, String noiDung, DanhMuc danhMuc) {
        this.mucDo = mucDo;
        this.noiDung = noiDung;
        this.danhMuc = danhMuc;
    }

    public int getMucDo() {
        return mucDo;
    }

    public void setMucDo(int mucDo) {
        this.mucDo = mucDo;
    }

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public DanhMuc getDanhMuc() {
        return danhMuc;
    }

    public void setDanhMuc(DanhMuc danhMuc) {
        this.danhMuc = danhMuc;
    }

    public void nhapThongTin() {
        System.out.print("- Muc do: ");
        this.mucDo = Integer.parseInt(sc.nextLine());
        System.out.print("- Noi dung: ");
        this.noiDung = sc.nextLine();
    }

    @Override
    public String toString() {
        String lv = (mucDo == 1) ? "De" : (mucDo == 2 ? "Trung binh" : "Kho");
        return String.format("- Muc do: %s\t\t- Danh muc: %s\n- Noi dung: %s", lv, danhMuc, noiDung);
    }
}
