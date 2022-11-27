package topic3;

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

    @Override
    public String toString() {
        return String.format("- Noi dung: %s\n- Giai thich: %s", noiDung, giaiThich);
    }
}
