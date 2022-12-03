package topic3.cauhoi;

import static topic3.CauHinh.sc;

import java.util.List;
import java.util.Objects;

public abstract class CauHoi {

    private static int dem = 0;
    private final int maCauHoi;
    private String mucDo;
    private String noiDung;
    private DanhMuc danhMuc;

    {
        maCauHoi = ++dem;
    }

    public CauHoi() {
    }

    public CauHoi(String mucDo, String noiDung) {
        this.mucDo = mucDo;
        this.noiDung = noiDung;
    }

    public CauHoi(String mucDo, String noiDung, DanhMuc danhMuc) {
        this.mucDo = mucDo;
        this.noiDung = noiDung;
        this.danhMuc = danhMuc;
    }

    public static int getDem() {
        return dem;
    }

    public abstract List<MultipleChoice> getDsCauHoi();

    public int getMaCauHoi() {
        return maCauHoi;
    }

    public String getMucDo() {
        return mucDo;
    }

    public void setMucDo(String mucDo) {
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
        System.out.print("- Muc do (De/Trung binh/Kho): ");
        this.mucDo = sc.nextLine();
        System.out.print("- Noi dung: ");
        this.noiDung = sc.nextLine();
    }

    public void hienThiThongTin() {
        System.out.printf("\n== THONG TIN CAU HOI %d ==\n", maCauHoi);
//        System.out.println(this);
        System.out.printf("- Muc do: %s\t\t", mucDo);
        System.out.printf("- Danh muc: %s\n", danhMuc.getTenDanhMuc());
        System.out.printf("- Noi dung: %s\n", noiDung);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CauHoi cauHoi = (CauHoi) o;
        return maCauHoi == cauHoi.maCauHoi;
    }

    @Override
    public int hashCode() {
        return Objects.hash(maCauHoi);
    }

    @Override
    public String toString() {
        return String.format("- Muc do: %s\t\t- Danh muc: %s\n- Noi dung: %s", mucDo, danhMuc.getTenDanhMuc(), noiDung);
    }
}
