package topic3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public class QuanLyCauHoi {

    private final List<CauHoi> dsCauHoi = new ArrayList<>();

    public static int random(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    public List<CauHoi> getDsCauHoi() {
        return dsCauHoi;
    }

    public void themCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.add(cauHoi);
    }

    public void xoaCauHoi(CauHoi cauHoi) {
        this.dsCauHoi.remove(cauHoi);
    }

    public void hienThiDS() {
        this.dsCauHoi.forEach(cauHoi -> System.out.printf("\n%s\n", cauHoi));
    }

    public CauHoi traCuuTheoId(int maCauHoi) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMaCauHoi() == maCauHoi).findFirst().orElse(null);
    }

    public List<CauHoi> traCuu(int mucDo) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getMucDo() == mucDo).collect(Collectors.toList());
    }

    public List<CauHoi> traCuu(DanhMuc danhMuc) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getDanhMuc().equals(danhMuc)).collect(Collectors.toList());
    }

    public List<CauHoi> traCuu(String noiDung) {
        return this.dsCauHoi.stream().filter(cauHoi -> cauHoi.getNoiDung().equals(noiDung)).collect(Collectors.toList());
    }

    public CauHoi randomCauHoi(String path) throws ClassNotFoundException {
        Class c = Class.forName(path);
        List<CauHoi> temp = this.dsCauHoi.stream().filter(cauHoi -> c.isInstance(cauHoi)).collect(Collectors.toList());
        return temp.get(random(0, temp.size() - 1));
    }

    public List<CauHoi> randomCauHoi(String path, int soLuong) throws ClassNotFoundException {
        List<CauHoi> temp = new ArrayList<>();
        Class c = Class.forName(path);
        while (soLuong > 0) {
            CauHoi cauHoi = this.dsCauHoi.get(random(0, this.dsCauHoi.size() - 1));
            if (c.isInstance(cauHoi) && !temp.contains(cauHoi)) {
                temp.add(cauHoi);
            }
            soLuong--;
        }
        return temp;
    }
}
