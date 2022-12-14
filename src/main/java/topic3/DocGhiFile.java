package topic3;

import static topic3.CauHinh.f;
import static topic3.CauHinh.quanLyCauHoi;
import static topic3.CauHinh.quanLyDanhMuc;
import static topic3.CauHinh.quanLyThanhVien;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;
import topic3.cauhoi.CauHoi;
import topic3.cauhoi.Conversation;
import topic3.cauhoi.DanhMuc;
import topic3.cauhoi.InComplete;
import topic3.cauhoi.MultipleChoice;
import topic3.cauhoi.PhuongAn;
import topic3.user.ThanhTich;
import topic3.user.ThanhVien;

public class DocGhiFile {

    public static final File multipleChoiceFile = new File("src/main/resources/topic3/MultipleChoice.txt");
    public static final File inCompleteFile = new File("src/main/resources/topic3/InComplete.txt");
    public static final File conversationFile = new File("src/main/resources/topic3/Conversation.txt");
    public static final File thanhVienFile = new File("src/main/resources/topic3/ThanhVien.txt");

    public static void docFile() {
        List<String> temp = new ArrayList<>();
        try {
            Scanner multipleChoice = new Scanner(multipleChoiceFile);
            while (multipleChoice.hasNextLine()) {
                String line = multipleChoice.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    for (int i = 1; i < temp.size(); i++) {
                        String[] thongTinPhuongAn = temp.get(i).split(" - ");
                        String noiDungPhuongAn = thongTinPhuongAn[0];
                        boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                        String giaiThich = thongTinPhuongAn[2];
                        PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                        dsPhuongAn.add(phuongAn);
                    }
                    CauHoi cauHoi = new MultipleChoice(mucDo, noiDungCauHoi, danhMuc, dsPhuongAn);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }

            Scanner inComplete = new Scanner(inCompleteFile);
            while (inComplete.hasNextLine()) {
                String line = inComplete.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<MultipleChoice> dsCauHoi = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    for (int i = 1; i < temp.size(); i += 5) {
                        String[] ttch = temp.get(i).split("/");
                        String md = ttch[0];
                        String nd = ttch[1];
                        String tdm = ttch[2];
                        for (int j = 1; j < 5; j++) {
                            String[] thongTinPhuongAn = temp.get(i + j).split(" - ");
                            String noiDungPhuongAn = thongTinPhuongAn[0];
                            boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                            String giaiThich = thongTinPhuongAn[2];
                            PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                            dsPhuongAn.add(phuongAn);
                        }
                        CauHoi cauHoi = new MultipleChoice(md, nd, new DanhMuc(tdm), dsPhuongAn);
                        CauHoi.setDem(CauHoi.getDem() - 1);
                        dsCauHoi.add((MultipleChoice) cauHoi);
                        dsPhuongAn = new ArrayList<>();
                    }
                    CauHoi cauHoi = new InComplete(mucDo, noiDungCauHoi, danhMuc, dsCauHoi);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }

            Scanner conversation = new Scanner(conversationFile);
            while (conversation.hasNextLine()) {
                String line = conversation.nextLine();
                if (!line.equals("end")) {
                    temp.add(line);
                } else {
                    List<MultipleChoice> dsCauHoi = new ArrayList<>();
                    String[] thongTinCauHoi = temp.get(0).split("/");
                    String mucDo = thongTinCauHoi[0];
                    String noiDungCauHoi = thongTinCauHoi[1];
                    String tenDanhMuc = thongTinCauHoi[2];
                    DanhMuc danhMuc = quanLyDanhMuc.traCuu(tenDanhMuc);
                    if (danhMuc == null) {
                        danhMuc = new DanhMuc(tenDanhMuc);
                    }
                    List<PhuongAn> dsPhuongAn = new ArrayList<>();
                    for (int i = 1; i < temp.size(); i += 5) {
                        String[] ttch = temp.get(i).split("/");
                        String md = ttch[0];
                        String nd = ttch[1];
                        String tdm = ttch[2];
                        for (int j = 1; j < 5; j++) {
                            String[] thongTinPhuongAn = temp.get(i + j).split(" - ");
                            String noiDungPhuongAn = thongTinPhuongAn[0];
                            boolean dapAn = Boolean.parseBoolean(thongTinPhuongAn[1]);
                            String giaiThich = thongTinPhuongAn[2];
                            PhuongAn phuongAn = new PhuongAn(noiDungPhuongAn, dapAn, giaiThich);
                            dsPhuongAn.add(phuongAn);
                        }
                        CauHoi cauHoi = new MultipleChoice(md, nd, new DanhMuc(tdm), dsPhuongAn);
                        CauHoi.setDem(CauHoi.getDem() - 1);
                        dsCauHoi.add((MultipleChoice) cauHoi);
                        dsPhuongAn = new ArrayList<>();
                    }
                    CauHoi cauHoi = new Conversation(mucDo, noiDungCauHoi, danhMuc, dsCauHoi);
                    danhMuc.themCauHoi(cauHoi);
                    quanLyCauHoi.themCauHoi(cauHoi);
                    quanLyDanhMuc.themDanhMuc(danhMuc);
                    temp.clear();
                }
            }
//
            Scanner thanhVien = new Scanner(thanhVienFile);
            while (thanhVien.hasNextLine()) {
                String[] thongTinThanhVien = thanhVien.nextLine().split(" - ");
                int maThanhVien = Integer.parseInt(thongTinThanhVien[0]);
                Date ngayGiaNhap = f.parse(thongTinThanhVien[1]);
                String hoTen = thongTinThanhVien[2];
                String queQuan = thongTinThanhVien[3];
                String gioiTinh = thongTinThanhVien[4];
                Date ngaySinh = f.parse(thongTinThanhVien[5]);
                ThanhVien thanhVien1 = new ThanhVien(maThanhVien, ngayGiaNhap, hoTen, queQuan, gioiTinh, ngaySinh,
                    new ThanhTich());
                if (thongTinThanhVien.length > 6) {
                    List<Double> dsDiem = new ArrayList<>();
                    int soLanLam = Integer.parseInt(thongTinThanhVien[6]);
                    for (int i = 7; i < thongTinThanhVien.length; i++) {
                        dsDiem.add(Double.parseDouble(thongTinThanhVien[i]));
                    }
                    ThanhTich thanhTich = new ThanhTich(soLanLam, dsDiem);
                    thanhVien1.setThanhTich(thanhTich);
                }
                quanLyThanhVien.themTV(thanhVien1);
            }

            multipleChoice.close();
            inComplete.close();
            conversation.close();
            thanhVien.close();
        } catch (FileNotFoundException | ParseException e) {
            e.printStackTrace();
        }
    }

    public static void GhiFile() {
        try {
            PrintWriter multipleChoice = new PrintWriter(multipleChoiceFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof MultipleChoice).forEach(ch -> {
                multipleChoice.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ((MultipleChoice) ch).getDsPhuongAn().forEach(phuongAn -> multipleChoice.println(
                    phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                multipleChoice.println("end");
            });

            PrintWriter inComplete = new PrintWriter(inCompleteFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof InComplete).forEach(ch -> {
                inComplete.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ch.getDsCauHoi().forEach(mc -> {
                    inComplete.println(mc.getMucDo() + "/" + mc.getNoiDung() + "/" + mc.getDanhMuc().getTenDanhMuc());
                    mc.getDsPhuongAn().forEach(phuongAn -> inComplete.println(
                        phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                });
                inComplete.println("end");
            });

            PrintWriter conversation = new PrintWriter(conversationFile);
            quanLyCauHoi.getDsCauHoi().stream().filter(cauHoi -> cauHoi instanceof Conversation).forEach(ch -> {
                conversation.println(ch.getMucDo() + "/" + ch.getNoiDung() + "/" + ch.getDanhMuc().getTenDanhMuc());
                ch.getDsCauHoi().forEach(mc -> {
                    conversation.println(mc.getMucDo() + "/" + mc.getNoiDung() + "/" + mc.getDanhMuc().getTenDanhMuc());
                    mc.getDsPhuongAn().forEach(phuongAn -> conversation.println(
                        phuongAn.getNoiDung() + " - " + phuongAn.isDapAn() + " - " + phuongAn.getGiaiThich()));
                });
                conversation.println("end");
            });

            PrintWriter thanhVien = new PrintWriter(thanhVienFile);
            quanLyThanhVien.getDsThanhVien().forEach(thanhVien1 -> {
                thanhVien.print(thanhVien1.getMaThanhVien());
                thanhVien.print(" - " + f.format(thanhVien1.getNgayGiaNhap()));
                thanhVien.print(" - " + thanhVien1.getHoTen());
                thanhVien.print(" - " + thanhVien1.getQueQuan());
                thanhVien.print(" - " + thanhVien1.getGioiTinh());
                thanhVien.print(" - " + f.format(thanhVien1.getNgaySinh()));
                thanhVien.print(" - " + thanhVien1.getThanhTich().getSoLanLam());
                if (thanhVien1.getThanhTich().getSoLanLam() > 0) {
                    for (int i = 0; i < thanhVien1.getThanhTich().getDsDiem().size(); i++) {
                        thanhVien.print(" - " + thanhVien1.getThanhTich().getDsDiem().get(i));
                    }
                }
                thanhVien.println();
            });
            multipleChoice.close();
            inComplete.close();
            conversation.close();
            thanhVien.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}